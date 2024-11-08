package in.project.blogpost.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.project.blogpost.payloads.PageResponse;
import in.project.blogpost.payloads.PostDTO;

public interface PostService {
	
	PostDTO createPost(PostDTO postDTO, int userId, int categoryId);
	
	PostDTO updatePost(PostDTO postDTO, int postId);
	
	void deletePost(int postId);
	
	//getting posts
	
	PageResponse getPosts(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	PostDTO getPostById(int postId);
	
	//get post by User
	
	List<PostDTO> getPostsByUser(int userId);
	
	//get post by category
	
	List<PostDTO> getPostByCategory(int categoryId);
	
//	Searching the post by different ways
	
	List<PostDTO> searchPosts(String keyword);
	
	
	String uploadImage(String path, MultipartFile image, int postId);
	
	InputStream getResource(String path, String name) throws FileNotFoundException;
	
}
