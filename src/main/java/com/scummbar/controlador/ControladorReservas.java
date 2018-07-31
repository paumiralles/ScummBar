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

	// Este request imprime la vista con el formulario para poder hacer una reserva
	@RequestMapping(value = "/reservar", method = RequestMethod.GET)
	public ModelAndView paginaAddReserva() {

		ModelAndView model = new ModelAndView("reservar");
		ReservarDTO dto = new ReservarDTO();
		dto.setRestaurantes(negocioRestaurante.getRestaurantes());
		dto.setTurnos(negocioRestaurante.getTurnos());
		model.addObject("command", dto);
		return model;
	}

	// Este request imprime la vista resumen de la reserva con su identificador una
	// vez esta se ha realizado
	@RequestMapping(value = "/reservar", method = RequestMethod.POST)
	public ModelAndView addReserva(@ModelAttribute ReservarDTO dto) {

		Restaurante restaurante = new Restaurante(dto.getRestauranteId());
		Turno turno = new Turno(dto.getTurnoId());
		Mesa mesa = negocioReserva.getMesaLibre(dto.getRestauranteId(), dto.getDia(), dto.getTurnoId(),
				dto.getPersonas());
		if (mesa == null) {
			return new ModelAndView("reservaNoPosible");
		}
		ModelAndView modelOk = new ModelAndView("reservado");
		String localizador = negocioReserva.getLocalizador();

		Reserva reserva = new Reserva(dto.getDia(), dto.getPersonas(), localizador, turno, mesa, restaurante);

		modelOk.addObject("reserva", negocioRestaurante.reserva(restaurante, reserva));

		return modelOk;
	}
}
