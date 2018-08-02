package com.scummbar.modelo.negocio.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
	private Mesa mockedMesaSinReserva1 = Mockito.mock(Mesa.class);
	private Mesa mockedMesaSinReserva2 = Mockito.mock(Mesa.class);
	private Mesa mockedMesaConReserva1 = Mockito.mock(Mesa.class);
	private Mesa mockedMesaConReserva2 = Mockito.mock(Mesa.class);
	private Reserva mockedReserva1 = Mockito.mock(Reserva.class);
	private Reserva mockedReserva2 = Mockito.mock(Reserva.class);

	@Before
	public void setup() {
		reservaDAO = Mockito.mock(ReservaDAO.class);
		restauranteDAO = Mockito.mock(RestauranteDAO.class);
		negocioReservaImpl = new NegocioReservaImpl(restauranteDAO, reservaDAO);

	}

	// Test getLocalizador()
	@Test
	public void getLocalizadorFunciona() {
		String localizador = negocioReservaImpl.getLocalizador();
		String esperado = DateTimeFormatter.ofPattern("ddMMyyHHmmss").format(LocalDateTime.now());
		assertEquals(esperado, localizador);

	}

	// Test getMesasLibresDeRestaurante()
	@Test
	public void givenTwoTablesOneReservedThenReturnedOneFreeTable() {
		// given
		List<Mesa> listaMesas = Arrays.asList(mockedMesaSinReserva1, mockedMesaConReserva1);
		List<Reserva> listaReservas = Arrays.asList(mockedReserva1);

		Mockito.when(mockedReserva1.getMesa()).thenReturn(mockedMesaConReserva1);

		List<Mesa> mesasLibresEsperadas = Arrays.asList(mockedMesaSinReserva1);

		// when
		List<Mesa> mesasLibres = negocioReservaImpl.getMesasLibresDeRestaurante(listaMesas, listaReservas);

		// then
		assertNotNull(mesasLibres);
		assertEquals(mesasLibres.isEmpty(), false);
		assertEquals(mesasLibres.size(), 1);
		assertEquals(mesasLibres, mesasLibresEsperadas);

	}

	@Test
	public void givenAllTablesReservedThenReturnedZeroFreeTable() {
		// given
		List<Mesa> listaMesas = Arrays.asList(mockedMesaConReserva1, mockedMesaConReserva2);
		List<Reserva> listaReservas = Arrays.asList(mockedReserva1, mockedReserva2);
		ArrayList<Mesa> mesasLibresEsperadas = new ArrayList<Mesa>();
		Mockito.when(mockedReserva1.getMesa()).thenReturn(mockedMesaConReserva1);
		Mockito.when(mockedReserva2.getMesa()).thenReturn(mockedMesaConReserva2);

		// when
		List<Mesa> mesasLibres = negocioReservaImpl.getMesasLibresDeRestaurante(listaMesas, listaReservas);

		// then
		assertNotNull(mesasLibres);
		assertEquals(mesasLibres.isEmpty(), true);
		assertEquals(mesasLibres, mesasLibresEsperadas); // Ejemplo poco usado y desaconsejado

	}

	// Test getMesaLibreParaReserva()
	@Test
	public void givenTwoTablesOneReservedOneOfEnoughCapacityThenReturnedTheFreeTable() {

		// given
		List<Mesa> listaMesas = Arrays.asList(mockedMesaSinReserva1, mockedMesaConReserva1);
		List<Reserva> listaReservas = Arrays.asList(mockedReserva1);

		Restaurante restaurante = new Restaurante();
		long restauranteId = 1L;
		restaurante.setId(restauranteId);
		restaurante.setMesas(listaMesas);
		long turnoId = 1L;
		Date dia = new Date();

		Mockito.when(mockedMesaSinReserva1.getCapacidad()).thenReturn(3);
		Mockito.when(mockedMesaConReserva1.getCapacidad()).thenReturn(2);
		Mockito.when(restauranteDAO.getRestaurante(restauranteId)).thenReturn(restaurante);
		Mockito.when(mockedReserva1.getMesa()).thenReturn(mockedMesaConReserva1);
		Mockito.when(reservaDAO.getReservasByRestaurantAndDayAndTurn(restauranteId, dia, turnoId))
				.thenReturn(listaReservas);

		Mesa expectedMesa = listaMesas.get(0);

		// when
		Mesa mesaLibre = negocioReservaImpl.getMesaLibreParaReserva(restauranteId, dia, turnoId, 2);

		// then
		assertNotNull(mesaLibre);
		assertEquals(mesaLibre, expectedMesa);

	}

	@Test
	public void givenTablesReservedThenReturnedNull() {

		// given
		List<Mesa> listaMesas = Arrays.asList(mockedMesaConReserva1, mockedMesaConReserva2);
		List<Reserva> listaReservas = Arrays.asList(mockedReserva1, mockedReserva2);

		Restaurante restaurante = new Restaurante();
		long restauranteId = 1L;
		restaurante.setId(restauranteId);
		restaurante.setMesas(listaMesas);
		long turnoId = 1L;
		Date dia = new Date();

		Mockito.when(mockedMesaConReserva2.getCapacidad()).thenReturn(3);
		Mockito.when(mockedMesaConReserva1.getCapacidad()).thenReturn(2);
		Mockito.when(restauranteDAO.getRestaurante(restauranteId)).thenReturn(restaurante);
		Mockito.when(mockedReserva1.getMesa()).thenReturn(mockedMesaConReserva1);
		Mockito.when(mockedReserva2.getMesa()).thenReturn(mockedMesaConReserva2);
		Mockito.when(reservaDAO.getReservasByRestaurantAndDayAndTurn(restauranteId, dia, turnoId))
				.thenReturn(listaReservas);

		// when
		Mesa mesaLibre = negocioReservaImpl.getMesaLibreParaReserva(restauranteId, dia, turnoId, 2);

		// then
		assertNull(mesaLibre);

	}

	@Test
	public void givenTwoTablesNoneReservedOneOfEnoughCapacityThenReturnedTheFreeTable() {

		// given
		List<Mesa> listaMesas = Arrays.asList(mockedMesaSinReserva1, mockedMesaSinReserva2);
		List<Reserva> listaReservas = new ArrayList<>();

		Restaurante restaurante = new Restaurante();
		long restauranteId = 1L;
		restaurante.setId(restauranteId);
		restaurante.setMesas(listaMesas);
		long turnoId = 1L;
		Date dia = new Date();

		Mockito.when(mockedMesaSinReserva1.getCapacidad()).thenReturn(1);
		Mockito.when(mockedMesaSinReserva2.getCapacidad()).thenReturn(2);
		Mockito.when(restauranteDAO.getRestaurante(restauranteId)).thenReturn(restaurante);
		Mockito.when(mockedReserva1.getMesa()).thenReturn(mockedMesaConReserva1);
		Mockito.when(reservaDAO.getReservasByRestaurantAndDayAndTurn(restauranteId, dia, turnoId))
				.thenReturn(listaReservas);

		Mesa expectedMesa = listaMesas.get(1);

		// when
		Mesa mesaLibre = negocioReservaImpl.getMesaLibreParaReserva(restauranteId, dia, turnoId, 2);

		// then
		assertNotNull(mesaLibre);
		assertEquals(mesaLibre, expectedMesa);

	}
}