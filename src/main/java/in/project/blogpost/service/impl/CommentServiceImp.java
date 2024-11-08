package in.project.blogpost.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.project.blogpost.entity.Comment;
import in.project.blogpost.entity.Post;
import in.project.blogpost.exceptions.ResourceNotFoundException;
import in.project.blogpost.payloads.CommentDTO;
import in.project.blogpost.repository.CommentRepo;
import in.project.blogpost.repository.PostRepo;
import in.project.blogpost.service.CommentService;
import net.bytebuddy.asm.Advice.This;

@Service
public class CommentServiceImp implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, int postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("POST", "ID", postId));
		Comment comment= this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		comment= this.commentRepo.save(comment);
		post.getComments().add(comment);
		post= this.postRepo.save(post);
		return this.modelMapper.map(comment, CommentDTO.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment= this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		this.commentRepo.delete(comment);
	}
	
	@Override
	public List<CommentDTO> getComments()
	{
		List<Comment> comments= this.commentRepo.findAll();
		List<CommentDTO> commentDTOs= comments.stream().map((comment)-> this.modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
		return commentDTOs;
	}

}
