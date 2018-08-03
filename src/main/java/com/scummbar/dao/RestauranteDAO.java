package com.scummbar.dao;

import java.util.List;

import com.scummbar.modelo.entities.Restaurante;

public interface RestauranteDAO {

	void addRestaurante(Restaurante restaurante);

	void updateRestaurante(Restaurante restaurante);

	Restaurante getRestaurante(long id);

	void deleteRestaurante(long id);

	List<Restaurante> getRestaurantes();

}
