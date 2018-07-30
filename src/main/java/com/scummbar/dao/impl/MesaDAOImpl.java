package com.scummbar.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scummbar.dao.MesaDAO;
import com.scummbar.modelo.entities.Mesa;

@Repository

public class MesaDAOImpl implements MesaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addMesa(Mesa mesa) {
		getCurrentSession().save(mesa);
	}

	public void updateMesa(Mesa mesa) {
		Mesa mesaToUpdate = getMesa(mesa.getId());
		mesaToUpdate.setNumero(mesa.getNumero());
		mesaToUpdate.setCapacidad(mesa.getCapacidad());
		getCurrentSession().update(mesaToUpdate);

	}

	public Mesa getMesa(long id) {
		Mesa mesa = (Mesa) getCurrentSession().get(Mesa.class, id);
		return mesa;
	}

	public void deleteMesa(long id) {
		Mesa mesa = getMesa(id);
		if (mesa != null)
			getCurrentSession().delete(mesa);
	}

	@SuppressWarnings("unchecked")
	public List<Mesa> getMesas() {
		return getCurrentSession().createQuery("from mesas").list();
	}

	// Este método retorna una lista con todas las mesas que tiene un restaurante
	@Override
	public List<Mesa> getMesasRestaurante(long id) {
		Query query = getCurrentSession().createQuery("from Mesa where localizador = :code ");
		query.setParameter("code", id);
		List<Mesa> lista = query.list();
		return lista;
	}

	// Este método retorna una lista con las mesas ocupadas de un restaurante
	@Override
	public List<Mesa> getMesasOcupadasRestaurante(long id) {
		Query query = getCurrentSession().createQuery("from Reserva where localizador = :code ");
		query.setParameter("code", id);
		List<Mesa> lista = query.list();
		return lista;
	}

//	// Este método retorna una lista con las mesas no ocupadas de un restaurante
//	@Override
//	public List<Mesa> getMesasLibres(List todas, List ocupadas) {
//		for (int i = 0; i < ocupadas.size(); i++) {
//			
//			return null;
//		}
//		return null;
//	}
}
