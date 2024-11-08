 package in.project.blogpost;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import in.project.blogpost.entity.Role;


//NOTE: userName: anurodhsinghmp@gmail.com userPassword: anurodh@123 role: ADMIN

@SpringBootApplication
@EnableWebMvc
public class BlogpostApplication{

	public static void main(String[] args) {
		SpringApplication.run(BlogpostApplication.class, args);
	}
	
	
	@Bean
	public ModelMapper mapper()
	{
		return new ModelMapper();
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Bean
	public Role userRole()
	{
		return new Role(2, "ROLE_USER");
	}
	

}
