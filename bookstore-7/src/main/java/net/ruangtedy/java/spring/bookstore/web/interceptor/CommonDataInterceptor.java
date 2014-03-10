package net.ruangtedy.java.spring.bookstore.web.interceptor;

import net.ruangtedy.java.spring.bookstore.service.BookstoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
/**
 * {@code WebRequestInterceptor} implementation to add common data (random books) to the model.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */

public class CommonDataInterceptor implements WebRequestInterceptor {

	@Autowired
	private BookstoreService bookstoreService;
	
	
	@Override
	public void afterCompletion(WebRequest arg0, Exception arg1)
			throws Exception {
		

	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		if(model!=null){
			model.addAttribute("randomBooks", this.bookstoreService.findRandomBooks());
		}

	}

	@Override
	public void preHandle(WebRequest arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
