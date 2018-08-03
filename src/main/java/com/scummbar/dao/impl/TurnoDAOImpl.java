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

import com.scummbar.dao.TurnoDAO;
import com.scummbar.modelo.entities.Turno;

@Repository

public class TurnoDAOImpl implements TurnoDAO {
	private static final String SELECT_TURNOS = "SELECT * from turnos";
	private static final String SELECT_TURNO_BY_ID = "SELECT * from turnos where id = ?";

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Turno getTurno(long id) {
		Object[] params = new Object[] { id };
		return jdbcTemplate.queryForObject(SELECT_TURNO_BY_ID, params, mapper());
	}

	public List<Turno> getTurnos() {
		return jdbcTemplate.query(SELECT_TURNOS, mapper());
	}

	public void addTurno(Turno turno) {
		getCurrentSession().save(turno);
	}

	public void updateTurno(Turno turno) {
		Turno turnoToUpdate = getTurno(turno.getId());
		turnoToUpdate.setDescripcion(turno.getDescripcion());
		getCurrentSession().update(turnoToUpdate);

	}

	public void deleteTurno(long id) {
		Turno turno = getTurno(id);
		if (turno != null)
			getCurrentSession().delete(turno);
	}

	private RowMapper<Turno> mapper() {
		RowMapper<Turno> map = new RowMapper<Turno>() {
			public Turno mapRow(ResultSet rs, int rowNum) throws SQLException {
				Turno turno = new Turno();
				turno.setId(rs.getLong("id"));
				turno.setDescripcion(rs.getString("descripcion"));
				return turno;
			}
		};
		return map;
	}

}
