package in.project.blogpost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.project.blogpost.controller.UserController;
import in.project.blogpost.entity.Role;
import in.project.blogpost.payloads.UserDTO;
import in.project.blogpost.service.UserService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
public class UserControllerTest {

	private MockMvc mockMvc;
	
	List<UserDTO> users;
	
	@Mock
	private UserService userService;
	
	@InjectMocks 
	private UserController userController;
	
	@Autowired 
	private ObjectMapper objectMapper;
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		users= new ArrayList<>();
		UserDTO record_1= new UserDTO();
		record_1.setId(1);
		record_1.setAboutUser("Anurodh is a java trainee");
		record_1.setUserEmail("anurodhsingh@gmail.com");
		record_1.setUserName("anurodh@111");
		record_1.setUserPassword("mouse@111");
		UserDTO record_2= new UserDTO();
		record_2.setId(2);
		record_2.setAboutUser("DTDc will parcel the charger");
		record_2.setUserEmail("dtdcContact@gmail.com");
		record_2.setUserName("dtdc@111");
		record_2.setUserPassword("dtdcsupport@111");
		users.add(record_1);
		users.add(record_2);
	}
	
	@Test
	public void getAllUsersTest() throws Exception {
		
		Mockito.when(userService.getAllUsers()).thenReturn(users);
		
		ResultActions response = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/users")
				.contentType(MediaType.APPLICATION_JSON));
		
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$[0].userName", is("anurodh@111")))
		.andExpect(jsonPath("$.size()", is(users.size())))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].userEmail").value("dtdcContact@gmail.com"));
		
	}
	
	
	@Test
	public void createUsersTest() throws Exception {
		UserDTO userDTO= new UserDTO();
		userDTO.setId(3);
		userDTO.setAboutUser("I am anurodh singh, and i love travelling and cricket playing.");
		userDTO.setUserEmail("anurodhsinghmp@gmail.com");
		userDTO.setUserName("anurodh@111");
		userDTO.setUserPassword("anurodh@000");
		
		Mockito.when(userService.createUser(userDTO)).thenReturn(userDTO);
		
		String content = objectMapper.writeValueAsString(userDTO);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		
		ResultActions response= mockMvc.perform(mockRequest);
		
		response.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$[0].userName",is("anurodh@111")));
		
		
	}
	
 	
}	
