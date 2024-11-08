package in.project.blogpost.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int postId;
		
	@Column(nullable=false, length=100)
	private String title;
	
	@Column(nullable=false, length=500)
	private String content;
	
	@Column(nullable=false, name="image")
	private String imageName;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

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
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", user=" + user + ", category=" + category + "]";
	}
	
	
	
}