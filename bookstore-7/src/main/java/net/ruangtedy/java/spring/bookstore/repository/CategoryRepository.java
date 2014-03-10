package net.ruangtedy.java.spring.bookstore.repository;

import java.util.List;

import net.ruangtedy.java.spring.bookstore.domain.Category;

public interface CategoryRepository {
	List<Category> findAll();
	Category findById(long id);
	void storeCategory(Category category);

}
