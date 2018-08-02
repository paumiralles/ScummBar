package com.scummbar.modelo.negocio.impl;

import org.junit.Before;
import org.mockito.Mockito;

import com.scummbar.dao.ReservaDAO;

public class NegocioRestauranteImplTest {

	private ReservaDAO reservaDAO;
	private NegocioRestauranteImpl negocioRestauranteImpl;

	@Before
	public void setup() {
		reservaDAO = Mockito.mock(ReservaDAO.class);
		negocioRestauranteImpl = new NegocioRestauranteImpl(reservaDAO);
	}

}
