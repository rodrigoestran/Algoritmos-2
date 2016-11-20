/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import estructuras.NodoLista;

/**
 *
 * @author alumnoFI
 */
public interface ILista extends Iterable<Object>{

	public void insertarInicio(Object elem);
		
	public void borrarInicio();
	
	public boolean esVacia();
	
	public void vaciarLista();	
	
	public int largo();
	
	public NodoLista getInicio();
	
	public String informe();
    
}
