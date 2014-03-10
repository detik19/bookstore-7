package net.ruangtedy.java.spring.bookstore.domain.support;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import net.ruangtedy.java.spring.bookstore.domain.Book;
import net.ruangtedy.java.spring.bookstore.domain.Category;
/**
 * Builds {@link Book} domain objects
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
@Component
public class BookBuilder extends EntityBuilder<Book> {
	private Book product;
	
	
	public BookBuilder title(String title){
		product.setTitle(title);
		return this;
	}
	
	public BookBuilder description(String description){
		product.setDescription(description);
		return this;
	}
	
	public BookBuilder author(String author){
		product.setAuthor(author);
		return this;
	}
	
	public BookBuilder year(int year){
		product.setYear(year);
		return this;
	}
	
	public BookBuilder price(String price){
		product.setPrice(new BigDecimal(price));
		return this;
	}
	
	public BookBuilder category(Category category){
		product.setCategory(category);
		return this;
	}
	
	public BookBuilder isbn(String isbn) {
		product.setIsbn(isbn);
		return this;
		
	}
	@Override
	void initProduct() {
		product=new Book();
		
	}

	@Override
	Book assembleProduct() {
		// TODO Auto-generated method stub
		return product;
	}
	

}
