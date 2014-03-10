package net.ruangtedy.java.spring.bookstore.repository;

import java.util.List;

import net.ruangtedy.java.spring.bookstore.domain.Book;
import net.ruangtedy.java.spring.bookstore.domain.BookSearchCriteria;
import net.ruangtedy.java.spring.bookstore.domain.Category;

/**
 * Repository for working with {@link Book} domain objects
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public interface BookRepository {

	Book findById(long id);
	List<Book> findByCategory(Category category);
	List<Book > findRandom(int count);
	List<Book> findBooks(BookSearchCriteria bookSearchCriteria);
	void storeBook(Book book);
}
