/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dominio.Empresa;
import estructuras.Nodo;

/**
 *
 * @author alumnoFI
 */
public interface IABB {
	
	    Nodo borrarMinimo(Nodo nodo);

	    int cantHojas(Nodo nodo);

	    int cantNodos(Nodo nodo);

	    boolean esArbolVacio();

	    boolean existe(String s, Nodo a);

	    boolean existeElemento(String s);

	    Nodo getRaiz();

	    void insertar(Empresa e);

	    void insertarElemento(Empresa e, Nodo nodo);

	    void mostrarPosOrder();

	    void mostrarPosOrder(Nodo a);

	    void mostrarPreOrder();

	    void mostrarPreOrder(Nodo a);

	    Nodo obtenerElemento(String s, Nodo nodo);

	    int obtenerPeso(Nodo nodo);
	    
	    String devolverInforme();
	    
	    void devolverInforme(Nodo a) ;
	    
	    public Nodo Buscar(String s);
	    
	    public Nodo Buscar(String s, Nodo n);
	    
	    public Empresa pertenece(String x);
}
