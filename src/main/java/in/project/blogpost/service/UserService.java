package in.project.blogpost.service;

import java.util.List;
import java.util.Map;

import in.project.blogpost.payloads.UserDTO;


public interface UserService {
	
	UserDTO createUser(UserDTO user);
	
	UserDTO updateAttributes(Map<String, String> updates, int userId);
	
	UserDTO updateUser(UserDTO user, int userId);
	
	UserDTO getUserByID(int userId);
	
	List<UserDTO> getAllUsers();
	
	void deleteUser(int userId);
}
