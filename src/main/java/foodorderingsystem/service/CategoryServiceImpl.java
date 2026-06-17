package foodorderingsystem.service;

import foodorderingsystem.dto.CategoryDto;
import foodorderingsystem.entity.Category;
import foodorderingsystem.exception.CategoryNotFoundException;
import foodorderingsystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /*Displaying record based on ID*/
    @Override
    public CategoryDto getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + id));

        return new CategoryDto(
                category.getId(),
                category.getName());
    }

    /*adding new record*/
    @Override
    public CategoryDto addCategory(CategoryDto dto) {

        Category category = new Category();
        category.setName(dto.getName());

        Category savedCategory = categoryRepository.save(category);

        return new CategoryDto(
                savedCategory.getId(),
                savedCategory.getName());
    }

    /*Updating existing record*/
    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {

        Category category = categoryRepository.findById(id).orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id: " + id));

        category.setName(dto.getName());
        Category updatedCategory = categoryRepository.save(category);

        return new CategoryDto(updatedCategory.getId(), updatedCategory.getName());
    }
}