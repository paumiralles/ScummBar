package com.scummbar.modelo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurantes")

public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "direccion", nullable = false, length = 100)
	private String direccion;

	@Column(name = "descripcion", nullable = false, length = 500)
	private String descripcion;

	@OneToMany
	private List<Reserva> reservas;

	@OneToMany
	private List<Mesa> mesas;

	public void setId(Long restauranteId) {
		this.id = restauranteId;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public Long getId() {
		return id;
	}

	public Restaurante() {
		super();
	}

	public Restaurante(Long id, String nombre, String direccion, String descripcion, List<Reserva> reservas,
			List<Mesa> mesas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.reservas = reservas;
		this.mesas = mesas;
	}

}
