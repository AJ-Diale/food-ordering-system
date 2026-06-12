package foodorderingsystem.service;

import java.util.List;

import foodorderingsystem.entity.Category;

/*Defines business operations*/
public interface CategoryService {

    List<Category> getAllCategories();

}