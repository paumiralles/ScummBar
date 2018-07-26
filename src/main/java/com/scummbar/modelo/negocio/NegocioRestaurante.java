package com.scummbar.modelo.negocio;

import java.util.List;

import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;

public interface NegocioRestaurante {
	public void addRestaurante(Restaurante restaurante);

	public void updateRestaurante(Restaurante restaurante);

	public Restaurante getRestaurante(long id);

	public void deleteRestaurante(long id);

	public List<Restaurante> getRestaurantes();

	public Reserva reservar(Restaurante restaurante, Reserva reserva);

	public Object cancelarReserva(Restaurante restaurante, Reserva reserva);

	public List<Turno> getTurnos();

}