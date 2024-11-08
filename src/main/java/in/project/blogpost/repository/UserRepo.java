package in.project.blogpost.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.project.blogpost.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findByUserEmail(String userEmail);
}
