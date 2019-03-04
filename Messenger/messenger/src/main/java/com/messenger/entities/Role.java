package com.messenger.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Simple JavaBean object that represents Roles of User
 * 
 * @author ANTON_WORK
 */

@Entity
@Table(name = "role")
public class Role extends BasicFields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6264309387155772030L;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "role")
	private Set<User> users;

	public Role() {
		super();
		users = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
