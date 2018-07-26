package com.scummbar.modelo.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;

@Service
public class NegocioRestaurante implements INegocioRestaurante {

	private EntityManager entitymanager;

	private List<Restaurante> restaurantes;

	private List<Turno> turnos;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scummbar.modelo.negocio.INegocioRestaurante#getRestaurantes()
	 */
	@Override
	public List<Restaurante> getRestaurantes() {

		restaurantes = new ArrayList<>();
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

		restaurantes.add(aux1);
		restaurantes.add(aux2);
		return restaurantes;
	}

	@Override
	public List<Turno> getTurnos() {
		turnos = new ArrayList<Turno>();
		turnos.add(new Turno(4L, "Turno 4"));
		turnos.add(new Turno(3L, "Turno 3"));
		return turnos;
	}

	@Override
	public Reserva reservar(Restaurante restaurante, Reserva reserva) {
		// TODO Auto-generated method stub
		// Guardar en BBDD + crear localizador + devolver Reserva [o null]
		Random rand = new Random();
		int localizador = rand.nextInt(101) + 1;
		reserva.setLocalizador(Integer.toString(localizador));
		return reserva;
	}

	@Override
	public Object cancelarReserva(Restaurante restaurante, Reserva reserva) {
		// TODO Auto-generated method stub
		return null;
	}

}
