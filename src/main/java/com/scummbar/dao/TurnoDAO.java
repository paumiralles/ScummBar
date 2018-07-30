package com.scummbar.dao;

import java.util.List;

import com.scummbar.modelo.entities.Turno;

public interface TurnoDAO {

	public void addTurno(Turno turno);

	public void updateTurno(Turno turno);

	public Turno getTurno(long id);

	public void deleteTurno(long id);

	public List<Turno> getTurnos();

}