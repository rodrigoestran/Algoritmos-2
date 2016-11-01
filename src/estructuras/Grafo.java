package estructuras;

import interfaces.IGrafo;
import interfaces.ILista;
import estructuras.Lista;

public class Grafo implements IGrafo{

	private int size;
	private int cantNodos;
	Arco[][] matrizAdyacencia;
	boolean[] nodosUsados;
	
	public Grafo(int size) {
		this.size = size;
		this.matrizAdyacencia = new Arco[cantNodos+1][cantNodos+1];
		for (int i = 1; i<=cantNodos; i++)
			for (int j = 1; j<=cantNodos; j++)
			  this.matrizAdyacencia[i][j]= new Arco();
				
		this.nodosUsados = new boolean[cantNodos+1];

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCantNodos() {
		return cantNodos;
	}

	public void setCantNodos(int cantNodos) {
		this.cantNodos = cantNodos;
	}

	public boolean hayLugar() {
		// TODO Auto-generated method stub
		return false;
	}

	public void agregarVertice(int pos) {
		cantNodos++;
		// TODO Auto-generated method stub
		
	}

	public boolean existeArista(int posicionIni, int posicionFin, Double coordXi, Double coordYi, Double coordXf,
			Double coordYf) {
		// TODO Auto-generated method stub
		return false;
	}

	public void agregarArista(int posicionIni, int posicionFin, int peso, Double coordXi, Double coordYi,
			Double coordXf, Double coordYf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearGrafo(int cantMax) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean esConexo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eliminarVertice(int v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarArista(int origen, int destino) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ILista obtenerVerticesAdyacentes(int v) {
		Lista l = new Lista();
		for(int i=1; i<=this.cantNodos; i++){
			if(this.sonAdyacentes(v, i)){
				l.insertarInicio(i);
			}
		}
		return l;
	}

	@Override
	public boolean sonAdyacentes(int a, int b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaVertice(int v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esVacio() {
		// TODO Auto-generated method stub
		return false;
	}

	public int devolverDistancia(int vmd, int u) {
		// TODO Auto-generated method stub
		return 0;
	}

}
