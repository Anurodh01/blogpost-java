package in.project.blogpost.payloads;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import in.project.blogpost.entity.Post;
import in.project.blogpost.entity.User;

public class CommentDTO {
	
	private int id;
	
	@NotNull
	@NotEmpty(message="Content inside the commnet should not be empty.")
	private String content;
	
	@JsonIgnoreProperties("comments")
	private PostDTO post;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PostDTO getPost() {
		return post;
	}

	public void setPost(PostDTO post) {
		this.post = post;
	}


	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
