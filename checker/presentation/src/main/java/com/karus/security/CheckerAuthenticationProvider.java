package com.karus.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.karus.services.login.LoginService;
import com.karus.services.login.dto.LoginDto;

public class CheckerAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getPrincipal().toString();
		String rawPass = authentication.getCredentials().toString();
		UsernamePasswordAuthenticationToken token = null;

		LoginDto loginDto = loginService.authenticateUser(userName, rawPass);

		if (loginDto != null){
			token = createAuthToken(authentication, loginDto);
		}

		return token;
	}

	
	private UsernamePasswordAuthenticationToken createAuthToken(Authentication authentication, LoginDto loginDto) {
		UsernamePasswordAuthenticationToken token;
		Collection<GrantedAuthority> authorities = Lists.newArrayList();
		for (String role : loginDto.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		
		token = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),
				authorities);
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
