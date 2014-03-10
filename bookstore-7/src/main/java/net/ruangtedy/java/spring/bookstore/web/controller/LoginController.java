package net.ruangtedy.java.spring.bookstore.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.ruangtedy.java.spring.bookstore.domain.Account;
import net.ruangtedy.java.spring.bookstore.service.AccountService;
import net.ruangtedy.java.spring.bookstore.service.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	public static final String ACCOUNT_ATTRIBUTE = "account";
    public static final String REQUESTED_URL = "REQUESTED_URL";

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void login(){
		//return "login";
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public String handleLogin(@RequestParam String username,
//							  @RequestParam String password,
//							  HttpServletRequest request,
//							  RedirectAttributes redirect,
//							  HttpSession session){
//		try {
//			Account account=this.accountService.login(username, password);
//			session.setAttribute(ACCOUNT_ATTRIBUTE, account);
//			return "redirect:/index.htm";
//		} catch (AuthenticationException e) {
//			//request.setAttribute("exception", e);
//			redirect.addFlashAttribute("exception", e);
//			//return "login";
//			return "redirect:/login";
//		}
//				
//	}
	
	   @RequestMapping(method = RequestMethod.POST)
	    public String handleLogin(@RequestParam String username, @RequestParam String password,
	            HttpSession session) throws AuthenticationException {
//	        try {
	            Account account = this.accountService.login(username, password);
	            session.setAttribute(ACCOUNT_ATTRIBUTE, account);
	            String url = (String) session.getAttribute(REQUESTED_URL);
	            session.removeAttribute(REQUESTED_URL); // Remove the attribute
	            if (StringUtils.hasText(url) && !url.contains("login")) { // Prevent loops for the login page.
	                return "redirect:" + url;
	            } else {
	                return "redirect:/index.htm";
	            }
	    }
//	@RequestMapping(method=RequestMethod.POST)
//	public String handleLogin(HttpServletRequest request, HttpSession session){
//		try {
//			String username = request.getParameter("username");
//			String password = request.getParameter("password");
//			Account account=this.accountService.login(username, password);
//			session.setAttribute(ACCOUNT_ATTRIBUTE, account);
//
//			return "redirect:/index.htm";
//		} catch (AuthenticationException e) {
//			request.setAttribute("exception", e);
//			return "login";
//			
//		}
//		
//	}
}
