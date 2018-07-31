package com.scummbar.modelo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Esta clase crea una tabla en la base de datos con las columnas "id", "numero" y "capacidad", donde cada fila representa una mesa
@Entity
@Table(name = "mesas", catalog = "test")

public class Mesa implements Comparable<Mesa> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "numero", nullable = false)
	private Integer numero;

	@Column(name = "capacidad", nullable = false)
	private Integer capacidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Mesa() {
		super();
	}

	public Mesa(Long id, Integer capacidad) {

		this.id = id;
		this.capacidad = capacidad;
	}

	@Override
	public int compareTo(Mesa mesa) {
		return this.getCapacidad().compareTo(mesa.getCapacidad());
	}

}
