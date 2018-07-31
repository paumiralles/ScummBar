package com.scummbar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scummbar.dao.MesaDAO;
import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;

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

	// Este método retorna una lista con las mesas disponibles de un restaurante
	// para un turno y dia concreto
	@Override
	public Mesa getMesaLibre(List<Mesa> listaTodasMesas, List<Reserva> listaReservas) {
		List<Mesa> listaMesasLibres = new ArrayList<>();
		for (Mesa mesa : listaTodasMesas) {
			if (listaReservas.stream().noneMatch(k -> k.getMesa().getId() == mesa.getId())) {
				listaMesasLibres.add(mesa);
			}
		}
		return listaMesasLibres.stream().sorted().findFirst().orElse(null);
	}

}
