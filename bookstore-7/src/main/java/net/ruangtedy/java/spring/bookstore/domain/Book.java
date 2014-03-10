package net.ruangtedy.java.spring.bookstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;




import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(region="books", usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4026561491764204030L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
    private String description;
	private BigDecimal price;
	private Integer year;
	private String author;
	private String Isbn;
	
	@ManyToOne(optional=false)
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return Isbn;
	}

	public void setIsbn(String isbn) {
		Isbn = isbn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
        return new HashCodeBuilder().append(getAuthor()).append(getTitle()).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
	      if (this == object) {
	            return true;
	        }
	        if (object == null || getClass() != object.getClass()) {
	            return false;
	        }
	        Book other = (Book) object;

	        return new EqualsBuilder().append(this.getTitle(), other.getTitle())
	                .append(this.getAuthor(), other.getAuthor()).isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder builder=new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		builder.append("title", this.title);
		builder.append("author", this.author);
		builder.append("isbn", this.Isbn);
		return builder.build();
	}
	

}
