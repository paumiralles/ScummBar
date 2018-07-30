package com.scummbar.dao;

import java.util.List;

import com.scummbar.modelo.entities.Reserva;

public interface ReservaDAO {

	public void addReserva(Reserva reserva);

	public void updateReserva(Reserva reserva);

	public Reserva getReserva(long id);

	public void deleteReserva(String code);

	public List<Reserva> getReservas();

}
