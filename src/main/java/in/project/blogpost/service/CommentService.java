package in.project.blogpost.service;

import java.util.List;

import in.project.blogpost.payloads.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO commentDTO, int postId);
	
	List<CommentDTO> getComments();
	
	void deleteComment(int commentId);
	
	
}
