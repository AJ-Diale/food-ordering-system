package foodorderingsystem.controller;

import foodorderingsystem.dto.CategoryDto;
import foodorderingsystem.entity.Category;
import foodorderingsystem.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    /*Getting all Categories*/
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /*Validating Input and adding new record*/
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    /*Validating Input and adding new record*/
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(
            @RequestBody @Valid CategoryDto dto) {

        CategoryDto createdCategory =
                categoryService.addCategory(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCategory);
    }
    /*Using the id endpoint to update existing records*/
    @PutMapping("/{id}")
    public CategoryDto updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CategoryDto dto) {

        return categoryService.updateCategory(id, dto);
    }

    /*Deleting records by ID*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
