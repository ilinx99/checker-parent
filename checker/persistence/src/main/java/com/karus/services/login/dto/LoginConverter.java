package com.karus.services.login.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.karus.services.login.Login;
import com.karus.services.login.Role;

@Component
public class LoginConverter {
	public LoginDto toDto(Login login) {
		LoginDto dto = new LoginDto();
		BeanUtils.copyProperties(login, dto, new String[]{"roles"});
		
		Set<String> roles = new HashSet<String>();
		for (Role role : login.getRoles()){
			roles.add(role.getRoleName());
	    }
		dto.setRoles(roles);
		
		return dto;
	}
}
