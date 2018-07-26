package com.scummbar.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scummbar.modelo.dto.ReservarDTO;
import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;
import com.scummbar.modelo.negocio.INegocioRestaurante;

@Controller
public class ControladorReservas {

	@Autowired
	INegocioRestaurante negocioRestaurante;

	@RequestMapping(value = "/reservar", method = RequestMethod.GET)
	public ModelAndView verFormulario() {
		ArrayList<Restaurante> listaaux = new ArrayList<Restaurante>();

		Restaurante aux1 = new Restaurante();
		aux1.setNombre("Pizzeria");
		aux1.setId(1L);
		aux1.setDireccion("Diagonal 12, Barcelona");
		aux1.setDescripcion("Pizzas");
		Mesa mesa1 = new Mesa(1L, 4);
		Mesa mesa2 = new Mesa(2L, 3);
		List<Mesa> mesas1 = new ArrayList<Mesa>();
		mesas1.add(mesa1);
		mesas1.add(mesa2);
		aux1.setMesas(mesas1);
		Reserva reserva1 = new Reserva(1L, new Date(2018, 2, 11), 2, "1", new Turno(1L, "Turno 1"));
		Reserva reserva2 = new Reserva(2L, new Date(2018, 2, 12), 3, "2", new Turno(2L, "Turno 2"));
		List<Reserva> reservas1 = new ArrayList<Reserva>();
		reservas1.add(reserva1);
		reservas1.add(reserva2);
		aux1.setReservas(reservas1);

		Restaurante aux2 = new Restaurante();
		aux2.setNombre("Japonès");
		aux2.setId(2L);
		aux2.setDireccion("Muntaner 12, Barcelona");
		aux2.setDescripcion("Sushi");
		Mesa mesa3 = new Mesa(3L, 1);
		Mesa mesa4 = new Mesa(4L, 2);
		List<Mesa> mesas2 = new ArrayList<Mesa>();
		mesas1.add(mesa3);
		mesas1.add(mesa4);
		aux2.setMesas(mesas2);
		Reserva reserva3 = new Reserva(3L, new Date(2018, 2, 9), 1, "3", new Turno(3L, "Turno 3"));
		Reserva reserva4 = new Reserva(4L, new Date(2018, 2, 14), 6, "5", new Turno(4L, "Turno 4"));
		List<Reserva> reservas2 = new ArrayList<Reserva>();
		reservas2.add(reserva3);
		reservas2.add(reserva4);
		aux2.setReservas(reservas2);

		listaaux.add(aux1);
		listaaux.add(aux2);

		ModelAndView model = new ModelAndView("reservar");
		ReservarDTO dto = new ReservarDTO();
		dto.setRestaurantes(negocioRestaurante.getRestaurantes());
		dto.setTurnos(negocioRestaurante.getTurnos());
		model.addObject("command", dto);
		return model;
	}

	@RequestMapping(value = "/reservar", method = RequestMethod.POST)
	public ModelAndView submitFormulario(ReservarDTO dto) {
		ModelAndView model = new ModelAndView("reservado");
		Reserva reserva = new Reserva(null, null, null, null, null);
		reserva.setDia(dto.getDia());
		reserva.setPersonas(dto.getPersonas());
		reserva.setTurno(Turno.valueOf(dto.getTurnoId()));
		Restaurante restaurante = new Restaurante();
		restaurante.setId(dto.getRestauranteId());
		Reserva reservaGuardada = negocioRestaurante.reservar(restaurante, reserva);
		if (reservaGuardada != null)
			model.addObject("reserva", reservaGuardada.getLocalizador());
		return model;
	}
}
