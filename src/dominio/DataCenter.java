/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;


/**
 *
 * @author alumnoFI
 */
public class DataCenter extends Punto{
	
	private String nombre; 
	private	Empresa empresa;
	private int capacidadCPUenHoras;
	private int costoCPUporHora;
	private int esfuerzoEnUso;
	
	
	// GETTERS AND SETTERS //
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
	
	public int getEsfuerzoEnUso() {
		return esfuerzoEnUso;
	}
	public void setEsfuerzoEnUso(int esfuerzoEnUso) {
		this.esfuerzoEnUso = esfuerzoEnUso;
	}
	
	
	// CONSTRUCTOR //
	public DataCenter(String n,Empresa emp, int capCPRHoras, int costCPUHora, Double coordX, Double coordY)
	{
		super(coordX, coordY);
		this.nombre = n;
		this.empresa = emp;
		this.capacidadCPUenHoras = capCPRHoras;
		this.costoCPUporHora = costCPUHora;
		
	}
	 
	@Override
	public String toString() {
	    return this.nombre+";"+this.empresa+";"+ super.getCoordX()+";"+ super.getCoordY();
	}   
    
}
