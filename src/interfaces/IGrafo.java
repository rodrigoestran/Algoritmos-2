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
public interface IGrafo {

	public void crearGrafo(int cantMax);
	
	public boolean esConexo();
	
	public void agregarVertice(int v);

	public void agregarArista(int origen, int destino, int peso, Double coordXi, Double coordYi, Double coordXf,         Double coordYf);

	public void eliminarVertice(int v);

	public void eliminarArista(int origen, int destino);

	public Lista obtenerVerticesAdyacentes(int v);
 
	public boolean sonAdyacentes(int a, int b);

	public boolean estaVertice(int v);

	public boolean esVacio();
    
}
