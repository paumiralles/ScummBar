package com.scummbar.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scummbar.dao.RestauranteDAO;
import com.scummbar.modelo.entities.Restaurante;

@Repository
public class RestauranteDAOImpl implements RestauranteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addRestaurante(Restaurante restaurante) {
		getCurrentSession().save(restaurante);
	}

	public void updateRestaurante(Restaurante restaurante) {
		Restaurante restauranteToUpdate = getRestaurante(restaurante.getId());
		restauranteToUpdate.setNombre(restaurante.getNombre());
		restauranteToUpdate.setDireccion(restaurante.getDireccion());
		restauranteToUpdate.setDescripcion(restaurante.getDescripcion());
		restauranteToUpdate.setReservas(restaurante.getReservas());
		restauranteToUpdate.setMesas(restaurante.getMesas());
		getCurrentSession().update(restauranteToUpdate);

	}

	public Restaurante getRestaurante(long id) {
		Restaurante restaurante = (Restaurante) getCurrentSession().get(Restaurante.class, id);
		return restaurante;
	}

	public void deleteRestaurante(long id) {
		Restaurante restaurante = getRestaurante(id);
		if (restaurante != null)
			getCurrentSession().delete(restaurante);
	}

	@SuppressWarnings("unchecked")
	public List<Restaurante> getRestaurantes() {
		return getCurrentSession().createQuery("from Restaurante").list();
	}

}
