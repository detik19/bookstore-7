package net.ruangtedy.java.spring.bookstore.web.controller;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import net.ruangtedy.java.spring.bookstore.domain.Account;
import net.ruangtedy.java.spring.bookstore.domain.Cart;
import net.ruangtedy.java.spring.bookstore.domain.Order;
import net.ruangtedy.java.spring.bookstore.service.BookstoreService;
import net.ruangtedy.java.spring.bookstore.validation.OrderValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes(types={Order.class})
@RequestMapping("/cart/checkout")
public class CheckoutController {
	private final Logger logger= LoggerFactory.getLogger(CheckoutController.class);
	
	@Autowired
	private Cart cart;
	
	@Autowired
	private BookstoreService bookstoreService;
	
	@ModelAttribute("countries")
	public Map<String, String> countries(Locale currentLocale) {
		Map<String, String> countries=new TreeMap<String, String>();
		for (Locale locale:Locale.getAvailableLocales()){
			countries.put(locale.getCountry(), locale.getDisplayCountry(currentLocale));
		}
		return countries;
	}
	/**
	 * The first method called on the controller when we click checkout is the show method, it takes our
	 * cart and uses the Account stored in the session to create an order and add that to the model. The order is 
	 * stored in the session in between requests; this due to the use of SessionAttributes. When this is done,
	 * the checkout page is rendered
	 * 
	 * @param session
	 * @param model
	 */
	@RequestMapping(method=RequestMethod.GET)
	public void show(HttpSession session, Model model){
		Account account=(Account) session.getAttribute(LoginController.ACCOUNT_ATTRIBUTE);
		Order order=this.bookstoreService.createOrder(this.cart, account);
		model.addAttribute(order);
	}
	/**
	 * When the Order button is pressed, the order is submitted and then validated by the com.apress.prospringmvc.bookstore.validation.OrderValidator. 
	 * In any errors occur, the page is redisplayed and error messages are shown to the customer. 
	 * The interesting part occurs when there are no errors. First, the order is stored in the database.
	 * When we are finished with the order, we need to remove it from the session, which we accomplish by 
	 * calling the setComplete method on the org.springframework.web.bind.support.SessionStatus object (see the Chapter 5 section, “Supported Method Argument Types”). 
	 * Finally, before redirecting to the index page again, we need to clear the shopping cart.
	 *  We do this so that the customer can add new books to the cart. 
	 *  
	 *  Because we cannot simply replace the session-scoped object, we need to call a method to clear it. 
	 *  If we were to replace the cart with a fresh instance we would basically destroy the scoped proxy object.
	 * 
	 */
	@RequestMapping(method=RequestMethod.POST, params="order")
	public String checkout(SessionStatus status, @Validated @ModelAttribute Order order, BindingResult errors) {
		if(errors.hasErrors()){
			return "cart/checkout";
		}
		else{
			this.bookstoreService.store(order);
			status.setComplete();
			this.cart.clear();
			return "redirect:/index.htm";
		}
	

	}
	
	@RequestMapping(method=RequestMethod.POST, params="update")
	public String update(@ModelAttribute Order order){
		order.updateOrderDetails();
		return "cart/checkout";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setValidator(new OrderValidator());
		
	}
	
}
