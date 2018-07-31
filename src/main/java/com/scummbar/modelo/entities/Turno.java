package com.scummbar.modelo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Esta clase crea una tabla en la base de datos con las columnas "id", "descripcion", donde cada fila representa un turno

@Entity
@Table(name = "turnos", catalog = "test")

public class Turno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "descripcion", nullable = false, length = 500)
	private String descripcion;

	public Turno(Long id) {
		super();
		this.id = id;
	}

	public static Turno valueOf(Long turnoId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Turno() {
		super();
	}

}
