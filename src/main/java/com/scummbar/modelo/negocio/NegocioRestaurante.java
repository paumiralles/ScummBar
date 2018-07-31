package com.scummbar.modelo.negocio;

// Este negocio gestiona todos los metodos relacionados con los restaurantes
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

	public List<Turno> getTurnos();

	public Object reserva(Restaurante restaurante, Reserva reserva);

}