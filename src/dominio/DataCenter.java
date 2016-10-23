/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import estructuras.Nodo;

/**
 *
 * @author alumnoFI
 */
public class DataCenter {
	
	private String nombre; 
	private	Empresa empresa;
	private int capacidadCPUenHoras;
	private int costoCPUporHora;
	private Ubicacion ubicacion;
	private int esfuerzoEnUso;
	
	
	//GETTERS AND SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public int getCapacidadCPUenHoras() {
		return capacidadCPUenHoras;
	}
	public void setCapacidadCPUenHoras(int capacidadCPUenHoras) {
		this.capacidadCPUenHoras = capacidadCPUenHoras;
	}
	public int getCostoCPUporHora() {
		return costoCPUporHora;
	}
	public void setCostoCPUporHora(int costoCPUporHora) {
		this.costoCPUporHora = costoCPUporHora;
	}	

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public int getEsfuerzoEnUso() {
		return esfuerzoEnUso;
	}
	public void setEsfuerzoEnUso(int esfuerzoEnUso) {
		this.esfuerzoEnUso = esfuerzoEnUso;
	}
	
	
	//CONSTRUCTORS
	public DataCenter(String n,Empresa emp, int capCPRHoras, int costCPUHora)
	{
		this.nombre = n;
		this.empresa = emp;
		this.capacidadCPUenHoras = capCPRHoras;
		this.costoCPUporHora = costCPUHora;
		this.ubicacion = null;
		
	}
	
	
	//compare to
	 public int compareTo(Nodo o) {
	        DataCenter nuevo = o.getDato();
	        return this.nombre.compareTo(nuevo.getNombre());
	    }
	 
	 @Override
	    public String toString() {
	        if(this.ubicacion!=null){
	            return this.nombre+";"+this.empresa+";"+this.ubicacion.getCoordX()+";"+this.ubicacion.getCoordY();
	        }else{
	            return  this.nombre+";"+this.empresa;
	        }
	    }   
    
}
