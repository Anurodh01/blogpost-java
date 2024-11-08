package in.project.blogpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.project.blogpost.entity.Category;
import in.project.blogpost.entity.Post;
import in.project.blogpost.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleIgnoreCaseContainingOrContentIgnoreCaseContaining(String keyword, String content);
	
}
