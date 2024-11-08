package in.project.blogpost.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryDTO {
	
	private int id;
	
	@NotNull(message="Title should not be null")
	@NotEmpty(message="Title can not be empty")
	private String title;
	
	@NotNull(message="Category Description should not be null")
	@NotEmpty(message="description can not be empty")
	private String description;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDTO(@NotNull(message = "Title should not be null") String title,
			@NotNull(message = "Category Description should not be null") String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
