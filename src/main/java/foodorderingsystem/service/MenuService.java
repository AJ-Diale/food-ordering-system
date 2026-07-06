package foodorderingsystem.service;

import foodorderingsystem.dto.MenuDto;
import foodorderingsystem.response.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MenuService {

    ApiResponse<MenuDto> createMenu(MenuDto dto);

    ApiResponse<Page<MenuDto>> getMenus(
            Long categoryId,
            String search,
            int page,
            int size,
            String sort);

    ApiResponse<MenuDto> getMenuById(Long id);

    ApiResponse<MenuDto> updateMenu(Long id, MenuDto dto);

    ApiResponse<Void> deleteMenu(Long id);


    ApiResponse<List<MenuDto>> getAllMenus();
}