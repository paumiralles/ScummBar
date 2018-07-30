package com.scummbar.modelo.negocio.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scummbar.dao.MesaDAO;
import com.scummbar.dao.ReservaDAO;
import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.negocio.NegocioReserva;

@Service
@Transactional
public class NegocioReservaImpl implements NegocioReserva {

	@Autowired
	private ReservaDAO reservaDAO;
	@Autowired
	private MesaDAO mesaDAO;

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
	public void deleteReserva(String code) {
		reservaDAO.deleteReserva(code);

	}

	@Override
	public List<Reserva> getReservas() {
		return reservaDAO.getReservas();
	}

	@Override
	public String getLocalizador() {
		// return DateTimeFormatter.ofPattern("ddMMyyyy").format(LocalDate.now());
		return DateTimeFormatter.ofPattern("ddMMyyHHmmss").format(LocalDateTime.now());
	}

	@Override
	public Mesa getMesasLibres(Long restauranteId, Date dia, Long turnoId, Integer personas) {
		List<Mesa> listaMesas = mesaDAO.getMesasRestaurante(restauranteId);
		List<Mesa> listaOcupadas = mesaDAO.getMesasOcupadasRestaurante(restauranteId);
		return null;
	}

}
