package foodorderingsystem.service;

import foodorderingsystem.dto.MenuDto;
import foodorderingsystem.entity.Category;
import foodorderingsystem.entity.Menu;
import foodorderingsystem.exception.CategoryNotFoundException;
import foodorderingsystem.exception.MenuNotFoundException;
import foodorderingsystem.response.ApiResponse;
import foodorderingsystem.repository.CategoryRepository;
import foodorderingsystem.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse<MenuDto> createMenu(MenuDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: "
                                        + dto.getCategoryId()));

        Menu menu = mapToEntity(dto, category);

        Menu savedMenu = menuRepository.save(menu);

        return new ApiResponse<>(
                200,
                "Menu created successfully",
                mapToDto(savedMenu));
    }

    @Override
    public ApiResponse<Page<MenuDto>> getMenus(
            Long categoryId,
            String search,
            int page,
            int size,
            String sort) {

        String[] sortParts = sort.split(",");

        Sort.Direction direction = Sort.Direction.ASC;

        if (sortParts.length > 1 &&
                sortParts[1].equalsIgnoreCase("desc")) {

            direction = Sort.Direction.DESC;
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction, sortParts[0]));

        Specification<Menu> specification = Specification.where((PredicateSpecification<Menu>) null);

        if (categoryId != null) {
            specification = specification.and(
                    (root, query, cb) ->
                            cb.equal(root.get("category").get("id"), categoryId));
        }

        if (search != null && !search.isBlank()) {

            specification = specification.and(
                    (root, query, cb) ->
                            cb.like(
                                    cb.lower(root.get("name")),
                                    "%" + search.toLowerCase() + "%"));
        }

        Page<MenuDto> menuPage = menuRepository
                .findAll(specification, pageable)
                .map(this::mapToDto);

        return new ApiResponse<>(
                200,
                "Menus retrieved successfully",
                menuPage);
    }

    @Override
    public ApiResponse<List<MenuDto>> getAllMenus() {

        List<MenuDto> menus = menuRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();

        return new ApiResponse<>(
                200,
                "Menus retrieved successfully",
                menus);
    }

    @Override
    public ApiResponse<MenuDto> getMenuById(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new MenuNotFoundException(
                                "Menu not found with id: " + id));

        return new ApiResponse<>(
                200,
                "Menu found",
                mapToDto(menu));
    }

    @Override
    public ApiResponse<MenuDto> updateMenu(
            Long id,
            MenuDto dto) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new MenuNotFoundException(
                                "Menu not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: "
                                        + dto.getCategoryId()));

        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);

        Menu updatedMenu = menuRepository.save(menu);

        return new ApiResponse<>(
                200,
                "Menu updated successfully",
                mapToDto(updatedMenu));
    }

    @Override
    public ApiResponse<Void> deleteMenu(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new MenuNotFoundException(
                                "Menu not found with id: " + id));

        menuRepository.delete(menu);

        return new ApiResponse<>(
                200,
                "Menu deleted successfully",
                null);
    }

    private MenuDto mapToDto(Menu menu) {

        return new MenuDto(
                menu.getId(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getImageUrl(),
                menu.getCategory().getId(),
                menu.getCategory().getName()
        );
    }

    private Menu mapToEntity(
            MenuDto dto,
            Category category) {

        return Menu.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .category(category)
                .build();
    }
}