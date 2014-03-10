package net.ruangtedy.java.spring.bookstore.domain.support;


import net.ruangtedy.java.spring.bookstore.domain.Category;

import org.springframework.stereotype.Component;
/**
 * Builds {@link Category} domain objects
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
@Component
public class CategoryBuilder extends EntityBuilder<Category> {

	@Override
	void initProduct() {
		// TODO Auto-generated method stub

	}

	@Override
	Category assembleProduct() {
		return this.product;
	}
	
	public CategoryBuilder name(String name) {
		this.product=new Category(name);
		return this;
		
	}

}
