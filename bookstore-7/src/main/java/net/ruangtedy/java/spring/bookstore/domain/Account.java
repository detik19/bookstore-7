package net.ruangtedy.java.spring.bookstore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
* A account resembles an authenticated user of our system. A account is able to submit orders. A account is identified
* by his or her username. When authenticating the user supplies its username and password. Besides identification
* information we also store basic legal information such as address, firstname, lastname and email address.
* 
* @author Marten Deinum
* @author Koen Serneels
*
*/
@Entity
//@javax.persistence.Table(name="Account")
public class Account implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1333184476907999215L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	
	private String firstName;
	private String lastName;
	
	private Date dateOfBirth;
	
	@Embedded
	@Valid
	private Address address = new Address();
	
	@NotEmpty
	@Email
	private String emailAddress;
	
	@NotEmpty
	private String username;

	@NotEmpty
	private String password;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Role> roles = new ArrayList<Role>();

	
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getId() {
		return id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	 
	 
}
