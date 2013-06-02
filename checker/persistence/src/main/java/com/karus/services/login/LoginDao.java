package com.karus.services.login;

import java.util.List;


public interface LoginDao {
	List<Login> getLogins();
	Login findLoginByName(String name);
	void updatePassword(String name, String pass);
}
