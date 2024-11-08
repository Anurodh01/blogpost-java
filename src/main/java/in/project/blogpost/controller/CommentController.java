package in.project.blogpost.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.project.blogpost.payloads.APIMessage;
import in.project.blogpost.payloads.CommentDTO;
import in.project.blogpost.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable("postId") int postId)
	{
		commentDTO= this.commentService.createComment(commentDTO, postId);
		return new ResponseEntity<CommentDTO>(commentDTO, HttpStatus.OK);
	}
	
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDTO>> getComments()
	{
		List<CommentDTO> commentDTOs= this.commentService.getComments();
		return new ResponseEntity<List<CommentDTO>>(commentDTOs, HttpStatus.OK);
	}
	
	
	@DeleteMapping("comments/{commentId}")
	public ResponseEntity<APIMessage> deleteComment(@PathVariable("commentId") int id)
	{
		this.commentService.deleteComment(id);
		APIMessage message= new APIMessage();
		message.setMessage("Comment with commentId "+id+" has been deleted successfully.");
		message.setSuccess(true);
		return new ResponseEntity<APIMessage>(message, HttpStatus.OK);
		
	}
	
}
