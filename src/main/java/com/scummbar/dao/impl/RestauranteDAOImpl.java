package com.scummbar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.scummbar.dao.RestauranteDAO;
import com.scummbar.modelo.entities.Restaurante;

@Repository
public class RestauranteDAOImpl implements RestauranteDAO {
	private static final String SELECT_RESTAURANTES = "SELECT * from restaurantes";
	private static final String SELECT_RESTAURANTE_BY_ID = "SELECT * from restaurantes where id = ?";

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Restaurante getRestaurante(long id) {
		Object[] params = new Object[] { id };
		return jdbcTemplate.queryForObject(SELECT_RESTAURANTE_BY_ID, params, mapper());
	}

	public List<Restaurante> getRestaurantes() {
		return jdbcTemplate.query(SELECT_RESTAURANTES, mapper());
	}

	public void addRestaurante(Restaurante restaurante) {
		getCurrentSession().save(restaurante);
	}

	public void updateRestaurante(Restaurante restaurante) {
		Restaurante restauranteToUpdate = getRestaurante(restaurante.getId());
		restauranteToUpdate.setNombre(restaurante.getNombre());
		restauranteToUpdate.setDireccion(restaurante.getDireccion());
		restauranteToUpdate.setDescripcion(restaurante.getDescripcion());
		restauranteToUpdate.setReservas(restaurante.getReservas());
		restauranteToUpdate.setMesas(restaurante.getMesas());
		getCurrentSession().update(restauranteToUpdate);

	}

	public void deleteRestaurante(long id) {
		Restaurante restaurante = getRestaurante(id);
		if (restaurante != null)
			getCurrentSession().delete(restaurante);
	}

	private RowMapper<Restaurante> mapper() {
		RowMapper<Restaurante> map = new RowMapper<Restaurante>() {
			public Restaurante mapRow(ResultSet rs, int rowNum) throws SQLException {
				Restaurante restaurante = new Restaurante();
				restaurante.setId(rs.getLong("id"));
				restaurante.setNombre(rs.getString("nombre"));
				restaurante.setDireccion(rs.getString("direccion"));
				restaurante.setDescripcion(rs.getString("descripcion"));
				return restaurante;
			}
		};
		return map;
	}

}
