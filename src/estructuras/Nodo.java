/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import dominio.Empresa;

/**
 *
 * @author alumnoFI
 */
public class Nodo {
	
	 private Empresa dato;
	    private Nodo der ;
	    private Nodo izq ;
	    
	    //GETTERS AND SETTERS//

	    public Empresa getDato() {
	        return dato;
	    }

	    public void setDato(Empresa dato) {
	        this.dato = dato;
	    }

	    public Nodo getDer() {
	        return der;
	    }

	    public void setDer(Nodo der) {
	        this.der = der;
	    }

	    public Nodo getIzq() {
	        return izq;
	    }

	    public void setIzq(Nodo izq) {
	        this.izq = izq;
	    }
	    

	    //CONSTRUCTOR//
	    public Nodo(Empresa dato) {
	        this.dato = dato;
	    }  
    
}
