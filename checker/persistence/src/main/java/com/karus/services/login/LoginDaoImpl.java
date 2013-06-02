package com.karus.services.login;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LoginDaoImpl implements LoginDao {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Login> getLogins() {
		List<Login> logins = getSession().createQuery("from Login").list();

		return logins;
	}
	
	public Login findLoginByName(String name) {
		Login login = (Login) getLoginCriteria().add(Restrictions.eq(Login.COLUMN_NAME, name)).uniqueResult();

		return login;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Criteria getLoginCriteria(){
		return getSession().createCriteria(Login.class);
	}

	@Override
	public void updatePassword(String name, String pass) {
		Query query = getSession().createQuery("update Login set pass = :pass where name = :name");
		query.setParameter("pass", pass);
		query.setParameter("name", name);
		
		query.executeUpdate();
	}

}
