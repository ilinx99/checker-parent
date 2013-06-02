package com.karus.services.login;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loginId", nullable = false, insertable = false, updatable = false)
	private Login login;

	@Column(name = "roleName")
	private String roleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
