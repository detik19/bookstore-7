package net.ruangtedy.java.spring.bookstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * An order detail is the link table between {@link Order} and {@link Book} We also store how many books are ordered in
 * the {@link #quantity} field
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
@Entity
public class OrderDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9123428160744973648L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional=false)
	private Book book;
	
	private int  quantity=1;
	
	public OrderDetail() {
		super();
	}
	
	public OrderDetail(Book book, int quantity){
		super();
		this.book=book;
		this.quantity=quantity;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getPrice(){
		if(this.book!=null){
			return this.book.getPrice().multiply(new BigDecimal(this.quantity));
		}
		return BigDecimal.ZERO;
	}
	

}
