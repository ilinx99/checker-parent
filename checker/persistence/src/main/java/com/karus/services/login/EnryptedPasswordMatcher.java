package com.karus.services.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class EnryptedPasswordMatcher {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SaltSource saltSource;
	
	
	public boolean isPassValid(Login login, String rawPass) {
		UserBuilder userBuilder = new UserBuilder().setLogin(login);
		User user = userBuilder.build();
		Object salt = saltSource.getSalt(user);
		
		boolean isPasswordValid = passwordEncoder.isPasswordValid(user.getPassword(), rawPass, salt);
		
		return isPasswordValid;
	}

}
