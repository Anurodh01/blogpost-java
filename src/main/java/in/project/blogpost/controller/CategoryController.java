package in.project.blogpost.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.project.blogpost.payloads.APIMessage;
import in.project.blogpost.payloads.CategoryDTO;
import in.project.blogpost.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping()
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
	{
		return new ResponseEntity<CategoryDTO>(this.categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<CategoryDTO>> getCategories()
	{
		List<CategoryDTO> categoryDTOs= this.categoryService.getCategories();
		return new ResponseEntity<List<CategoryDTO>>(categoryDTOs ,HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable("categoryId") int categoryId)
	{
		return new ResponseEntity<CategoryDTO>(this.categoryService.getCategory(categoryId), HttpStatus.OK);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable("categoryId") int categoryId)
	{
		return new ResponseEntity<CategoryDTO>(this.categoryService.updateCategory(categoryDTO, categoryId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<APIMessage> deleteCategory(@PathVariable("categoryId") int categoryId)
	{
		APIMessage apiMessage=new APIMessage();
		this.categoryService.deleteCategory(categoryId);
		apiMessage.setMessage("Category with "+ categoryId +" has been deleted Successfully.");
		apiMessage.setSuccess(true);
		return new ResponseEntity<APIMessage>(apiMessage, HttpStatus.ACCEPTED);
		
	}
	
	
}
