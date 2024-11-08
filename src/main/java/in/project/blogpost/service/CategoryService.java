package in.project.blogpost.service;

import java.util.List;

import in.project.blogpost.payloads.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId);
	
	CategoryDTO getCategory(int categoryId);
	
	List<CategoryDTO> getCategories();
	
	void deleteCategory(int categoryId);
}
