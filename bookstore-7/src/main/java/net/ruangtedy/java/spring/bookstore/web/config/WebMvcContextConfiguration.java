package net.ruangtedy.java.spring.bookstore.web.config;


import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import net.ruangtedy.java.spring.bookstore.converter.StringToEntityConverter;
import net.ruangtedy.java.spring.bookstore.domain.Cart;
import net.ruangtedy.java.spring.bookstore.domain.Category;
import net.ruangtedy.java.spring.bookstore.web.interceptor.CommonDataInterceptor;
import net.ruangtedy.java.spring.bookstore.web.interceptor.SecurityHandlerInterceptor;
import net.ruangtedy.java.spring.bookstore.web.method.support.SessionAttributeProcessor;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"net.ruangtedy.java.spring.bookstore.web"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * Cara ini digunakan untuk view Controller, memetakan request yang masuk (index.htm) dan kemudian setViewName
	 * menuju jsp untuk dirender
	 * @return
	 */
	@Bean(name="/index.htm")//<-- request yang masuk
	public Controller index(){
		ParameterizableViewController index;
		index=new ParameterizableViewController();
		index.setViewName("index");//--> JSP yang akan dirender
		return index;
	}

	
	   @Override
	    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**/*").addResourceLocations("classpath:/META-INF/web-resources/");
	    }

		/**
		 * Cara kedua lebih mudah untuk view Controller dengan mengoverride
		 *	method addViewControllers
		 */
//	    @Override
//	    public void addViewControllers(final ViewControllerRegistry registry) {
//	        registry.addViewController("/index.htm").setViewName("index");
//	    }

	    @Override
	    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }

	    /**
	     * we configure the interceptor and use the addInterceptor method to add it to the registry. 
	     * The framework will take care of the additional details for registering the interceptors
	     * with the configured handler mappings
	     */
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	    	
	    	registry.addInterceptor(localeChangeInterceptor());
	        registry.addWebRequestInterceptor(commonDataInterceptor());//menampilkan random books
	        registry.addInterceptor(new SecurityHandlerInterceptor()).addPathPatterns("/customer/account","/cart/checkout");
	        
	    }

	    //-- Start Locale Support (I18N) --//

	    /**
	     * The {@link LocaleChangeInterceptor} allows for the locale to be changed. It provides a <code>paramName</code> property which sets 
	     * the request parameter to check for changing the language, the default is <code>locale</code>.
	     * @return the {@link LocaleChangeInterceptor}
	     */
	    @Bean
	    public HandlerInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	        localeChangeInterceptor.setParamName("lang");
	        return localeChangeInterceptor;
	    }

	    /**
	     * The {@link LocaleResolver} implementation to use. Specifies where to store the current selected locale.
	     * 
	     * @return the {@link LocaleResolver}
	     */
	    @Bean
	    public LocaleResolver localeResolver() {
	        return new CookieLocaleResolver();
	    }

	    /**
	     * To resolve message codes to actual messages we need a {@link MessageSource} implementation. The default 
	     * implementations use a {@link java.util.ResourceBundle} to parse the property files with the messages in it.
	     * @return the {@link MessageSource}
	     */
	    @Bean
	    public MessageSource messageSource() {
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasename("classpath:/messages");
	        messageSource.setUseCodeAsDefaultMessage(true);
	        return messageSource;
	    }

	    //-- End Locale Support (I18N) --//

	    @Override
	    public void addFormatters(FormatterRegistry registry) {
	        registry.addConverter(categoryConverter());
	        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
	    }

	    @Bean
	    public StringToEntityConverter categoryConverter() {
	        return new StringToEntityConverter(Category.class);
	    }
	    
	    // BAB 6
	    /**
	     * Menggunakan Session Scope
	     * Kita ingin menggunakan class berbasis proxi 
	     * 
	     * {@code com.apress.prospringmvc.bookstore.domain.Cart} tidak mengimplementasikan
	     * sebuah interface, sehingga kita membutuhkan class brbasis proxi. Kita akan injectkan
	     *
 	     * @return
	     */
	    @Bean
	    @Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	    public Cart cart() {
			return new Cart();
		}
	    
	    @Bean
	    public WebRequestInterceptor commonDataInterceptor(){
	    	return new CommonDataInterceptor();
	    }
	    
	    @Bean
	    public SessionAttributeProcessor sessionAttributeProcessor() {
	        return new SessionAttributeProcessor();
	    }
	    
	    @Override
	    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	        argumentResolvers.add(sessionAttributeProcessor());
	    }

	    @Override
	    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
	        returnValueHandlers.add(sessionAttributeProcessor());
	    }
	    
	    
	    
	    /**
	     * Pengaturan Exception Handler
	     */
	    @Override
		public void configureHandlerExceptionResolvers(
				List<HandlerExceptionResolver> exceptionResolvers) {
	    	exceptionResolvers.add(simpleMappingExceptionResolver());
		}


		@Bean
	    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
	        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
	        Properties mappings = new Properties();
	        mappings.setProperty("AuthenticationException", "login");

	        Properties statusCodes = new Properties();
	        mappings.setProperty("login", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));

	        exceptionResolver.setExceptionMappings(mappings);
	        exceptionResolver.setStatusCodes(statusCodes);
	        return exceptionResolver;
	    }
	    	
	
}
