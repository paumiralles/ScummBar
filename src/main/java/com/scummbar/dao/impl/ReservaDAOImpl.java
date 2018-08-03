package com.scummbar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.scummbar.dao.ReservaDAO;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.exceptions.CancelacionException;

@Repository

public class ReservaDAOImpl implements ReservaDAO {
	private static final String SELECT_RESERVA = "SELECT * from reservas";
	private static final String SELECT_RESERVA_BY_ID = "SELECT * from reservas where id = ?";
	private static final String INSERT_RESERVA = "insert reservas(dia, localizador, personas, mesa_id, restaurante_id, turno_id ) value (?,?,?,?,?,?)";
	private static final String DELETE_RESERVA = "delete from reservas where localizador = ?";
	private static final String UPDATE_RESERVA = "update reservas set dia=?, localizador=?, personas=? where id=?";
	private static final String EL_LOCALIZADOR_NO_EXISTE = "El localizador no existe";

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Reserva getReserva(long id) {
		Object[] params = new Object[] { id };
		return jdbcTemplate.queryForObject(SELECT_RESERVA_BY_ID, params, mapper());
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservas() {
		return jdbcTemplate.query(SELECT_RESERVA, mapper());
	}

	// Este método retorna una lista con las reservas de un restaurante, de un dia y
	// de un turno
	@Override
	public List<Reserva> getReservasByRestaurantAndDayAndTurn(Long restauranteId, Date dia, Long turnoId) {
		Query query = getCurrentSession()
				.createQuery("from Reserva where (restaurante_id = :rest and turno_id = :turno and dia = :date)");
		query.setLong("rest", restauranteId);
		query.setDate("date", dia);
		query.setLong("turno", turnoId);
		List<Reserva> lista = query.list();
		return lista;
	}

	public void addReserva(Reserva reserva) {
		// dia, localizador, personas, mesa_id, restaurante_id, turno_id
		Object[] params = new Object[] { reserva.getDia(), reserva.getLocalizador(), reserva.getPersonas(),
				reserva.getMesa().getId(), reserva.getRestaurante().getId(), reserva.getTurno().getId() };
		jdbcTemplate.update(INSERT_RESERVA, params);
	}

	public void updateReserva(Reserva reserva) {
		// dia, localizador, personas, mesa_id, restaurante_id, turno_id
		Object[] params = new Object[] { reserva.getDia(), reserva.getLocalizador(), reserva.getPersonas(),
				reserva.getId() };
		jdbcTemplate.update(UPDATE_RESERVA, params);

	}

	public void deleteReserva(long id) {
		Reserva reserva = getReserva(id);
		if (reserva != null)
			getCurrentSession().delete(reserva);
	}

	@Override
	public void deleteReserva(String localizador) throws CancelacionException {

		Object[] params = new Object[] { localizador };
		int filasAfectadas = jdbcTemplate.update(DELETE_RESERVA, params);
		if (filasAfectadas == 0) {
			throw new CancelacionException(EL_LOCALIZADOR_NO_EXISTE);
		}

	}

	private RowMapper<Reserva> mapper() {
		RowMapper<Reserva> map = new RowMapper<Reserva>() {
			public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {
				Reserva reserva = new Reserva();
				reserva.setId(rs.getLong("id"));
				reserva.setDia(rs.getDate("dia"));
				reserva.setPersonas(rs.getInt("personas"));
				reserva.setLocalizador(rs.getString("localizador"));
				return reserva;
			}
		};
		return map;
	}
}
