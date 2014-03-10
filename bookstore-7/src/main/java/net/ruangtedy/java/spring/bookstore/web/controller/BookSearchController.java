package net.ruangtedy.java.spring.bookstore.web.controller;

import java.util.Collection;
import java.util.List;



import net.ruangtedy.java.spring.bookstore.domain.Book;
import net.ruangtedy.java.spring.bookstore.domain.BookSearchCriteria;
import net.ruangtedy.java.spring.bookstore.domain.Category;
import net.ruangtedy.java.spring.bookstore.service.BookstoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Controller to handle book search requests.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
@Controller
public class BookSearchController {

	@Autowired
	private BookstoreService bookstoreService;
//kalo POST ini sepertinya harus disertakan	
//	@ModelAttribute
//	public BookSearchCriteria criteria(){
//		System.out.println(("crireria"));
//		return new BookSearchCriteria();
//	}
	
	/**
	 * use the ModelAttribute annotation on methods to create an object
	 * to be used in form or to get reference data(data that is needed to
	 * render the form like a list of categories)
	 * 
	 * Dipanggil sebelum Request GET
	 * @return
	 */
	 @ModelAttribute("categories")//---->send to
	public List<Category> getCategories(){
		return bookstoreService.findAllCategories();
		
		
	}

	/**
     * This method searches our database for books based on the given {@link BookSearchCriteria}. 
     * Only books matching the criteria are returned.
     * 
     * @param criteria the criteria used for searching
     * @return the found books
     * 
     * @see com.apress.prospringmvc.bookstore.repository.BookRepository#findBooks(BookSearchCriteria)
     */
    @RequestMapping(value = "/book/search", method = { RequestMethod.GET })
    public Collection<Book> listok(@ModelAttribute("bookSearchCriteria") BookSearchCriteria criteria) {
        return this.bookstoreService.findBooks(criteria);
    }
}
