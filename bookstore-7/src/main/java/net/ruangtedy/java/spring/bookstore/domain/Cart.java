package net.ruangtedy.java.spring.bookstore.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * Basic implementation of a cart.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6371080009277810835L;

	private Map<Book, Integer> books=new HashMap<Book, Integer>();
	
	public Map<Book, Integer> getBooks(){
		return Collections.unmodifiableMap(this.books);
		
	}
	public void addBook(Book book){
		if(this.books.containsKey(book)){
			int quntity=this.books.get(book);
			quntity++;
			this.books.put(book, quntity);
		}
		else{
			this.books.put(book, 1);
		}
	}
	public void removeBook(Book book){
		this.books.remove(book);
	}
	
	public void clear(){
		this.books.clear();
	}
	@Override
	public String toString() {
		 ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
	     builder.append("books", this.books.keySet());
	     return builder.build();
	}
	
	
	
}
