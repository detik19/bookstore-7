package net.ruangtedy.java.spring.bookstore.domain.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;




import net.ruangtedy.java.spring.bookstore.domain.Account;
import net.ruangtedy.java.spring.bookstore.domain.Book;
import net.ruangtedy.java.spring.bookstore.domain.Order;
import net.ruangtedy.java.spring.bookstore.domain.OrderDetail;

public class OrderBuilder extends EntityBuilder<Order> {

	private List<OrderDetail> orderDetails;
	
	@Override
	void initProduct() {
		this.product=new Order();
		this.orderDetails=new ArrayList<OrderDetail>();
		
	}
	
	public OrderBuilder addBook(Book book, int quantity){
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setQuantity(quantity);
		
		orderDetail.setBook(book);
		this.orderDetails.add(orderDetail);
		return this;
	}
	
	public OrderBuilder addBooks(Map<Book, Integer> map) {
		for(Entry<Book, Integer> entry: map.entrySet()){
			addBook(entry.getKey(), entry.getValue());
		}
		return this;
		
		
	}
	
	public OrderBuilder deliveryDate(Date date) {
		this.product.setDeliveryDate(date);
		
		return this;
		
	}
	
	public OrderBuilder orderDate(Date date){
		this.product.setOrderDate(date);
		return this;
	}
	
	public OrderBuilder account(Account account) {
		this.product.setAccount(account);
		
		return this;
		
		
	}

	@Override
	Order assembleProduct() {
		for(OrderDetail orderDetail:this.orderDetails){
			this.product.addOrderDetail(orderDetail);
			
		}
		return  this.product;
	}
	
}
