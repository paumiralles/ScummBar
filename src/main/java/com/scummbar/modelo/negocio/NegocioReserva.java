package com.scummbar.modelo.negocio;

import java.util.Date;
import java.util.List;

import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;

public interface NegocioReserva {

	public void addReserva(Reserva reserva);

	public void updateReserva(Reserva reserva);

	public Reserva getReserva(long id);

	public void deleteReserva(String code);

	public List<Reserva> getReservas();

	public String getLocalizador();

	public Mesa getMesasLibres(Long restauranteId, Date dia, Long turnoId, Integer personas);

}
