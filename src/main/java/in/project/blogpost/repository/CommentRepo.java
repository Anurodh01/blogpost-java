package in.project.blogpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.project.blogpost.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
}
