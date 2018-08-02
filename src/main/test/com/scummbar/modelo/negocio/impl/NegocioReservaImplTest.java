package com.scummbar.modelo.negocio.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.scummbar.dao.ReservaDAO;
import com.scummbar.dao.RestauranteDAO;
import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;

public class NegocioReservaImplTest {

	private NegocioReservaImpl negocioReservaImpl;
	private ReservaDAO reservaDAO;
	private RestauranteDAO restauranteDAO;
	private Mesa mockedMesaSinReserva = Mockito.mock(Mesa.class);
	private Mesa mockedMesaConReserva1 = Mockito.mock(Mesa.class);
	private Mesa mockedMesaConReserva2 = Mockito.mock(Mesa.class);

	@Before
	public void setup() {
		reservaDAO = Mockito.mock(ReservaDAO.class);
		restauranteDAO = Mockito.mock(RestauranteDAO.class);
		negocioReservaImpl = new NegocioReservaImpl(restauranteDAO, reservaDAO);

	}

	@Test
	public void getLocalizadorFunciona() {
		String localizador = negocioReservaImpl.getLocalizador();
		String esperado = DateTimeFormatter.ofPattern("ddMMyyHHmmss").format(LocalDateTime.now());
		assertEquals(esperado, localizador);

	}

	@Test
	public void givenTwoTablesOneReservedThenReturnedOneFreeTable() {
		// given - dado
		List<Mesa> listaMesas = Arrays.asList(mockedMesaSinReserva, mockedMesaConReserva1);

		Reserva mockedReserva = new Reserva();
		mockedReserva.setMesa(mockedMesaConReserva1);

		List<Reserva> listaReservas = Arrays.asList(mockedReserva);

		List<Mesa> expectedmesasLibres = Arrays.asList(mockedMesaSinReserva);

		// when - cuando
		List<Mesa> mesasLibres = negocioReservaImpl.getMesasLibresDeRestaurante(listaMesas, listaReservas);

		// then - entonces
		assertNotNull(mesasLibres);
		assertEquals(mesasLibres.isEmpty(), false);
		assertEquals(mesasLibres.size(), 1);
		assertEquals(mesasLibres, expectedmesasLibres);

	}

	@Test
	public void givenAllTablesReservedThenReturnedZeroFreeTable() {
		// given - dado
		List<Mesa> listaMesas = Arrays.asList(mockedMesaConReserva1, mockedMesaConReserva2);

		Reserva mockedReserva = new Reserva();
		mockedReserva.setMesa(mockedMesaConReserva1);
		Reserva mockedReserva2 = new Reserva();
		mockedReserva2.setMesa(mockedMesaConReserva2);

		List<Reserva> listaReservas = Arrays.asList(mockedReserva, mockedReserva2);

		// when - cuando
		List<Mesa> mesasLibres = negocioReservaImpl.getMesasLibresDeRestaurante(listaMesas, listaReservas);

		// then - entonces
		assertNotNull(mesasLibres);
		assertEquals(mesasLibres.isEmpty(), true);
		assertEquals(mesasLibres, new ArrayList<Mesa>()); // Ejemplo poco usado y desaconsejado

	}

	@Test

	public void getMesaLibreParaReservarFunciona() {

		// given

		Mesa mockedMesa1 = Mockito.mock(Mesa.class);
		Mesa mockedMesa2 = Mockito.mock(Mesa.class);
		Mockito.when(mockedMesa1.getCapacidad()).thenReturn(3);
		Mockito.when(mockedMesa2.getCapacidad()).thenReturn(2);
		List<Mesa> listaMesas = Arrays.asList(mockedMesa1, mockedMesa2);

		Restaurante restaurante = new Restaurante();
		long restauranteId = 1L;
		long turnoId = 1L;
		Date dia = new Date();

		restaurante.setId(restauranteId);
		Integer i = listaMesas.get(0).getCapacidad();

		restaurante.setMesas(listaMesas);
		Mockito.when(restauranteDAO.getRestaurante(restauranteId)).thenReturn(restaurante);

		List<Reserva> listaReservas = new ArrayList<>();
		Reserva mockedReserva = Mockito.mock(Reserva.class);
		Mockito.when(mockedReserva.getMesa()).thenReturn(mockedMesa2);
		listaReservas.add(mockedReserva);

		Mockito.when(reservaDAO.getReservasByRestaurantAndDayAndTurn(restauranteId, dia, turnoId))
				.thenReturn(listaReservas);
		Mesa expectedMesa = listaMesas.get(1);

		// when
		Mesa mesaLibre = negocioReservaImpl.getMesaLibreParaReserva(restauranteId, dia, turnoId, 2);

		// then
		assertNotNull(mesaLibre);

		// assertEquals(mesaLibre, expectedMesa);
	}
}
//	public void getMesaLibreParaReservaFunciona() {
//		// given
//
//		Mesa mockedMesa1 = Mockito.mock(Mesa.class);
//		Mesa mockedMesa2 = Mockito.mock(Mesa.class);
//		Mockito.when(mockedMesa1.getCapacidad()).thenReturn(3);
//		Mockito.when(mockedMesa2.getCapacidad()).thenReturn(2);
//		List<Mesa> listaMesas = Arrays.asList(mockedMesa1, mockedMesa2);
//
//		Restaurante restaurante = new Restaurante();
//		long restauranteId = 1L;
//		long turnoId = 1L;
//		Date dia = new Date();
//
//		restaurante.setId(restauranteId);
//		Integer i = listaMesas.get(0).getCapacidad();
//		
//		restaurante.setMesas(listaMesas);
//		Mockito.when(restauranteDAO.getRestaurante(restauranteId)).thenReturn(restaurante);
//		
//		List<Reserva> listaReservas = new ArrayList<>();
//		Reserva mockedReserva = Mockito.mock(Reserva.class);
//		Mockito.when(mockedReserva.getMesa()).thenReturn(mockedMesa2);
//		listaReservas.add(mockedReserva);
//
//		Mockito.when(reservaDAO.getReservasByRestaurantAndDayAndTurn(restauranteId, dia, turnoId))
//				.thenReturn(listaReservas);
//		Mesa expectedMesa = listaMesas.get(1);
//
//		// when
//		Mesa mesaLibre = negocioReservaImpl.getMesaLibreParaReserva(restauranteId, dia, turnoId, 2);
//
//		// then
//		assertNotNull(mesaLibre);
//
//		// assertEquals(mesaLibre, expectedMesa);
//	}
