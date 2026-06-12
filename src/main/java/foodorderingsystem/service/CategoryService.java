package foodorderingsystem.service;

import java.util.List;
import foodorderingsystem.dto.CategoryDto;
/*Defines business operations*/
public interface CategoryService {

    List<CategoryDto> getAllCategories();

}