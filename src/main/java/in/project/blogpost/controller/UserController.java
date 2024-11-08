package in.project.blogpost.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.project.blogpost.payloads.APIMessage;
import in.project.blogpost.payloads.UserDTO;
import in.project.blogpost.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> CreateUser(@Valid @RequestBody UserDTO userDTO)
	{
		UserDTO userDTO2= this.userService.createUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO2, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getUsers()
	{
		
		List<UserDTO> userDTOs= this.userService.getAllUsers();
		if(userDTOs.size()== 0)
		{
			return ResponseEntity.noContent().build();
		}
		return new  ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int userId)
	{
			UserDTO userDTO= userService.getUserByID(userId);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("id") int userId)
	{
		
		userDTO= this.userService.updateUser(userDTO, userId);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<APIMessage> deleteUser(@PathVariable("id") int userId)
	{
		this.userService.deleteUser(userId);
		APIMessage message= new APIMessage();
		message.setSuccess(true);
		message.setMessage("User has been Deleted successfully.");
		return new ResponseEntity<APIMessage>(message, HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("{userId}/update")
	public ResponseEntity<UserDTO> updateAttributes(@RequestBody Map<String, String> updates, @PathVariable("userId") int userId)
	{
		UserDTO userDTO= this.userService.updateAttributes(updates, userId);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
	}
	
	
	
	
}
