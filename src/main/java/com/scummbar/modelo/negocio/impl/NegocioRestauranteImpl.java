package com.scummbar.modelo.negocio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scummbar.dao.ReservaDAO;
import com.scummbar.dao.RestauranteDAO;
import com.scummbar.dao.TurnoDAO;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;
import com.scummbar.modelo.negocio.NegocioRestaurante;

@Service
@Transactional
public class NegocioRestauranteImpl implements NegocioRestaurante {

	@Autowired
	private RestauranteDAO restauranteDAO;
	@Autowired
	private TurnoDAO turnoDAO;
	@Autowired
	private ReservaDAO reservaDAO;

	@Override
	public List<Restaurante> getRestaurantes() {
		return restauranteDAO.getRestaurantes();
	}

	@Override
	public List<Turno> getTurnos() {
		return turnoDAO.getTurnos();
	}

	@Override
	public void addRestaurante(Restaurante restaurante) {
		restauranteDAO.addRestaurante(restaurante);
	}

	@Override
	public void updateRestaurante(Restaurante restaurante) {
		restauranteDAO.updateRestaurante(restaurante);
	}

	@Override
	public Restaurante getRestaurante(long id) {
		return restauranteDAO.getRestaurante(id);
	}

	@Override
	public void deleteRestaurante(long id) {
		restauranteDAO.deleteRestaurante(id);

	}

	@Override
	public Reserva reserva(Restaurante restaurante, Reserva reserva) {
		reservaDAO.addReserva(reserva);
		return reserva;
	}

	public void cancelar(Restaurante restaurante, Reserva reserva) {
		String code = "";
		reservaDAO.deleteReserva(code);

	}

}
