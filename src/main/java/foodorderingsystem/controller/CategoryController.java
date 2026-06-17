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

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(
            @RequestBody @Valid CategoryDto dto) {

        CategoryDto createdCategory =
                categoryService.addCategory(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCategory);
    }
}
