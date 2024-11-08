package in.project.blogpost.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.project.blogpost.entity.Category;
import in.project.blogpost.exceptions.ResourceNotFoundException;
import in.project.blogpost.payloads.CategoryDTO;
import in.project.blogpost.repository.CategoryRepo;
import in.project.blogpost.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		
		Category category= this.modelMapper.map(categoryDTO, Category.class);
		Category category2= this.categoryRepo.save(category);
		return this.modelMapper.map(category2, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId) {
//		 first find the category with the folowing categoryId
		Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		
		category.setCategoryTitle(categoryDTO.getTitle());
		category.setCategoryDescription(categoryDTO.getDescription());
		Category category2= this.categoryRepo.save(category);
		return this.modelMapper.map(category2, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategory(int categoryId) {
		Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> categories= this.categoryRepo.findAll();
		List<CategoryDTO> categoryDTOs= categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return categoryDTOs;
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		this.categoryRepo.delete(category);
		
	}

}
