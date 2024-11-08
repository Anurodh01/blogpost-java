package in.project.blogpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.project.blogpost.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
