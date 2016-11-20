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
	
		
	    boolean esArbolVacio();  

	    Nodo getRaiz();

	    void insertar(Empresa e);	    
	    
	    String devolverInforme();
	    
	    void devolverInforme(Nodo a) ;
    
}
