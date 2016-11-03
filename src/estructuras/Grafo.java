package estructuras;

import interfaces.IGrafo;
import interfaces.ILista;
import dominio.DataCenter;
import dominio.Punto;
import estructuras.Lista;

public class Grafo implements IGrafo{

	private int cantVertices;
	private int tope;
	Arco[][] matrizAdyacencia;
	boolean[] nodosUsados;
	Hash vertices; // guarda los objetos DC. Es el unico lugar donde estan guardados
	
	public Grafo(int size) {
		this.cantVertices = size;
		this.matrizAdyacencia = new Arco[tope+1][tope+1];
		for (int i = 1; i<=tope; i++)
			for (int j = 1; j<=tope; j++)
			  this.matrizAdyacencia[i][j]= new Arco();
				
		this.nodosUsados = new boolean[tope+1];

	}

	public int getSize() {
		return cantVertices;
	}

	public void setSize(int size) {
		this.cantVertices = size;
	}

	public int getCantNodos() {
		return tope;
	}

	public void setCantNodos(int cantNodos) {
		this.tope = cantNodos;
	}
	
	

//	public boolean hayLugar() {
//		// TODO Auto-generated method stub
//		return false;
//	}

	public Hash getVertices() {
		return vertices;
	}

	public void setVertices(Hash vertices) {
		this.vertices = vertices;
	}

	public void busquedaEnProdundidad(int v, boolean[] visitados) {
        visitados[v] = true;
        for (int i = 0; i < tope; i++) {
            if (matrizAdyacencia[v][i].isExiste() && !visitados[i]) {
            	busquedaEnProdundidad(i, visitados);
            }
        }
    }

	// modifique boolean por int, para que devielva directamente la posicion que tiene libre
	public int tieneLugarDisponible() {
		for (int i = 0; i < tope; i++) {
            if (!this.nodosUsados[i]) {
                return i;
            }
        }
        return -1;
	}
	
	public void agregarVertice(int pos, Punto p) {
		this.cantVertices++;
        this.nodosUsados[pos] = true;		
        /// esto hay que arrglarlo porque no se como se obtiene luego la relacion con el hash 
        this.vertices.insertarEnHash(p);
	}

	public boolean existeArista(Punto ini, Punto fin) {
		int indIni = obtenerPosicion(ini);
		int indFin = obtenerPosicion(fin);
		if (matrizAdyacencia[indIni][indFin].getInicio() == null) return false;
		return true;
	}

	public void agregarArista(int peso, Punto inicio, Punto fin) {
		Arco nuevo = new Arco(peso, inicio, fin);
		int indIni = obtenerPosicion(inicio);
		int indFin = obtenerPosicion(fin);
		this.matrizAdyacencia[indIni][indFin] = nuevo;
        this.matrizAdyacencia[indFin][indIni] = nuevo;
	}

	@Override
	public void crearGrafo(int cantMax) {
		// TODO Auto-generated method stub
		
	}
	
	private int obtenerPosicion(Punto p){
		return vertices.posicionActual(p.getCoordX(), p.getCoordY());
	}

	@Override
	public boolean esConexo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eliminarVertice(int v) {
		this.nodosUsados[v] = false;
        this.cantVertices--;

        for (int i = 1; i <= this.tope; i++) {
            this.matrizAdyacencia[i][v] = new Arco();
            this.matrizAdyacencia[v][i] = new Arco();
        }
				
	}

	@Override
	public void eliminarArista(Punto ini, Punto fin) {
		int origen = obtenerPosicion(ini);
		int destino = obtenerPosicion(fin);
		this.matrizAdyacencia[origen][destino] = new Arco();
		this.matrizAdyacencia[destino][origen] = new Arco();
				
	}

	@Override
	public ILista obtenerVerticesAdyacentes(int v) {
		Lista l = new Lista();
		for(int i=1; i<=this.tope; i++){
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
		return this.nodosUsados[v];
	}

	@Override
	public boolean esVacio() {
		return this.cantVertices == 0;
	}

	public int devolverDistancia(int x, int y) {
		 if (this.matrizAdyacencia[x][y] != null) {
	            return this.matrizAdyacencia[x][y].getDistancia();
	        }
	        return 0;
	}
	
	//moira
	public void actualizarRedMinima(){
		//Inicializacion
		//va a usarlo para ir revisando en estas posiciones si ya pasó
		boolean[] visitados = new boolean[tope]; 
		visitados[0] = true; //por ejemplo
		Arco[][] aux = new Arco[tope][tope]; //va a ser la nueva matriz
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux.length; j++) {
				aux[i][j] = new Arco();
			}
		}
		//Proceso
		//Inicializar valor maximo (MAX_VALUE), y coordenadas de arista candidata
		for (int k = 0; k < tope-1 ; k++) {
			for (int i = 0; i < aux.length; i++) {
				for (int j = 0; j < aux.length; j++) {
					
					// si es candidato (une visitado con no visitado)
					// y si es mejor que mi anterior candidato, sustituyo mi mejor cand.
				}
			}
			// agrego arista bidireccional a partir del valor minimo y las coordenadas
			// y pongo como visitado a j
		}
		matrizAdyacencia = aux;
		
	}
	
	public ILista transformaraListaTramos(){
		ILista lista = new Lista();
		for (int i=0; i<matrizAdyacencia.length; i++){
			for (int j = 0; j < matrizAdyacencia.length; j++) {

			}
		}
		return lista;
	}
	
	public DataCenter buscarDC(String nombre){
		this.vertices.buscar(nombre);
		return null;
	}

	public String getInforme() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean estaVertice(Double coordX, Double coordY) {
		return vertices.perteneceAHash(coordX, coordY);
	}

	public Punto buscarPunto(Double x, Double y) {
		Punto p = null;
		int pos = this.vertices.posicionActual(x, y);
		if (pos != -1) {
			p = this.vertices.puntoPorPosicion(pos);
		}
		return p;
	}

	public boolean existe(Double coordX, Double coordY) {
		return vertices.perteneceAHash(coordX, coordY);
	}

	public Punto obtenerPunto(Double coordX, Double coordY) {
		int posicion = vertices.posicionActual(coordX, coordY); 
		if (posicion != -1)
			return vertices.puntoPorPosicion(posicion);
		return null;
	}




}
