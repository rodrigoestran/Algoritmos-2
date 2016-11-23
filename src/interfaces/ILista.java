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

public interface ILista{

	void insertarInicio(Object elem);
		
	boolean esVacia();
	
	public void vaciarLista();	
	
	NodoLista getInicio();
	
	String[] informe();
    
}
