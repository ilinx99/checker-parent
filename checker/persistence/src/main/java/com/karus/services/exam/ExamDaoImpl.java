package com.karus.services.exam;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExamDaoImpl implements ExamDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Exam exam) {
		 getSession().save(exam);
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Exam> getExams() {
		List<Exam> exams = getSession().createQuery("from Exam").setCacheable(true).list();
		return exams;
	}

	@Override
	public Exam findExamByName(String examName) {
		return (Exam) getExamCriteria().add(Restrictions.eq("name", examName)).setCacheable(true).uniqueResult();
	}
	
	public Criteria getExamCriteria(){
		return getSession().createCriteria(Exam.class);
	}
}
