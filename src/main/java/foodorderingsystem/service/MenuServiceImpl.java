package foodorderingsystem.service;

import foodorderingsystem.dto.MenuDto;
import foodorderingsystem.entity.Category;
import foodorderingsystem.entity.Menu;
import foodorderingsystem.exception.CategoryNotFoundException;
import foodorderingsystem.response.ApiResponse;
import foodorderingsystem.repository.CategoryRepository;
import foodorderingsystem.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                        new RuntimeException(
                                "Menu not found with id: " + id));

        return new ApiResponse<>(
                200,
                "Menu found",
                mapToDto(menu));
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