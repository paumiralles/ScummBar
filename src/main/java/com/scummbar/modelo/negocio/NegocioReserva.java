package com.scummbar.modelo.negocio;

//Este negocio gestiona todos los metodos relacionados con los reservas

import java.util.Date;
import java.util.List;

import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.exceptions.CancelacionException;

public interface NegocioReserva {

	public void addReserva(Reserva reserva);

	public void updateReserva(Reserva reserva);

	public Reserva getReserva(long id);

	public void deleteReserva(String code) throws CancelacionException;

	public List<Reserva> getReservas();

	public String getLocalizador();

	public Mesa getMesaLibre(Long restauranteId, Date dia, Long turnoId, Integer personas);

}
