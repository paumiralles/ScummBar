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
public class ControladorCancelacion {

	@Autowired
	NegocioRestaurante negocioRestaurante;
	@Autowired
	NegocioReserva negocioReserva;

	@RequestMapping(value = "/cancelar", method = RequestMethod.GET)
	public ModelAndView cancelar() {

		ModelAndView model = new ModelAndView("cancelar");
		ReservarDTO dto = new ReservarDTO();
		dto.setRestaurantes(negocioRestaurante.getRestaurantes());
		dto.setTurnos(negocioRestaurante.getTurnos());
		model.addObject("command", dto);
		return model;
	}

	@RequestMapping(value = "/cancelar", method = RequestMethod.POST)
	public ModelAndView removeReserva(@ModelAttribute ReservarDTO dto) {

		ModelAndView model = new ModelAndView("cancelado");
		Restaurante restaurante = new Restaurante();
		Turno turno = new Turno();
		Reserva reserva = new Reserva();
		Mesa mesa = new Mesa();
//			String localizador = negocioReserva.getLocalizador();
		reserva.setDia(dto.getDia());
		reserva.setPersonas(dto.getPersonas());
		restaurante.setId(dto.getRestauranteId());
		reserva.setRestaurante(restaurante);
		turno.setId(dto.getTurnoId());
		reserva.setTurno(turno);
		mesa.setId(1L);
		reserva.setMesa(mesa);

		reserva.setLocalizador("");

		model.addObject("reserva", negocioRestaurante.reserva(restaurante, reserva));

		return model;
	}

	@RequestMapping(value = "/cancelarLoc", method = RequestMethod.GET)
	public ModelAndView cancelarConLoc() {

		ModelAndView model = new ModelAndView("cancelarPorLoc");
		ReservarDTO dto = new ReservarDTO();
		dto.setRestaurantes(negocioRestaurante.getRestaurantes());
		dto.setTurnos(negocioRestaurante.getTurnos());
		model.addObject("command", dto);
		return model;
	}

	@RequestMapping(value = "/cancelarLoc", method = RequestMethod.POST)
	public ModelAndView removeReservaLoc(@ModelAttribute ReservarDTO dto) {

		ModelAndView model = new ModelAndView("cancelado");
		String localizador = dto.getLocalizador();
		negocioReserva.deleteReserva(localizador);
		return model;
	}

}
