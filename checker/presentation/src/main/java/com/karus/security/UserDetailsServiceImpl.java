//package com.karus.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.karus.services.login.Login;
//import com.karus.services.login.LoginDao;
//import com.karus.services.login.UserBuilder;
//
//@Service("userDetailsService")
//public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	private LoginDao loginDao;
//
//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Login login = loginDao.findLoginByName(username);
//		if (login == null) {
//			throw new UsernameNotFoundException("Cannot find user with name = " + username);
//		}
//		User user = new UserBuilder().setLogin(login).build();
//		
//		return user;
//	}
//
//}
