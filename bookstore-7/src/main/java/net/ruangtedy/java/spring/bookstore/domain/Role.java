package net.ruangtedy.java.spring.bookstore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"role"}) })
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2192582079321824676L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String role;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Permission> permissions=new ArrayList<Permission>();
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	public Role(String role){
		this.role=role;
	}

	public Long getId() {
		return id;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermission(List<Permission> permissions) {
		this.permissions = permissions;
	}
	

}
