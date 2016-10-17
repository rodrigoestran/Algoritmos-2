/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dominio.DataCenter;
import estructuras.Nodo;

/**
 *
 * @author alumnoFI
 */
public interface IABB {
	
		int altura();

	    Nodo borrarMinimo(Nodo nodo);

	    int cantHojas(Nodo nodo);

	    int cantNodos(Nodo nodo);

	    boolean esArbolVacio();

	    boolean existe(String s, Nodo a);

	    boolean existeElemento(String s);

	    Nodo getRaiz();

	    void insertar(DataCenter dc);

	    void insertarElemento(DataCenter dc, Nodo nodo);

	    void mostrarInOrder();

	    void mostrarInOrder(Nodo a);

	    void mostrarPosOrder();

	    void mostrarPosOrder(Nodo a);

	    void mostrarPreOrder();

	    void mostrarPreOrder(Nodo a);

	    Nodo obtenerElemento(String s, Nodo nodo);

	    int obtenerPeso(Nodo nodo);
	    void devolverInforme();
	    void devolverInforme(Nodo a) ;
    
}
