package estructuras;

import dominio.DataCenter;
import dominio.Empresa;
import dominio.Punto;
import interfaces.ILista;

public class Dijkstra {
	
	private Grafo grafo;
	//array para guardar distancias visitadas
    private int[] distancias;
    //array para guardar puntos visitados
    private int[] previos;
    //array para guardar estado
    private  boolean[] visitados;
    
    private int[] listaDCcapable;
    
    // Para ir recordando los mejores. Creo que no deberia necesitarse
    private Hash hash;
    private DataCenter dataCenter;
    private Punto punto;
    private int DESTINO;
    private int origen;
    private int costoActual;
    // Getters y setters //
    public int[] getDistancia() {
        return distancias;
    }
   
    public void setDistancia(int[] d) {
        distancias = d;
    }
   
    public int[] getPrevio() {
        return previos;
    }
   
    public void setPrevio(int[] p) {
        previos = p;
    }
   
    public boolean[] getVisitado() {
        return visitados;
    }
    
    public void setVisitado(boolean[] aVisitado) {
        visitados = aVisitado;
    }
    
    public int getDESTINO() {
        return DESTINO;
    }
   
    public void setDESTINO(int d) {
        DESTINO = d;
    }
    
    public int getORIGEN() {
        return origen;
    }

    public void setORIGEN(int o) {
        origen = o;
    }
  
    public Grafo getGrafo() {
        return grafo;
    }
   
    public void setGrafo(Grafo g) {
        grafo = g;
    }

    public Hash getHash() {
        return hash;
    }
   
    public void setHash(Hash h) {
        hash = h;
    }
    
    public  DataCenter getDataCenter() {
        return dataCenter;
    }
    
    public void setDataCenter(DataCenter dc) {
        dataCenter = dc;
    }
    
    public Punto getPunto() {
        return punto;
    }
   
    public void setPunto(Punto aPunto) {
        punto = aPunto;
    }
    
	public int getCostoActual() {
		return costoActual;
	}

	public void setCostoActual(int costoActual) {
		this.costoActual = costoActual;
	}

	public void dijkstra(Grafo mapa, Hash ubicaciones, int origen, int esfuerzoCPUrequeridoEnHoras) {
		// Inicializo
	    this.grafo = mapa;
        this.distancias = new int[ubicaciones.getSizeTable()]; // la distancia mas corta partiendo del origen
        this.previos = new int[ubicaciones.getSizeTable()]; // el nodo previo en el camino.
        this.visitados = new boolean[ubicaciones.getSizeTable()]; //nodos visitados.
        this.origen = origen;
        this.hash = ubicaciones;
        setDataCenter(null);

        // pongo todas las distancias en max value
        // seteo visitados = false.
        for (int i = 0; i < getDistancia().length; i++) {
        	this.distancias[i] = Integer.MAX_VALUE;
            this.visitados[i] = false;
            this.previos[i] = -1;
        }
        
     

        this.distancias[origen] = 0; //distancia 0 entre orig y orig.
        this.visitados[origen] = true; //el origen queda como vistado
        
        //llamo al recursivo que devuelve un dc. Viendo lo que hicieron en clase esto es diferente, 
        //en clase devuelven un int que es la distancia a la posicion destino
        dijkstraREC(origen, esfuerzoCPUrequeridoEnHoras);
	
	}
	
	public void dijkstraREC(int p, int esfuerzoCPUrequeridoEnHoras) {
	    // Busco vertices adyacentes del origen
	    ILista l = obtenerAdyacentes(p);
	
	    // saco el vertice que está a menor distancia
	    int vertMenorDist = obtenerVerticeMenorDistancia(l, p);
	    // si aun sigue existiendo un vertice no visitado, sino ahi es cuando da -1
	    if (vertMenorDist != -1){
	        //p = this.previos[vertMenorDist]; // esto es redundante. Cuando llega aca ya estaba seteado
	        //int peso = grafo.devolverDistancia(vertMenorDist, p);
	
	        //Modifico listas con el punto que seleccioné
	        this.visitados[vertMenorDist] = true;
	        //this.previos[vertMenorDist] = p;
	        //this.distancias[vertMenorDist] = this.distancias[p] + peso; // redundante?
	
	        //punto = hash.puntoPorPosicion(vertMenorDist);
	        dijkstraREC(vertMenorDist, esfuerzoCPUrequeridoEnHoras);
	    }
	    else
	    {
	    	int indice = -1;
	    	this.visitados[p] = true;
	    	for(int i = 0; i<visitados.length; i++)
	    	{
	    		if(!visitados[i] && hash.getTable()[i]!= null && !this.obtenerAdyacentes(i).esVacia())
	    		{
	    			indice = i;
	    			break;
	    		}
	    	}
	    	if(indice != -1)
	    	{
	    		dijkstraREC(indice, esfuerzoCPUrequeridoEnHoras);
	    	}  	
	    }
	}

	private int obtenerVerticeMenorDistancia(ILista l, int p) {
		int verticeMenor = -1;
		int verticeAux = 0;
     	int pesoaux = 0;
     	int pesoMenor = Integer.MAX_VALUE;
     	NodoLista aux = l.getInicio();
	
     	//recorro la lista de vertices adyacentes y seteo sus nuevas distancias y previos
     	while (aux != null) {
     		verticeAux = (Integer) aux.getDato();
     		// si este vertice actual no fue ya visitado
     		//if (!this.visitados[verticeAux]) {
     			// guardo la distancia entre el vertice actual y el punto p
     			pesoaux = this.grafo.devolverDistancia(verticeAux, p);
     			// si la distancia alcanzada hasta p más la distancia entre p y el vertice actual es 
     			// menor que la distancia que habia hasta el momento en el vertice actual
     			if (this.distancias[p] + pesoaux < this.distancias[verticeAux]) {
     				this.previos[verticeAux] = p;
     				this.distancias[verticeAux] = this.distancias[p] + pesoaux;
     				if (this.distancias[verticeAux] < pesoMenor){ //&& !this.visitados[verticeAux]){
     					pesoMenor = this.distancias[verticeAux];
     					verticeMenor = verticeAux;
     				}
     				
     			}           
     		//} 
     		aux = aux.getSig();
	     }
	     return verticeMenor;
	}

	private ILista obtenerAdyacentes(int u) {
		 return getGrafo().obtenerVerticesAdyacentes(u);
	}
	
	// Hace la busqueda dentro de la lista de distancias. Considera las distancias sumadas al costoCPUhoras, si es de otra empresa,
	// y se queda con el que tiene menor costo overal, pero solo dentro de los que pertenecen a una lista precreada solo con los DC
	// que tienen la capacidad de procesamiento necesaria.
	public DataCenter obtenerDCcapableConMenorDistancia(Empresa eOrigen, int esfRequeridoEnHs){
		int cantDeCapable = obtenerDCsCapable(esfRequeridoEnHs);
		int distMin = Integer.MAX_VALUE;
		DataCenter dcTemp = null;
		for (int i=0; i< this.distancias.length; i++){
			boolean hayCapable = false;
			for (int j = 0; j <= cantDeCapable-1; j++){
				if (i == listaDCcapable[j]) {
					hayCapable = true;
					break;
				}
			}
			if (!hayCapable) continue;
			int costo = distancias[i];
			// Significa que no es el mismo origen, que cuesta 0 en distancia
			if (costo != 0){
				DataCenter candidato = (DataCenter)this.hash.getTable()[i];
				if ( !candidato.getEmpresa().equals(eOrigen) ) costo += esfRequeridoEnHs*candidato.getCostoCPUporHora();
				if( costo < distMin ) {
					distMin = costo;
					dcTemp = candidato;
				}
			}
		}
		this.dataCenter = dcTemp;
		this.costoActual = distMin;
		return dcTemp;
	}
	
	// guarda la lista como atributo, y devuelve el tamaño de este array
	public int obtenerDCsCapable(int capMin){
		//de todo el grafo, busco los DC con capacidad mayor a la que necesito y las guardo sus indices en una lista
		listaDCcapable = new int[this.distancias.length];
		// inicializo todas las posiciones en -1 para que no se confunda
		for (int i = 0; i < distancias.length; i ++){
			listaDCcapable[i] = -1;
		}
		int contador = 0;
		for (int i = 0; i< hash.getSizeTable(); i++){
			Punto puntoEnHash = hash.getTable()[i];
			if (puntoEnHash instanceof DataCenter){
				DataCenter dcEnHash = (DataCenter) puntoEnHash;
				if (dcEnHash.getCapacidadCPUenHoras() >= capMin) {
					listaDCcapable[contador] = i;
					contador++;
				}
			}
		}
		return contador;
	}

}
