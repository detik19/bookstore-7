package net.ruangtedy.java.spring.bookstore.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * A component which resembles the address of a {@link Account}
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
@Embeddable
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2966604779577325875L;
	
	@NotEmpty
	private String street;
    private String houseNumber;
    private String boxNumber;
    
    @NotEmpty
    private String city;
    private String postalCode;
    
    @NotEmpty
    private String country;
    
    public Address() {
		
	}
    
    public Address(Address source){
    	super();
    	this.street=source.street;
    	this.houseNumber=source.houseNumber;
    	this.boxNumber=source.boxNumber;
    	this.city=source.city;
    	this.postalCode=source.postalCode;
    	this.country=source.country;
    }
    
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getBoxNumber() {
		return boxNumber;
	}
	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

    
}
