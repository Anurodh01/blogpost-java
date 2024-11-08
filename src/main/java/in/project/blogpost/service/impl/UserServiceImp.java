package in.project.blogpost.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.project.blogpost.entity.Role;
import in.project.blogpost.entity.User;
import in.project.blogpost.exceptions.ResourceNotFoundException;
import in.project.blogpost.payloads.UserDTO;
import in.project.blogpost.repository.UserRepo;
import in.project.blogpost.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Role userRole;

	public UserDTO createUser(UserDTO userDTO) {
		User user = DTOToUser(userDTO);
		user.setUserPassword(this.passwordEncoder.encode(user.getUserPassword()));
		Set<Role> roles= new HashSet<>();
		roles.add(userRole);
		user.setRoles(roles);
		User user2= userRepo.save(user);
		return this.UserToDTO(user2);
	}

	public UserDTO updateUser(UserDTO userDTO, int userId) {
		
//		first find the user
		User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ", userId));
		
		user.setUserEmail(userDTO.getUserEmail());
		user.setAboutUser(userDTO.getAboutUser());
		user.setUserPassword(userDTO.getUserPassword());
		user.setUserName(userDTO.getUserName());
		
		User user2= userRepo.save(user);
		return this.UserToDTO(user2);
	}

	public UserDTO getUserByID(int userId) {
		User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ", userId));
		return this.UserToDTO(user);
	}

	public List<UserDTO> getAllUsers() {
		
		List<User> users= this.userRepo.findAll();
		
		List<UserDTO> userDTOs= users.stream().map(user -> this.UserToDTO(user)).collect(Collectors.toList());
		return userDTOs;
	}

	public void deleteUser(int userId) {
		User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "ID", userId));
		this.userRepo.delete(user);
	}
	
//	Coversion of userDTO to user and vice versa
//	Now we can use the ModelMapper object to map one class object to another
	
	@Autowired
	private ModelMapper modelMapper;
	
	private UserDTO UserToDTO(User user)
	{
		UserDTO userDTO= modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	
	private User DTOToUser(UserDTO userDTO)
	{
		User user= modelMapper.map(userDTO, User.class);
		return user;
	}

	@Override
	public UserDTO updateAttributes(Map<String, String> updates, int userId) {
		
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		updates.entrySet().forEach(entry ->{
			switch (entry.getKey()) {
			case "userName":
				user.setUserName(entry.getValue());
				break;
			case "userEmail":
				user.setUserEmail(entry.getValue());
				break;
			case "userPassword":
				user.setUserPassword(this.passwordEncoder.encode(entry.getValue()));
				break;
			case "aboutUser":
				user.setAboutUser(entry.getValue());
				break;
			default:
				break;
			}
			});
		User user2= this.userRepo.save(user);
		return this.modelMapper.map(user2, UserDTO.class);
	}

}
