package net.ruangtedy.java.spring.bookstore.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(region="categories", usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4899309958399599490L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Basic(optional=false)
	private String name;
	
	public Category(){
		
	}
	
	public Category(String name){
		this.name=name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
