package com.scummbar.modelo.negocio;

import java.util.List;

import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;

public interface INegocioRestaurante {

	public List<Restaurante> getRestaurantes();

	public List<Turno> getTurnos();

	public Reserva reservar(Restaurante restaurante, Reserva reserva);

	public Object cancelarReserva(Restaurante restaurante, Reserva reserva);

}