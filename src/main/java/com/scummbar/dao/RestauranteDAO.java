package com.scummbar.dao;

import java.util.List;

import com.scummbar.modelo.entities.Restaurante;

public interface RestauranteDAO {

	public void addRestaurante(Restaurante restaurante);

	public void updateRestaurante(Restaurante restaurante);

	public Restaurante getRestaurante(long id);

	public void deleteRestaurante(long id);

	public List<Restaurante> getRestaurantes();

}
