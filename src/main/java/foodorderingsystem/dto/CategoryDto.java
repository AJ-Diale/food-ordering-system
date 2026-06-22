package foodorderingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/* Used to transfer data between layers */
public class CategoryDto {

    private Long id;

    /* Adding validation for category name and length */
    @NotBlank(message = "Category name is required")
    @Size(
            min = 2,
            max = 50,
            message = "Name must be 2-50 characters")

    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 255,
            message = "Description must be 5-255 characters")
    private String description;

    public CategoryDto() {
    }


    public CategoryDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}