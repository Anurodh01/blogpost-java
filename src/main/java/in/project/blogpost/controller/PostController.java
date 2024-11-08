package in.project.blogpost.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.project.blogpost.config.Constants;
import in.project.blogpost.entity.Post;
import in.project.blogpost.payloads.APIMessage;
import in.project.blogpost.payloads.PageResponse;
import in.project.blogpost.payloads.PostDTO;
import in.project.blogpost.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Value("${project.upload}")
	String path;
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO
			,@PathVariable("userId") int userId,
			@PathVariable("categoryId") int categoryId)
	{
		postDTO= this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(postDTO, HttpStatus.ACCEPTED);
	}
	
//	get posts by category wise
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable("categoryId") int categoryId)
	{
		List<PostDTO> postDTOs= this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}
	
//	getting posts by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable("userId") int userId)
	{
		List<PostDTO> postDTOs= this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}
	
//	getting user by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") int postId)
	{
		return new ResponseEntity<PostDTO>(this.postService.getPostById(postId), HttpStatus.OK);
	}
	
//	getting all posts 
//	we were returning the all post data at the same time which is not efficient 
//	so try to apply pagination and also change the response format 
//	as the user should know that at which he is, what is total number of posts are there, how many pages are there many more
//	@GetMapping("/posts")
//	public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(value= "pageNumber", required= false, defaultValue="0") int pageNumber,
//			@RequestParam(value="pageSize", required=false, defaultValue="5") int pageSize){
//		List<PostDTO> postDTOs= this.postService.getPosts(pageNumber, pageSize);
//		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
//	}
	
//	add functionality of sorting the data
	@GetMapping("/posts")
	public ResponseEntity<PageResponse> getAllPosts(
	@RequestParam(value="pageNumber", required=false, defaultValue=Constants.PAGE_NUMBER) int pageNumber,
	@RequestParam(value="pageSize", required=false, defaultValue=Constants.PAGE_SIZE) int pageSize,
	@RequestParam(value="sortBy", required=false, defaultValue=Constants.SORT_BY) String sortBy,
	@RequestParam(value="sortDir", required=false, defaultValue=Constants.SORT_DIR) String sortDir
			){
		PageResponse pageResponse= this.postService.getPosts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PageResponse>(pageResponse, HttpStatus.OK);
	}
	
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable("postId") int postId)
	{
		postDTO= this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(postDTO, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<APIMessage> deletePost(@PathVariable("postId") int postId)
	{
		this.postService.deletePost(postId);
		APIMessage message= new APIMessage();
		message.setMessage("Post with postId "+ postId +" has been deleted successfully.");
		message.setSuccess(true);
		return new ResponseEntity<APIMessage>(message, HttpStatus.OK);
	}
	
	
	@GetMapping("/posts/search/{searchKeyword}")
	public ResponseEntity<List<PostDTO>> searchProducts(@PathVariable("searchKeyword") String searchKeyword){
		List<PostDTO> posts= this.postService.searchPosts(searchKeyword);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	
	
}
