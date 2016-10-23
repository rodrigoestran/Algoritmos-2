/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import estructuras.Lista;

/**
 *
 * @author alumnoFI
 */
public class Ciudad {
    
	private Ubicacion ubicacion;
	private Lista datacenters;
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Lista getDatacenters() {
		return datacenters;
	}

	public void setDatacenters(Lista datacenters) {
		this.datacenters = datacenters;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
}
