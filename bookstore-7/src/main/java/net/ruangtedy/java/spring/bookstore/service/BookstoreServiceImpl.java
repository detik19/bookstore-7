package net.ruangtedy.java.spring.bookstore.service;

import java.util.Date;
import java.util.List;








import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ruangtedy.java.spring.bookstore.domain.Account;
import net.ruangtedy.java.spring.bookstore.domain.Book;
import net.ruangtedy.java.spring.bookstore.domain.BookSearchCriteria;
import net.ruangtedy.java.spring.bookstore.domain.Cart;
import net.ruangtedy.java.spring.bookstore.domain.Category;
import net.ruangtedy.java.spring.bookstore.domain.Order;
import net.ruangtedy.java.spring.bookstore.domain.OrderDetail;
import net.ruangtedy.java.spring.bookstore.repository.BookRepository;
import net.ruangtedy.java.spring.bookstore.repository.CategoryRepository;
import net.ruangtedy.java.spring.bookstore.repository.OrderRepository;
/**
 * @see BookstoreService
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
@Service("bookstoreService")
@Transactional(readOnly=true)
public class BookstoreServiceImpl implements BookstoreService {

	private static final int RANDOM_BOOKS=2;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Book> findBooksByCategory(Category category) {
	
		return this.bookRepository.findByCategory(category);
	}

	@Override
	public Book findBook(long id) {
		
		return this.bookRepository.findById(id);
	}

	@Override
	public Order findOrder(long id) {
		
		return this.orderRepository.findById(id);
	}
	

	@Override
	public List<Book> findRandomBooks() {
		
		return this.bookRepository.findRandom(RANDOM_BOOKS);
	}

	@Override
	public List<Order> findOrdersForAccount(Account account) {
		
		return this.orderRepository.findByAccount(account);
	}

	@Override
    @Transactional(readOnly = false)
	public Order store(Order order) {
        return this.orderRepository.save(order);

	}

	@Override
	public List<Book> findBooks(BookSearchCriteria bookSearchCriteria) {
        return this.bookRepository.findBooks(bookSearchCriteria);

	}

	@Override
    @Transactional(readOnly = false)
	public Order createOrder(Cart cart, Account customer) {
		Order order=new Order(customer);
		for(Entry<Book, Integer> line:cart.getBooks().entrySet()){
			order.addOrderDetail(new OrderDetail(line.getKey(), line.getValue()));
			
		}
		order.setOrderDate(new Date());
		
		return order;
	}

	@Override
	public List<Category> findAllCategories() {
		
		return this.categoryRepository.findAll();
		
	}

	@Override
	@Transactional(readOnly=false)
	public void addBook(Book book) {
		this.bookRepository.storeBook(book);
		

	}

}
