package in.project.blogpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.project.blogpost.config.JWTTokenHelper;
import in.project.blogpost.payloads.APIMessage;
import in.project.blogpost.payloads.JWTRequest;
import in.project.blogpost.payloads.JWTResponse;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/auth")
public class JWTAuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@PostMapping("/login")
	public ResponseEntity<JWTResponse> authenticateuser(@RequestBody JWTRequest jwtRequest)
	{
		this.authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
		
		UserDetails userDetails= this.userDetailsService.loadUserByUsername(jwtRequest.getEmail());
		String token= this.jwtTokenHelper.generateToken(userDetails);
		JWTResponse response= new JWTResponse();
		response.setToken(token);
		
		return new ResponseEntity<JWTResponse>(response, HttpStatus.ACCEPTED);	
	}
	
	
	public void authenticate(String email, String password)
	{
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(email, password);
		this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	}

	
	@ApiIgnore
	@GetMapping("/access-denied")
	public ResponseEntity<APIMessage> accessDenied()
	{
		return new ResponseEntity<APIMessage>(new APIMessage("Access Denied!!", false), HttpStatus.FORBIDDEN);
	}
	
}
