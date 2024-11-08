package in.project.blogpost.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="users")
public class User implements UserDetails{
	
		@Id
		@GeneratedValue(strategy= GenerationType.AUTO)
		private int id;
		
		
		private String userEmail;
		
		
		private String userName;
		
		
		private String userPassword;
		
		
		private String aboutUser;
		
		@ManyToMany(fetch=FetchType.EAGER)
		@JoinTable(
		name="user_roles",
		joinColumns= @JoinColumn(referencedColumnName="id", name="user"),
		inverseJoinColumns=@JoinColumn(name="role", referencedColumnName="id")
		)	
		private Set<Role> roles;
		
		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}

		@OneToMany(mappedBy="user", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
		private List<Post> posts= new ArrayList<>();

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
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

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			List<SimpleGrantedAuthority> grantedAuthorities= this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
			return grantedAuthorities;
		}

		@Override
		public String getPassword() {
			return this.userPassword;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return this.userEmail;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
		
		
}
