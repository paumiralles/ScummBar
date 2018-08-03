package com.scummbar.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scummbar.dao.MesaDAO;
import com.scummbar.dao.ReservaDAO;
import com.scummbar.dao.RestauranteDAO;
import com.scummbar.dao.TurnoDAO;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.negocio.NegocioRestaurante;

@Controller

public class ControladorRestaurantes {

	@Autowired
	private NegocioRestaurante negocioRestaurante;

	// Este request imprime la vista con todos los restaurantes del negocio
	@RequestMapping(value = "/restaurantes", method = RequestMethod.GET)
	public ModelAndView restaurantes() {
		ModelAndView model = new ModelAndView("restaurantes");

		List<Restaurante> restaurantes = negocioRestaurante.getRestaurantes();
		model.addObject("restaurantes", restaurantes);
		return model;
	}

	// TODO: Borrar el código de aquí abajo. NUNCA usar DAO en capa de Controler; te
	// cortan los huevos
	@Autowired
	private MesaDAO mesaDao;
	@Autowired
	private ReservaDAO reservaDao;
	@Autowired
	private RestauranteDAO restauranteDao;
	@Autowired
	private TurnoDAO turnoDao;

	@RequestMapping(value = "/mesas", method = RequestMethod.GET)
	public void mesas() {
		mesaDao.getMesas();
		reservaDao.getReservas();
		restauranteDao.getRestaurantes();
		turnoDao.getTurnos();
	}

	@RequestMapping(value = "/mesas/{id}", method = RequestMethod.GET)
	public void mesas(@PathVariable long id) {
//		mesaDao.get(id);
//		restauranteDao.getRestaurante(id);
//		turnoDao.getTurno(id);

		Reserva reserva = reservaDao.getReserva(id);
		reserva.setPersonas(0);
		reservaDao.updateReserva(reserva);
	}

	// XXX: Borrar el código de aquí arriba
	// FIXME: Borrar el código de aquí arriba

}
