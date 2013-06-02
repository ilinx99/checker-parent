package com.karus.services.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.google.common.base.Preconditions;
import com.google.gwt.thirdparty.guava.common.collect.Lists;

public class UserBuilder {
	private User user;
	private Login login;
	
	public UserBuilder setLogin(Login login) {
		this.login = login;
		return this;
	}
	
	public User build(){
		Preconditions.checkNotNull(login, "Login cannot be null");
		String username = login.getName();
	    String password = login.getPass();
	   
	    Collection<GrantedAuthority> authorities = Lists.newArrayList();
	    for (Role role : login.getRoles()){
	    	SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
	    	authorities.add(authority);
	    }
		
	    user = new User(username, password, authorities);
	    
	    return user;
	}

}
