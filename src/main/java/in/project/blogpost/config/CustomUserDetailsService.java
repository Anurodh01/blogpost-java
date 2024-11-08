package in.project.blogpost.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.project.blogpost.entity.Role;
import in.project.blogpost.entity.User;
import in.project.blogpost.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//here we will get the user by email
		User user= this.userRepo.findByUserEmail(username).orElseThrow(()-> new UsernameNotFoundException("UserEmail not Found"));
		user.getRoles().forEach(role -> System.out.println(role.getName()));
		return user;
		
		
	}

}
