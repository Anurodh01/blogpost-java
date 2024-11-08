package in.project.blogpost.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import in.project.blogpost.exceptions.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static final String[] PUBLIC_URL= {
			"/images/**",
			"/api/auth/**",
			"/api/users",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"};
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JWTAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(PUBLIC_URL).permitAll()
		.antMatchers(HttpMethod.GET).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
		.accessDeniedHandler(new CustomAccessDeniedHandler())
		.and()
		.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
//	here for the basic authentication to use the custom userDetail services we need to override the
//	authentication builder method

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AccessDeniedHandler customAccessDeniedHandler()
	{
		return new CustomAccessDeniedHandler();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
	

	
	
	
	
	
}
