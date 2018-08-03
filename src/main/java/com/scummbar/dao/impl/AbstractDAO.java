package com.scummbar.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDAO<E> {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public abstract E get(long id);

//	public abstract List<E> getList();
//
//	public abstract void insert(E entity);
//
//	public abstract void update(E entity);
//
//	public abstract void delete(long id);

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
