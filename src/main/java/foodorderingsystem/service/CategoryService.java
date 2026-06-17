package foodorderingsystem.service;

import java.util.List;

import foodorderingsystem.dto.CategoryDto;
import foodorderingsystem.entity.Category;

/*Defines business operations*/
public interface CategoryService {

    List<Category> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto addCategory(CategoryDto dto);
}