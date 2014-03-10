package net.ruangtedy.java.spring.bookstore.web.controller;

import net.ruangtedy.java.spring.bookstore.domain.Book;
import net.ruangtedy.java.spring.bookstore.service.BookstoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookDetailController {

	@Autowired
	private BookstoreService bookstoreService;
	
	@RequestMapping(value="book/detail/{bookId}")
	public String details(@PathVariable("bookId") long bookId, Model model){
		Book book=this.bookstoreService.findBook(bookId);
		model.addAttribute(book);
		return "book/detail";
		
	}
}
