package foodorderingsystem.service;

import foodorderingsystem.dto.MenuDto;
import foodorderingsystem.response.ApiResponse;

import java.util.List;

public interface MenuService {

    ApiResponse<MenuDto> createMenu(MenuDto dto);

    ApiResponse<List<MenuDto>> getAllMenus();

    ApiResponse<MenuDto> getMenuById(Long id);
}