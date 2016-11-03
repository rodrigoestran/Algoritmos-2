package estructuras;

import dominio.DataCenter;
import dominio.Punto;
import interfaces.ILista;

public class DjikstraDCMasProximo {
	
	private static Grafo grafo;
    private static int[] distancia;
    private static int[] previo;
    private static boolean[] visitado;
    private static Hash hash;
    private static DataCenter dataCenter;
    private static Punto punto;
    private static int DESTINO;
    private static int ORIGEN;
   
    public static int[] getDistancia() {
        return distancia;
    }
   
    public static void setDistancia(int[] d) {
        distancia = d;
    }
   
    public static int[] getPrevio() {
        return previo;
    }
   
    public static void setPrevio(int[] p) {
        previo = p;
    }
   
    public static boolean[] getVisitado() {
        return visitado;
    }
    
    public static void setVisitado(boolean[] aVisitado) {
        visitado = aVisitado;
    }
    
    public static int getDESTINO() {
        return DESTINO;
    }
   
    public static void setDESTINO(int d) {
        DESTINO = d;
    }
    
    public static int getORIGEN() {
        return ORIGEN;
    }

    public static void setORIGEN(int o) {
        ORIGEN = o;
    }
  
    public static Grafo getGrafo() {
        return grafo;
    }
   
    public static void setGrafo(Grafo g) {
        grafo = g;
    }

    public static Hash getHash() {
        return hash;
    }
   
    public static void setHash(Hash h) {
        hash = h;
    }
    
    public static DataCenter getDataCenter() {
        return dataCenter;
    }
    
    public static void setDataCenter(DataCenter dc) {
        dataCenter = dc;
    }
    
    public static Punto getPunto() {
        return punto;
    }
   
    public static void setPunto(Punto aPunto) {
        punto = aPunto;
    }

	public DataCenter dijkstra(Grafo mapa, Hash ubicaciones, int u) {
	    setGrafo(mapa);
        setDistancia(new int[ubicaciones.getSizeTable()]); // la distancia mas corta partiendod el origen
        setPrevio(new int[ubicaciones.getSizeTable()]); // el nodo previo en el camino.
        setVisitado(new boolean[ubicaciones.getSizeTable()]); //nodos visitados.
        setORIGEN(u);//el inicio
        DjikstraDCMasProximo.setHash(ubicaciones);
        setDataCenter(null);

        //aca se inicializa
        //se pone todas las distancias en max value
        //se setea visitados a false.
        for (int i = 0; i < getDistancia().length; i++) {
            getDistancia()[i] = Integer.MAX_VALUE;
            getVisitado()[i] = false;
        }

        getDistancia()[u] = 0; //distancia 0 entre orig y orig.
        getVisitado()[u] = true; //el origen queda como vistado

        DataCenter dc = dijkstra(u);
        return dc;
	
	}
	
	public DataCenter dijkstra(int u) {
	    boolean notFound = true;
	    DataCenter dc = null;
	    //Busco vertices adyacentes del origen
	    ILista l = obtenerAdyacentes(u);
	
	    //punto de menor distancia
	    int vertMenorDist = obtenerVerticeMenorDistancia(l, u);
	
	    if (vertMenorDist != -1) {
	        u = getPrevio()[vertMenorDist];
	        int peso = getGrafo().devolverDistancia(vertMenorDist, u);
	
	        //Modifico listas con el punto que selecciono
	        getVisitado()[vertMenorDist] = true;
	        getPrevio()[vertMenorDist] = u;
	        getDistancia()[vertMenorDist] = getDistancia()[u] + peso;
	
	        setPunto(getHash().puntoPorPosicion(vertMenorDist));
	        setDataCenter(devolverDataCenter(getPunto()));
	
	        if (getDataCenter() != null) {	                
	                notFound = false;
	                dc = getDataCenter();         
	        }
		        
	        if (notFound) {
	            dijkstra(vertMenorDist);
	        }
	    }
        return dc;
	 }

	 private DataCenter devolverDataCenter(Punto punto2) {
		 return (DataCenter)punto2; // cambié esto
	 }


	private int obtenerVerticeMenorDistancia(ILista l, int u) {
		int verticeMenor = -1;
		int vertice = 0;
     	int pesoaux = 0;
     	int pesoMenor = Integer.MAX_VALUE;
     	NodoLista aux = l.getInicio();
	
     	while (aux != null) {
     		vertice = (Integer) aux.getDato();
     		if (!getVisitado()[vertice]) {
     			pesoaux = getGrafo().devolverDistancia(vertice, u);
     			if (getDistancia()[u] + pesoaux < getDistancia()[vertice]) {
     				getPrevio()[vertice] = u;
     				getDistancia()[vertice] = getDistancia()[u] + pesoaux;
     			}            
     		} 
     		aux = aux.getSig();
	     }
	     for(int i = 0; i< getDistancia().length;i++){
	         if(getDistancia()[i]<pesoMenor && !getVisitado()[i]){
	             verticeMenor = i;
	             pesoMenor = getDistancia()[i];
	         }
	     }
	     return verticeMenor;
	}

	private ILista obtenerAdyacentes(int u) {
		 return getGrafo().obtenerVerticesAdyacentes(u);
	}

}
