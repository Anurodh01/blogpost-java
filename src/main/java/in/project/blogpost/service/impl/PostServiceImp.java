package in.project.blogpost.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.project.blogpost.entity.Category;
import in.project.blogpost.entity.Post;
import in.project.blogpost.entity.User;
import in.project.blogpost.exceptions.ResourceNotFoundException;
import in.project.blogpost.payloads.PageResponse;
import in.project.blogpost.payloads.PostDTO;
import in.project.blogpost.repository.CategoryRepo;
import in.project.blogpost.repository.PostRepo;
import in.project.blogpost.repository.UserRepo;
import in.project.blogpost.service.PostService;

@Service
public class PostServiceImp implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDTO, int userId, int categoryId) {
		Post post = this.modelMapper.map(postDTO, Post.class);
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("USER", "ID", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		
		post.setUser(user);
		post.setCategory(category);
		post.setImageName("dog.png");
		Post post1= this.postRepo.save(post);
		
		return this.modelMapper.map(post1, PostDTO.class);
		
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, int postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("POST", "ID", postId));
		post.setContent(postDTO.getContent());
		post.setTitle(postDTO.getTitle());
		if(postDTO.getImageName()!= null)
		{
			post.setImageName(post.getImageName());
		}
		
		
		Post post1= this.postRepo.save(post);
		return this.modelMapper.map(post1, PostDTO.class);
		
	}

	@Override
	public void deletePost(int postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("POST", "ID", postId));
		
		this.postRepo.delete(post);

	}

	@Override
	public PageResponse getPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
//		for the pagination purpose some methods are
		
//		Pageable pageable = PageRequest.of(pageNumber, pageSize); 
//		for the sorting we willl be using the overloaded method as
		Sort sort= sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending():  Sort.by(sortBy);  
		
		Pageable pageable= PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePosts= this.postRepo.findAll(pageable);
		List<Post> posts= pagePosts.getContent();
		
		PageResponse pageResponse= new PageResponse();
		pageResponse.setContent(ConversonOfListOfPostToPostDTO(posts));
		pageResponse.setPageNumber(pagePosts.getNumber());
		pageResponse.setPageSize(pagePosts.getSize());
		pageResponse.setTotalElements(pagePosts.getTotalElements());
		pageResponse.setTotalpages(pagePosts.getTotalPages());
		pageResponse.setLastPage(pagePosts.isLast());
		return pageResponse;
		
//		List<Post> posts= this.postRepo.findAll();
		
//		conversion of posts to PostDTO
		
//		return ConversonOfListOfPostToPostDTO(posts);
		
	}

	@Override
	public PostDTO getPostById(int postId) {
		
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("POST", "ID", postId));
		return this.modelMapper.map(post, PostDTO.class);
		
	}

	@Override
	public List<PostDTO> getPostsByUser(int userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("USER", "ID", userId));
		List<Post> posts= this.postRepo.findByUser(user);
		
//		now convert the post to PostDTO's
		return ConversonOfListOfPostToPostDTO(posts);
	}

	@Override
	public List<PostDTO> getPostByCategory(int categoryId) {
		
		Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		List<Post> posts= this.postRepo.findByCategory(category);
		
//		Now convert these to PostDTO
		return ConversonOfListOfPostToPostDTO(posts);
	}
	
	public List<PostDTO> ConversonOfListOfPostToPostDTO(List<Post> posts)
	{
		return posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleIgnoreCaseContainingOrContentIgnoreCaseContaining(keyword, keyword);
		return ConversonOfListOfPostToPostDTO(posts);
	}

	@Override
	public String uploadImage(String path, MultipartFile image, int postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		String imageName= image.getOriginalFilename();
		String imageAbsolutePath= path+ File.separator+ imageName;
		post.setImageName(imageName);
		this.postRepo.save(post);
		String pathing=null;
		Resource resource= new ClassPathResource(path);
		try {
			pathing= resource.getFile().getAbsolutePath();
		}catch(IOException e)
		{}
		File file= new File(pathing+File.separator+imageName);
		String absolutepath= file.getAbsolutePath();
		System.out.println(absolutepath);
		
		try {
			Files.copy(image.getInputStream(), Paths.get(absolutepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageName;
	}

	@Override
	public InputStream getResource(String path, String name) throws FileNotFoundException  {
		
		InputStream resource= null;
		try {
			Resource classPathResource = new ClassPathResource("/static/images/"+name);
			String absolutepath= classPathResource.getFile().getAbsolutePath();
			resource= new FileInputStream(absolutepath);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
		} catch(Exception e)
		{
			throw new FileNotFoundException("File could not be found!!!");
		}
		
		
		return resource;
		
		
	}
	
	
	
}
