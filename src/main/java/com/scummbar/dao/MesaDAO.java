package com.scummbar.dao;

import java.util.List;

import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;

public interface MesaDAO {

	void addMesa(Mesa mesa);

	void updateMesa(Mesa mesa);

	Mesa getMesa(long id);

	void deleteMesa(long id);

	List<Mesa> getMesas();

	Mesa getMesaLibre(List<Mesa> listaTodasMesas, List<Reserva> listaReservas);

}
