package net.ruangtedy.java.spring.bookstore.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesViewResolver;

@Configuration
public class ViewConfiguration {
	 @Bean
	    public TilesConfigurer tilesConfigurer() {
	        return new TilesConfigurer();
	    }

	    @Bean
	    public TilesViewResolver tilesViewResolver() {
	        TilesViewResolver tilesViewResolver = new TilesViewResolver();
	        tilesViewResolver.setOrder(2);
	        return tilesViewResolver;
	    }
}
