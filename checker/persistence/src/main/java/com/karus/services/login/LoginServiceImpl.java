package com.karus.services.login;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karus.services.login.dto.LoginConverter;
import com.karus.services.login.dto.LoginDto;


@Service("loginServiceImpl")
@WebService(endpointInterface = "com.karus.services.login.LoginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private EnryptedPasswordMatcher passwordMatcher;
	
	@Autowired
	private LoginConverter loginConverter;
	
	@Override
	@Transactional(readOnly = true)
	public LoginDto authenticateUser(String userName, String rawPass) {
		Login login = loginDao.findLoginByName(userName);
		if (login == null) {
			return null;
		}
		
		if (passwordMatcher.isPassValid(login, rawPass)){
			return loginConverter.toDto(login);
		}
		
		return null;
	}
	
	@Transactional
	public void updatePassword(String name, String pass) {
		loginDao.updatePassword(name, pass);
	}

	@Transactional
	public void encryptUsers() {
		//loginDao.getLogins();
		//TODO: ecrypt users
	}
}
