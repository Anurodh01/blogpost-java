package in.project.blogpost.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name="title")
	private String categoryTitle;
	
	@Column(name="description")
	private String categoryDescription;
	
	@OneToMany(mappedBy="category", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
	private List<Post> posts= new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int id, String categoryTitle, String categoryDescription) {
		super();
		this.id = id;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}
	
	
}
