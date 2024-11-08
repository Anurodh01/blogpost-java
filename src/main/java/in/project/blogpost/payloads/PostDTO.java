package in.project.blogpost.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import in.project.blogpost.entity.Comment;

public class PostDTO {
	
	private int postId;
	
	@NotNull
	@NotBlank(message="Title could not be empty")
	private String title;
	
	@NotNull @NotBlank
	private String content;
	
	private String imageName;
	
	private UserDTO user;
	
	private CategoryDTO category;
	
	private List<Comment> comments;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		this.imageName= ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(this.imageName).toUriString();
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public PostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}
