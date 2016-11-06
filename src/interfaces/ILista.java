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


	void insertarInicio(Object elem);
		
	void borrarInicio();
	
	boolean esVacia();
	
	void vaciarLista();	
	
	int largo();
	
	NodoLista getInicio();
	
	boolean existe(Object elem);

	String informe();
    
}
