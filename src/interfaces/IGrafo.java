/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dominio.Punto;
import estructuras.Arco;
import estructuras.Hash;

/**
 *
 * @author alumnoFI
 */
public interface IGrafo {
	
	public void agregarVertice(Punto p);

	public void agregarArista(int peso, Object inicio, Object fin);

	public void eliminarVertice(int v);

	public void eliminarArista(Object ini, Object fin);
	
	public boolean existeArista(Punto ini, Punto fin);

	public ILista obtenerVerticesAdyacentes(int v);
 
	public boolean sonAdyacentes(int a, int b);

	Hash getVertices();

	ILista actualizarRedMinima();

	public int devolverDistancia(int verticeAux, int p);

	int tieneLugarDisponible();

	public Arco[][] getMatrizAdyacencia();
}
