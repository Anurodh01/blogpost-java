package in.project.blogpost.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="comments")
public class Comment {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="postId")
	@JsonIgnoreProperties("comments")
	private Post post;
	

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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}


	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
