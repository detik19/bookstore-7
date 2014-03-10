package net.ruangtedy.java.spring.bookstore.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ruangtedy.java.spring.bookstore.domain.Account;
import net.ruangtedy.java.spring.bookstore.service.AuthenticationException;
import net.ruangtedy.java.spring.bookstore.web.controller.LoginController;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
/**
 * {@code HandlerInterceptor} to apply security to controllers.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Account account=(Account) WebUtils.getSessionAttribute(request, LoginController.ACCOUNT_ATTRIBUTE);
		if(account==null){
            //Retrieve and store the original URL.
			String url=request.getRequestURL().toString();
			WebUtils.setSessionAttribute(request, LoginController.REQUESTED_URL, url);
            throw new AuthenticationException("Authentication required.", "authentication.required");
		}
		return true;
	}

	
}
