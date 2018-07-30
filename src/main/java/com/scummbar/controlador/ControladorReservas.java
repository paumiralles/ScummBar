package com.scummbar.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scummbar.modelo.dto.ReservarDTO;
import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;
import com.scummbar.modelo.negocio.NegocioReserva;
import com.scummbar.modelo.negocio.NegocioRestaurante;

@Controller
public class ControladorReservas {

	@Autowired
	NegocioRestaurante negocioRestaurante;
	@Autowired
	NegocioReserva negocioReserva;

	@RequestMapping(value = "/reservar", method = RequestMethod.GET)
	public ModelAndView paginaAddReserva() {

		ModelAndView model = new ModelAndView("reservar");
		ReservarDTO dto = new ReservarDTO();
		dto.setRestaurantes(negocioRestaurante.getRestaurantes());
		dto.setTurnos(negocioRestaurante.getTurnos());
		model.addObject("command", dto);
		return model;
	}

	@RequestMapping(value = "/reservar", method = RequestMethod.POST)
	public ModelAndView addReserva(@ModelAttribute ReservarDTO dto) {

		ModelAndView model = new ModelAndView("reservado");
		Restaurante restaurante = new Restaurante();
		Turno turno = new Turno();
		Reserva reserva = new Reserva();
		Mesa mesa = new Mesa();
		String localizador = negocioReserva.getLocalizador();
		reserva.setDia(dto.getDia());
		reserva.setPersonas(dto.getPersonas());
		restaurante.setId(dto.getRestauranteId());
		reserva.setRestaurante(restaurante);
		turno.setId(dto.getTurnoId());
		reserva.setTurno(turno);
		mesa.setId(1L);
		reserva.setMesa(mesa);

		reserva.setLocalizador(localizador);

		model.addObject("reserva", negocioRestaurante.reserva(restaurante, reserva));

		return model;
	}
}
