package in.project.blogpost.controller;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.project.blogpost.payloads.PostDTO;
import in.project.blogpost.service.PostService;

@RestController
public class ImageController {
	
	@Value("${project.upload}")
	String path;
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping(value= "/api/upload-image/{postId}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<PostDTO> uploadImage(@RequestPart("image") MultipartFile image, @PathVariable("postId") int postId){
		PostDTO post= this.postService.getPostById(postId);
		String imageName = this.postService.uploadImage(path,image, postId);
		post.setImageName(imageName);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value= "/images/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void getResource(@PathVariable("imageName") String imageName, HttpServletResponse response) throws FileNotFoundException{
		
		try {
		InputStream resource = this.postService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);		
		StreamUtils.copy(resource, response.getOutputStream());
		}
		catch(Exception ex)
		{
			throw new FileNotFoundException("File not found!!");
		}
		
		
	}

}
