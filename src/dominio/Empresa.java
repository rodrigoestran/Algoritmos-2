/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import estructuras.Nodo;
import utilidades.EmailValidator;

/**
 *
 * @author alumnoFI
 */
public class Empresa {

	private String nombre;
	private String dir;
	private String email;
	private String color;
	private String pais;

	// Constructor //
	public Empresa(String nombre, String dir, String email, String color, String pais) {
		super();
		this.nombre = nombre;
		this.dir = dir;
		this.email = email;
		this.color = color;
		this.pais = pais;
	}

	// Getters y setters //
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	// comportamiento //
	public boolean validar() {
		EmailValidator validator = new EmailValidator();
		return validator.validate(this.email);
	}

	@Override
	public String toString() {
		return nombre + ";" + email;
	}

	@Override
	public boolean equals(Object obj) {
		Empresa other = (Empresa) obj;
		return other.getNombre().equals(this.nombre);
	}

	public int compareTo(Nodo n) {
		Empresa nuevo = n.getDato();
		return this.nombre.compareTo(nuevo.getNombre());
	}


}
