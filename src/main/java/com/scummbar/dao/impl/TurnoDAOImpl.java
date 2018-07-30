package com.scummbar.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scummbar.dao.TurnoDAO;
import com.scummbar.modelo.entities.Turno;

@Repository

public class TurnoDAOImpl implements TurnoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addTurno(Turno turno) {
		getCurrentSession().save(turno);
	}

	public void updateTurno(Turno turno) {
		Turno turnoToUpdate = getTurno(turno.getId());
		turnoToUpdate.setDescripcion(turno.getDescripcion());
		getCurrentSession().update(turnoToUpdate);

	}

	public Turno getTurno(long id) {
		Turno turno = (Turno) getCurrentSession().get(Turno.class, id);
		return turno;
	}

	public void deleteTurno(long id) {
		Turno turno = getTurno(id);
		if (turno != null)
			getCurrentSession().delete(turno);
	}

	@SuppressWarnings("unchecked")
	public List<Turno> getTurnos() {
		return getCurrentSession().createQuery("from Turno").list();
	}

}
