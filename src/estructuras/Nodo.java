/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import dominio.DataCenter;

/**
 *
 * @author alumnoFI
 */
public class Nodo {
	
	 private DataCenter dato;
	    private Nodo der ;
	    private Nodo izq ;

	    public DataCenter getDato() {
	        return dato;
	    }

	    public void setDato(DataCenter dato) {
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

	    public Nodo(DataCenter dato) {
	        this.dato = dato;
	    }

	    boolean remove(String valor, Nodo aux) {
	        if (this.getDato().getNombre().equals(valor)) {
	                  if (izq != null)
	                        return izq.remove(valor, this);
	                  else
	                        return false;
	            } else if (this.getDato().getNombre().compareTo(valor) > 0) {
	                  if (der != null)
	                        return der.remove(valor, this);
	                  else
	                        return false;
	            } else {
	                  if (izq != null && der != null) {
	                        this.dato = der.minValue();
	                        der.remove(this.getDato().getNombre(), this);
	                  } else if (aux.getIzq().equals(this)) {
	                        aux.setIzq((izq != null) ? izq : der);
	                  } else if (aux.getDer().equals(this)) {
	                        aux.setDer((izq != null) ? izq : der);
	                  }
	                  return true;
	    }

	    }
	    
	     public DataCenter minValue() {
	            if (izq == null)
	                  return dato;
	            else
	                  return izq.minValue();
	      }
    
}
