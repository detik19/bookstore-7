package net.ruangtedy.java.spring.bookstore.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import net.ruangtedy.java.spring.bookstore.config.InfrastructureContextConfiguration;
import net.ruangtedy.java.spring.bookstore.config.TestDataContextConfiguration;
import net.ruangtedy.java.spring.bookstore.web.config.WebMvcContextConfiguration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
/**
* {@link WebApplicationInitializer} that will be called by Spring's {@code SpringServletContainerInitializer} as part
* of the JEE {@code ServletContainerInitializer} pattern. This class will be called on application startup and will
* configure our JEE and Spring configuration.
* <p/>
* 
* It will first initializes our {@code AnnotationConfigWebApplicationContext} with the common {@link Configuration}
* classes: {@code InfrastructureContextConfiguration} and {@code TestDataContextConfiguration} using a typical JEE
* {@code ContextLoaderListener}.
* <p/>
* 
* Next it creates a {@link DispatcherServlet}, being a normal JEE Servlet which will create on its turn a child
* {@code AnnotationConfigWebApplicationContext} configured with the Spring MVC {@code Configuration} classes
* {@code WebMvcContextConfiguration} and {@code WebflowContextConfiguration}. This Servlet will be registered using
* JEE's programmatical API support.
* <p/>
* 
* Note: the {@code OpenEntityManagerInViewFilter} is only enabled for pages served soley via Spring MVC. For pages
* being served via WebFlow we configured WebFlow to use the JpaFlowExecutionListener.
* 
* @author Marten Deinum
* @author Koen Serneels
* 
*/
public class BookstoreWebApplicationInitializer implements
		WebApplicationInitializer {
	private static final String DISPACTHER_SERVLET_NAME="dispatcher";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
        registerListener(servletContext);

		registerDispatcherServlet(servletContext);
		
		registerHiddenHttpMethodFilter(servletContext);
		

		
	}
	private void registerListener(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext;
        rootContext = createContext(InfrastructureContextConfiguration.class, TestDataContextConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

    }
	/**
	 * configure the HiddenHttpMethodFilter for our web application
	 * @param servletContext
	 */
	private void registerHiddenHttpMethodFilter(ServletContext servletContext){
		FilterRegistration.Dynamic registration;
		registration=servletContext.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
		registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, DISPACTHER_SERVLET_NAME);
	}
	/**
	 * Web Application Context
	 * jika *.htm -> tdk ada mslh pada resource, tp jika mapping / mka perlu modifikasi pd resources
	 * @param servletContext
	 */
	private void registerDispatcherServlet(ServletContext servletContext){
        AnnotationConfigWebApplicationContext dispatcherContext = createContext(WebMvcContextConfiguration.class);

        DispatcherServlet dispatcherServlet=new DispatcherServlet(dispatcherContext);
        ServletRegistration.Dynamic dispatcher=servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");//<-- semua request di mapping ke controller
       //dispatcher.addMapping("*.htm");//<-- Hanya request berekstensi *.htm yang dimapping
	}
	 /**
     * Factory method to create {@link AnnotationConfigWebApplicationContext} instances. 
     * @param annotatedClasses
     * @return
     */
    private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(annotatedClasses);
        return context;
    }

}
