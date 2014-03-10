package net.ruangtedy.java.spring.bookstore.web.controller;

import net.ruangtedy.java.spring.bookstore.domain.Book;
import net.ruangtedy.java.spring.bookstore.domain.Cart;
import net.ruangtedy.java.spring.bookstore.service.BookstoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CartController {
	private org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private Cart cart;
	
	@Autowired
	private BookstoreService bookstoreService;
	
	@RequestMapping("/cart/add/{bookId}")
	public String addToStringCart(@PathVariable("bookId") long bookId,
									@RequestHeader("referer") String referer){
	
		Book book=this.bookstoreService.findBook(bookId);
		this.cart.addBook(book);
		this.logger.info("Cart: {}", this.cart);
		return "redirect:"+referer;
		
		
	}
}
