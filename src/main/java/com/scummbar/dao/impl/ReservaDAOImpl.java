package com.scummbar.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scummbar.dao.ReservaDAO;
import com.scummbar.modelo.entities.Reserva;

@Repository

public class ReservaDAOImpl implements ReservaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addReserva(Reserva reserva) {
		getCurrentSession().save(reserva);
	}

	public void updateReserva(Reserva reserva) {
		Reserva reservaToUpdate = getReserva(reserva.getId());
		reservaToUpdate.setDia(reserva.getDia());
		reservaToUpdate.setPersonas(reserva.getPersonas());
		reservaToUpdate.setLocalizador(reserva.getLocalizador());
		reservaToUpdate.setTurno(reserva.getTurno());
		getCurrentSession().update(reservaToUpdate);

	}

	public Reserva getReserva(long id) {
		Reserva reserva = (Reserva) getCurrentSession().get(Reserva.class, id);
		return reserva;
	}

	public void deleteReserva(long id) {
		Reserva reserva = getReserva(id);
		if (reserva != null)
			getCurrentSession().delete(reserva);
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservas() {
		return getCurrentSession().createQuery("from Reserva").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteReserva(String code) {

		Query query = getCurrentSession().createQuery("from Reserva where localizador = :code ");
		query.setParameter("code", code);
		List<Reserva> list = query.list();
		if (!list.isEmpty()) {
			getCurrentSession().delete(list.get(0));
		} else {

		}

	}

	// Este método retorna una lista con las reservas de un restaurante, de un dia y
	// de un turno
	@Override
	public List<Reserva> getReservasByRestaurantAndDayAndTurn(Long restauranteId, Date dia, Long turnoId) {
		Query query = getCurrentSession()
				.createQuery("from Reserva where (restaurante_id = :rest and turno_id = :turno and dia = :date)");
		query.setParameter("rest", restauranteId);
		query.setParameter("date", dia);
		query.setParameter("turno", turnoId);
		List<Reserva> lista = query.list();
		return lista;
	}
}
