/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dominio.Punto;

/**
 *
 * @author alumnoFI
 */
public interface IGrafo {

		
	public void agregarVertice(Punto p);

	public void agregarArista(int peso, Punto inicio, Punto fin);

	public void eliminarVertice(int v);

	public void eliminarArista(Punto ini, Punto fin);

	public ILista obtenerVerticesAdyacentes(int v);
 
	public boolean sonAdyacentes(int a, int b);

    
}
