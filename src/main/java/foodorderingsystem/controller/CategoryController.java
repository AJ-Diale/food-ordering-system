package foodorderingsystem.controller;

import foodorderingsystem.dto.CategoryDto;
import foodorderingsystem.response.ApiResponse;
import foodorderingsystem.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Handles HTTP requests for Category operations.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
     * GET ALL
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories() {

        List<CategoryDto> categories =
                categoryService.getAllCategories();

        ApiResponse<List<CategoryDto>> response =
                new ApiResponse<>(
                        200,
                        "Categories retrieved successfully",
                        categories);

        return ResponseEntity.ok(response);
    }

    /*
     * GET BY ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(
            @PathVariable Long id) {

        CategoryDto category =
                categoryService.getCategoryById(id);

        ApiResponse<CategoryDto> response =
                new ApiResponse<>(
                        200,
                        "Category found",
                        category);

        return ResponseEntity.ok(response);
    }

    /*
     * CREATE
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> addCategory(
            @RequestBody @Valid CategoryDto dto) {

        CategoryDto category =
                categoryService.addCategory(dto);

        ApiResponse<CategoryDto> response =
                new ApiResponse<>(
                        201,
                        "Category created successfully",
                        category);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /*
     * UPDATE
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CategoryDto dto) {

        CategoryDto category =
                categoryService.updateCategory(id, dto);

        ApiResponse<CategoryDto> response =
                new ApiResponse<>(
                        200,
                        "Category updated successfully",
                        category);

        return ResponseEntity.ok(response);
    }

    /*
     * DELETE
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        204,
                        "Category deleted successfully",
                        null);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(response);
    }
}