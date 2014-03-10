/**
 * 
 */
package net.ruangtedy.java.spring.bookstore.web.controller;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import net.ruangtedy.java.spring.bookstore.domain.Account;
import net.ruangtedy.java.spring.bookstore.repository.AccountRepository;
import net.ruangtedy.java.spring.bookstore.repository.OrderRepository;
import net.ruangtedy.java.spring.bookstore.web.method.support.SessionAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author tedy.saputro
 *
 */
@Controller
@RequestMapping("/customer/account")
@SessionAttributes(types=Account.class)
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@ModelAttribute("countries")
	public Map<String, String> countries(Locale currentLocale){
		Map<String, String> countries=new TreeMap<String, String>();
		for(Locale locale:Locale.getAvailableLocales()){
			countries.put(locale.getCountry(), locale.getDisplayCountry(currentLocale));
		}
		return countries;
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setDisallowedFields("id");
		binder.setRequiredFields("username","password","emailAddress");
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public String index(Model model,
            @SessionAttribute(value = LoginController.ACCOUNT_ATTRIBUTE, exposeAsModelAttribute = true) Account account) {
        model.addAttribute("orders", this.orderRepository.findByAccount(account));
        return "customer/account";
    }

	@RequestMapping(method={RequestMethod.POST,RequestMethod.PUT})
	public String update(@ModelAttribute Account account){
		  this.accountRepository.save(account);
	        return "redirect:/customer/account";
		
	}
}