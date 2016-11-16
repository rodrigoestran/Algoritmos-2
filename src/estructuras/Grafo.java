package estructuras;

import interfaces.IGrafo;
import interfaces.ILista;

import javax.xml.crypto.Data;

import dominio.Ciudad;
import dominio.DataCenter;
import dominio.Punto;
import estructuras.ListaSEIni;

public class Grafo implements IGrafo{

	private int cantVertices;
	private int tope;
	Arco[][] matrizAdyacencia;
	boolean[] nodosUsados;
	ILista informeRedMinima = new ListaSEIni();	
	Hash vertices; // guarda los objetos DC. Es el unico lugar donde estan guardados
	
	public Grafo(int size) {
		this.cantVertices = 0;
		this.tope = size;
		this.matrizAdyacencia = new Arco[tope+1][tope+1];
		this.vertices = new Hash(tope);
		for (int i = 0; i<=tope; i++)
			for (int j = 0; j<=tope; j++)
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

	public void setInformeRedMinima(ILista informeRedMinima) {
		this.informeRedMinima = informeRedMinima;
	}

	public Hash getVertices() {
		return vertices;
	}

	public void setVertices(Hash vertices) {
		this.vertices = vertices;
	}

	public void busquedaEnProdundidad(int v, boolean[] visitados) {
        visitados[v] = true;
        for (int i = 0; i < tope; i++) {
            if (matrizAdyacencia[v][i].getExiste() && !visitados[i]) {
            	busquedaEnProdundidad(i, visitados);
            }
        }
    }

	public int tieneLugarDisponible() {
		for (int i = 0; i < tope; i++) 
            if (!this.nodosUsados[i]) 
                return i;
        return -1;
	}
	
	public void agregarVertice(Punto p) {
		this.cantVertices++;
		int pos = this.vertices.insertarEnHash(p);
        this.nodosUsados[pos] = true;	
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
		return vertices.posicionPorCoord(p.getCoordX(), p.getCoordY());
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
	// devuelve una lista con los indices de los adyacentes a el objeto en el indice v
	public ILista obtenerVerticesAdyacentes(int v) {
		ListaSEIni l = new ListaSEIni();
		for(int i=0; i<=this.tope; i++){
			if(this.sonAdyacentes(v, i)){
				l.insertarInicio(i);
			}
		}
		return l;
	}

	@Override
	public boolean sonAdyacentes(int a, int b) {
		return this.matrizAdyacencia[a][b].getExiste();
	}

	@Override
	public boolean estaVertice(int v) {
		return this.nodosUsados[v];
	}

	@Override
	public boolean esVacio() {
		return this.cantVertices == 0;
	}

	public String buildMapString(){
    	String mapString = "";
    	for (int i=0; i < vertices.getSizeTable(); i++){
    		Punto p = vertices.getTable()[i];
    		if ( p instanceof Ciudad || p instanceof DataCenter){
	    		if (p instanceof Ciudad)	
	    			mapString += "&markers=color:yellow";
	    		else    			
	    			mapString += "&markers=color:" + ((DataCenter)p).getEmpresa().getColor();
	    		
	    		mapString += "%7Clabel:" + p.getNombre() + 
	    					 "%7C" + p.getCoordX() + "," + p.getCoordY() ;
    		}
    	}
    	return mapString;
    }
	
	public int devolverDistancia(int x, int y) {
		if (this.matrizAdyacencia[x][y] != null) 
            return this.matrizAdyacencia[x][y].getDistancia();
        return 0;
	}
		
	// Pre: El grafo no est� vac�o y es no direccionado
	// Pos: Objeto ILista con conjunto de Arcos unicos que tienen origen y destino para trazar tramos
	public ILista actualizarRedMinima(){
		//Inicializacion:
		// No creamos matriz auxiliar porque no va a sustituir a la original, y no la necesitamos como tal.
		// en vez de eso devolvemos una lista de arcos
		informeRedMinima.vaciarLista();
		boolean[] visitados = new boolean[tope]; 
		visitados[0] = true; 
		
		//Proceso
		//Inicializar valor maximo (MAX_VALUE), y coordenadas de arista candidata
		for (int k = 0; k < tope-1 ; k++) {
			int costo = Integer.MAX_VALUE; // cambie esto
			int iCandidato = -1;
			int jCandidato = -1;
			for (int i = 0; i < tope; i++) {
				if (vertices.getTable()[i] != null && visitados[i]){
					for (int j = 0; j < tope; j++) {
						if (matrizAdyacencia[i][j].getExiste()){ // si es candidato (une visitado con no visitado)
							if (!visitados[j]){
								int costoAux = matrizAdyacencia[i][j].getDistancia();
								if (costo > costoAux ){ // y si es mejor que mi anterior candidato, sustituyo mi mejor cand.
									costo = costoAux;
									iCandidato = i;
									jCandidato = j;
								}
							}
						}
					}
				}
			}

			// agrego arista bidireccional a partir del valor minimo y las coordenadas
			// y pongo como visitado a j
			if (jCandidato != -1){
				visitados[jCandidato] = true;
				informeRedMinima.insertarInicio(matrizAdyacencia[iCandidato][jCandidato]);
			}
		}		
		return informeRedMinima;
	}
	
	
	public DataCenter buscarDC(String nombre){
		this.vertices.buscar(nombre);
		return null;
	}

	public boolean estaVertice(Double coordX, Double coordY) {
		return vertices.perteneceAHash(coordX, coordY);
	}

	public Punto buscarPunto(Double x, Double y) {
		Punto p = null;
		int pos = this.vertices.posicionPorCoord(x, y);
		if (pos != -1) {
			p = this.vertices.puntoPorPosicion(pos);
		}
		return p;
	}

	public boolean existe(Double coordX, Double coordY) {
		return vertices.perteneceAHash(coordX, coordY);
	}

	public Punto obtenerPunto(Double coordX, Double coordY) {
		int posicion = vertices.posicionPorCoord(coordX, coordY); 
		if (posicion != -1)
			return vertices.puntoPorPosicion(posicion);
		return null;
	}




}
