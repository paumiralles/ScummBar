package com.scummbar.dao;

import java.util.List;

import com.scummbar.modelo.entities.Mesa;

public interface MesaDAO {

	public void addMesa(Mesa mesa);

	public void updateMesa(Mesa mesa);

	public Mesa getMesa(long id);

	public void deleteMesa(long id);

	public List<Mesa> getMesas();

	public List<Mesa> getMesasRestaurante(long id);

	public List<Mesa> getMesasOcupadasRestaurante(long id);

//	public List<Mesa> getMesasLibres(List a, List b);

}
