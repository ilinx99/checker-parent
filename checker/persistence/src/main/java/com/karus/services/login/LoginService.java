package com.karus.services.login;

import javax.jws.WebService;

import com.karus.services.login.dto.LoginDto;


@WebService
public interface LoginService {
	LoginDto authenticateUser(String userName, String rawPass);
}
