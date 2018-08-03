package com.scummbar.dao;

import java.util.Date;
import java.util.List;

import com.scummbar.modelo.entities.Mesa;

public interface MesaDAO {

	void addMesa(Mesa mesa);

	void updateMesa(Mesa mesa);

	Mesa get(long id);

	void deleteMesa(long id);

	List<Mesa> getMesas();

	Mesa getMesaLibre(long restauranteId, long turnoId, Date dia, int personas);

}
