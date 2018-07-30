package com.scummbar.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.negocio.NegocioRestaurante;

@Controller

public class ControladorRestaurantes {

	@Autowired
	private NegocioRestaurante negocioRestaurante;

	@RequestMapping(value = "/restaurantes", method = RequestMethod.GET)
	public ModelAndView restaurantes() {
		ModelAndView model = new ModelAndView("restaurantes");

		List<Restaurante> restaurantes = negocioRestaurante.getRestaurantes();
		model.addObject("restaurantes", restaurantes);
		return model;
	}
}
