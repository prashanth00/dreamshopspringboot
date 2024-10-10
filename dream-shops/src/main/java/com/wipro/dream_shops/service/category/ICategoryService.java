package com.wipro.dream_shops.service.category;

import java.util.List;

import com.wipro.dream_shops.model.Category;

public interface ICategoryService {
	Category getCategoryById(Long id);
	Category getCateforyByName(String name);
	List<Category> getAllCategories();
	Category addCategory(Category category);
	Category updateCategory(Category category,Long id);
	void deleteCategoryById(Long id);
}
