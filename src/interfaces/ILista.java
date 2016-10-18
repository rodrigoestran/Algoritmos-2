/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author alumnoFI
 */
public interface ILista {


	void insertarInicio(Object elem);
	
	void insertarFin(Object elem);
	
	void borrarInicio();

	void borrarFin();
	
	boolean esVacia();
	
	void vaciarLista();	
	
	void borrarUnElem(Object elem);
	
	int largo();
	
	boolean existe(Object elem);
    
}
