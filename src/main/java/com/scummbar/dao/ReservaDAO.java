package com.scummbar.dao;

import java.util.Date;
import java.util.List;

import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.exceptions.CancelacionException;

public interface ReservaDAO {

	public void addReserva(Reserva reserva);

	public void updateReserva(Reserva reserva);

	public Reserva getReserva(long id);

	public void deleteReserva(String code) throws CancelacionException;

	public List<Reserva> getReservas();

	public List<Reserva> getReservasByRestaurantAndDayAndTurn(Long restauranteId, Date dia, Long turnoId);

}
