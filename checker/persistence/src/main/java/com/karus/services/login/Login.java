package com.karus.services.login;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = Login.TABLE_NAME)
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME = "login";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";

	@Id
	@Column(name = COLUMN_ID)
	@GeneratedValue
	private Integer id;

	@Column(name = COLUMN_NAME)
	private String name;

	@Column(name = "pass")
	private String pass;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "login")
	private Set<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
