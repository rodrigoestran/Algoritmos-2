package estructuras;

import dominio.DataCenter;
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
	    
	    

	    /**
	     * @return  distancia
	     */
	    public static int[] getDistancia() {
	        return distancia;
	    }

	    /**
	     * @param seteo la distancia.
	     */
	    public static void setDistancia(int[] d) {
	        distancia = d;
	    }

	    /**
	     * @return previo
	     */
	    public static int[] getPrevio() {
	        return previo;
	    }

	    /**
	     * @param setear el previo.
	     */
	    public static void setPrevio(int[] p) {
	        previo = p;
	    }

	    /**
	     * @return the visitado
	     */
	    public static boolean[] getVisitado() {
	        return visitado;
	    }

	    /**
	     * @param aVisitado the visitado to set
	     */
	    public static void setVisitado(boolean[] aVisitado) {
	        visitado = aVisitado;
	    }

	    /**
	     * @return retorna el destino
	     */
	    public static int getDESTINO() {
	        return DESTINO;
	    }

	    /**
	     * @param setea el destino.
	     */
	    public static void setDESTINO(int d) {
	        DESTINO = d;
	    }

	    /**
	     * @return devuelve el origen.
	     */
	    public static int getORIGEN() {
	        return ORIGEN;
	    }

	    /**
	     * @param o el origen que se quiere setear
	     */
	    public static void setORIGEN(int o) {
	        ORIGEN = o;
	    }

	    /**
	     * @return devuelve un grafo
	     */
	    public static Grafo getGrafo() {
	        return grafo;
	    }

	    /**
	     * @param g es el grafo a setear
	     */
	    public static void setGrafo(Grafo g) {
	        grafo = g;
	    }

	    /**
	     * @return un hash
	     */
	    public static Hash getHash() {
	        return hash;
	    }

	    /**
	     * @param h es el hash a setear.
	     */
	    public static void setHash(Hash h) {
	        hash = h;
	    }

	    /**
	     * @return un data center
	     */
	    public static DataCenter getDataCenter() {
	        return dataCenter;
	    }

	    /**
	     * @param dc el movil a setear
	     */
	    public static void setDataCenter(DataCenter dc) {
	        dataCenter = dc;
	    }

	    /**
	     * @return el punto
	     */
	    public static Punto getPunto() {
	        return punto;
	    }

	    /**
	     * @param el punto a setear
	     */
	    public static void setPunto(Punto aPunto) {
	        punto = aPunto;
	    }
	
	
	
	
	
	
	

	public DataCenter dijkstra(Grafo mapa, Hash ubicaciones, int u) {
		    setGrafo(mapa);
	        setDistancia(new int[ubicaciones.getSizeTabla()]); // la distancia mas corta partiendod el origen
	        setPrevio(new int[ubicaciones.getSizeTabla()]); // el nodo previo en el camino.
	        setVisitado(new boolean[ubicaciones.getSizeTabla()]); //nodos visitados.
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

	        //vertice de menor distancia
	        int vertMenorDist = obtenerVerticeMenorDistancia(l, u);

	        if (vertMenorDist != -1) {
	            u = getPrevio()[vertMenorDist];
	            int peso = getGrafo().devolverDistancia(vertMenorDist, u);

	            //Modifico listas con el punto que selecciono
	            getVisitado()[vertMenorDist] = true;
	            getPrevio()[vertMenorDist] = u;
	            getDistancia()[vertMenorDist] = getDistancia()[u] + peso;

	            setPunto(getHash().devolverPuntoPorPosicion(vertMenorDist));
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
		// TODO Auto-generated method stub
		return null;
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
