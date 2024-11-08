package in.project.blogpost.payloads;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import in.project.blogpost.customvalidator.UserNameValidator;
import in.project.blogpost.entity.Role;

public class UserDTO {
	
	private int id;
	
//	@NotNull(message="username can not be null")
//	@NotBlank(message="username can not be blank")
//	@Size(min= 2, max=20)
	
	@UserNameValidator()               //this is custom validator
	private String userName;
	
	@NotBlank(message="UserEmail can not be blank")
	@Email
	private String userEmail;
	
	@NotEmpty(message="UserPassword can not be empty")
	@Size(min=5, message="Password must be of minimum 5 character long")
	private String userPassword;
	
	@NotEmpty(message="Please tell us something about you")
	@Size(min=5, max=100)
	private String aboutUser;
	
	private Set<Role> roles;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getAboutUser() {
		return aboutUser;
	}
	public void setAboutUser(String aboutUser) {
		this.aboutUser = aboutUser;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
