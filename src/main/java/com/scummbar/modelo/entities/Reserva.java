package com.scummbar.modelo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservas", catalog = "test")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "dia", nullable = false)
	private Date dia;

	@Column(name = "personas", nullable = false)
	private Integer personas;

	@Column(name = "localizador", nullable = false, length = 100)
	private String localizador;

	@OneToOne
	private Turno turno;

	public Reserva() {
		super();
	}

	public Reserva(Long id, Date dia, Integer personas, String localizador, Turno turno) {
		super();
		this.id = id;
		this.dia = dia;
		this.personas = personas;
		this.localizador = localizador;
		this.turno = turno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Integer getPersonas() {
		return personas;
	}

	public void setPersonas(Integer personas) {
		this.personas = personas;
	}

	public String getLocalizador() {
		return localizador;
	}

	public void setLocalizador(String localizador) {
		this.localizador = localizador;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}
