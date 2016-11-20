/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import dominio.DataCenter;
import dominio.Empresa;
import interfaces.IABB;

/**
 *
 * @author alumnoFI
 */
public class ABB implements IABB {

	private Nodo raiz;
	private String informe;

	// CONSTRUCTOR
	public ABB() {
		this.raiz = null;
		this.informe = "";
	}

	// GETTERS AND SETTERS

	@Override
	public Nodo getRaiz() {
		return raiz;
	}

	// COMPORTAMIENTO

	@Override
	public boolean esArbolVacio() {
		return (raiz == null);
	}

	@Override
	public String devolverInforme() {
		devolverInforme(this.raiz);
		return informe;
	}

	@Override
	public void devolverInforme(Nodo a) {
		if (a != null) {
			devolverInforme(a.getIzq());
			this.informe += a.getDato().toString() + " | ";
			devolverInforme(a.getDer());
		}

	}

	@Override
	public void insertar(Empresa e) {
		raiz = insertar(e, raiz);
	}

	private Nodo insertar(Empresa e, Nodo a) {
		if (a == null) {
			a = new Nodo(e);
		} else if (e.compareTo(a) < 0) {
			a.setIzq(insertar(e, a.getIzq()));
		} else if (e.compareTo(a) > 0) {
			a.setDer(insertar(e, a.getDer()));
		}
		return a;
	}

	public Empresa pertenece(String x) {
		return perteneceRec(x, raiz);
	}

	// Arreglar todo esto
	private Empresa perteneceRec(String x, Nodo nodo) {
		if (nodo == null)
			return null;
		else {
			if (nodo.getDato().getNombre().equals(x))
				return nodo.getDato();
			else {
				if (x.compareTo(nodo.getDato().getNombre()) < 0)
					return perteneceRec(x, nodo.getIzq());
				else
					return perteneceRec(x, nodo.getDer());
			}
		}
	}

}
