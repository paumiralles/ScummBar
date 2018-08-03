package com.scummbar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.scummbar.dao.MesaDAO;
import com.scummbar.modelo.entities.Mesa;

@Repository

public class MesaDAOImpl extends AbstractDAO<Mesa> implements MesaDAO {

	private static final String SELECT_MESAS = "SELECT * from mesas";
	static final String SELECT_MESA_BY_ID = "SELECT * from mesas where id = ?";
	static final String SELECT_MESA_OPTIMA = "select m.* from mesas m join restaurantes_mesas rm on m.id = rm.mesas_id where rm.restaurantes_id = ? and m.capacidad >= ? and m.id not in (select r.mesa_id from reservas r where r.restaurante_id = ? and r.turno_id = ? and r.dia = ?) order by m.capacidad ASC limit 1";

	@Override
	public Mesa get(long id) {
		Object[] params = new Object[] { id };
		return jdbcTemplate.queryForObject(SELECT_MESA_BY_ID, params, mapper());
	}

	public List<Mesa> getMesas() {
		return jdbcTemplate.query(SELECT_MESAS, mapper());
	}

	// Este método retorna una mesa para asignar a una reserva teniendo en cuenta el
	// restaurante, el turno, la fecha i la capacidad
	@Override
	public Mesa getMesaLibre(long restauranteId, long turnoId, Date dia, int personas) {
		Object[] params = new Object[] { restauranteId, personas, restauranteId, turnoId, dia };
		try {
			return jdbcTemplate.queryForObject(SELECT_MESA_OPTIMA, params, mapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void addMesa(Mesa mesa) {

		getCurrentSession().save(mesa);
	}

	public void updateMesa(Mesa mesa) {
		Mesa mesaToUpdate = get(mesa.getId());
		mesaToUpdate.setNumero(mesa.getNumero());
		mesaToUpdate.setCapacidad(mesa.getCapacidad());
		getCurrentSession().update(mesaToUpdate);

	}

	public void deleteMesa(long id) {
		Mesa mesa = get(id);
		if (mesa != null)
			getCurrentSession().delete(mesa);
	}

	private RowMapper<Mesa> mapper() {
		RowMapper<Mesa> map = new RowMapper<Mesa>() {
			public Mesa mapRow(ResultSet rs, int rowNum) throws SQLException {
				Mesa mesa = new Mesa();
				mesa.setId(rs.getLong("id"));
				mesa.setCapacidad(rs.getInt("capacidad"));
				mesa.setNumero(rs.getInt("numero"));
				return mesa;
			}
		};
		return map;
	}

}
