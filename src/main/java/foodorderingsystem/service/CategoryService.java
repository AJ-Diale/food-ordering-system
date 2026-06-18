package foodorderingsystem.service;

import java.util.List;

import foodorderingsystem.dto.CategoryDto;

/*Defines business operations*/
public interface CategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto addCategory(CategoryDto dto);
    CategoryDto updateCategory(Long id, CategoryDto dto);
    void deleteCategory(Long id);
}