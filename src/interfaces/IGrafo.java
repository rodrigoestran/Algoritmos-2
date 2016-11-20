/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import estructuras.Hash;

/**
 *
 * @author alumnoFI
 */
public interface IGrafo {
	
	public void agregarVertice(Object p);
	
	public Hash getVertices();

	public void agregarArista(int peso, Object inicio, Object fin);

	public void eliminarVertice(int v);

	public void eliminarArista(Object ini, Object fin);
	
	public boolean existeArista(Object ini, Object fin);

	public ILista obtenerVerticesAdyacentes(int v);
 
	public boolean sonAdyacentes(int a, int b);
    
	public boolean estaVertice(int v);
	
	public boolean esVacio();
	
	public int devolverDistancia(int x, int y);
	
	public ILista actualizarRedMinima();
	
	public int tieneLugarDisponible();
}
