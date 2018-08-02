package com.scummbar.modelo.negocio.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scummbar.dao.ReservaDAO;
import com.scummbar.dao.RestauranteDAO;
import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.exceptions.CancelacionException;
import com.scummbar.modelo.negocio.NegocioReserva;

@Service
@Transactional
public class NegocioReservaImpl implements NegocioReserva {

	@Autowired
	private ReservaDAO reservaDAO;
	@Autowired
	private RestauranteDAO restauranteDAO;

	public NegocioReservaImpl(RestauranteDAO restauranteDAO2, ReservaDAO reservaDAO2) {
		this.reservaDAO = reservaDAO2;// TODO Auto-generated constructor stub
		this.restauranteDAO = restauranteDAO2;
	}

	@Override
	public void addReserva(Reserva reserva) {
		reservaDAO.addReserva(reserva);
	}

	@Override
	public void updateReserva(Reserva reserva) {
		reservaDAO.updateReserva(reserva);
	}

	@Override
	public Reserva getReserva(long id) {
		return reservaDAO.getReserva(id);
	}

	@Override
	public void deleteReserva(String code) throws CancelacionException {
		reservaDAO.deleteReserva(code);
	}

	@Override
	public List<Reserva> getReservas() {
		return reservaDAO.getReservas();
	}

	// Este metodo genera un localizador unico para cada reserva a partir del
	// momento en que se hace la reserva
	@Override
	public String getLocalizador() {
		// return DateTimeFormatter.ofPattern("ddMMyyyy").format(LocalDate.now());
		return DateTimeFormatter.ofPattern("ddMMyyHHmmss").format(LocalDateTime.now());
	}

	// Este metodo retorna la mesa libre optima para una reserva, si no hay mesa
	// disponible, retorna nulo.
	@Override
	public Mesa getMesaLibreParaReserva(Long restauranteId, Date dia, Long turnoId, Integer personas) {
		List<Mesa> listaMesas = restauranteDAO.getRestaurante(restauranteId).getMesas();
		List<Reserva> listaReservas = reservaDAO.getReservasByRestaurantAndDayAndTurn(restauranteId, dia, turnoId);
		List<Mesa> mesaLibre = getMesasLibresDeRestaurante(listaMesas, listaReservas);
		return mesaLibre.stream().filter(m -> m.getCapacidad() >= personas).sorted().findFirst().orElse(null);
	}

	// Este método retorna una lista con las mesas disponibles de un restaurante
	// para un turno y dia concreto
	public List<Mesa> getMesasLibresDeRestaurante(List<Mesa> listaTodasMesas, List<Reserva> listaReservas) {
		List<Mesa> listaMesasLibres = new ArrayList<>();
		for (Mesa mesa : listaTodasMesas) {
			if (listaReservas.stream().noneMatch(k -> k.getMesa().equals(mesa))) {
				listaMesasLibres.add(mesa);
			}
		}
		return listaMesasLibres;
	}

}
