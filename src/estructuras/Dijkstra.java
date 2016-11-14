package estructuras;

import dominio.DataCenter;
import dominio.Punto;
import interfaces.ILista;

public class Dijkstra {
	
	private static Grafo grafo;
	//array para guardar distancias visitadas
    private static int[] distancia;
  //array para guardar puntos visitados
    private static int[] previo;
  //array para guardar estado
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

    //El metodo difiere del de clase en que recibe el grafo(q aca lo uso para setear el grafo)y saber donde buscar, lo uso despues para
    //utilizar metodos del grafo en el recursivo, en obtener ady, y en obtener vertice menor distancia. No reciben Hash 
    //yo si, y busco los dc en el hash, y reciben destino que yo no, esa parte no la entendí 
	public void dijkstra(Grafo mapa, Hash ubicaciones, int origen, int esfuerzoCPUrequeridoEnHoras) {
		//cargo el grafo
	    setGrafo(mapa);
	   
        setDistancia(new int[ubicaciones.getSizeTable()]); // la distancia mas corta partiendo del origen
        setPrevio(new int[ubicaciones.getSizeTable()]); // el nodo previo en el camino.
        setVisitado(new boolean[ubicaciones.getSizeTable()]); //nodos visitados.
        setORIGEN(origen);//el inicio
        Dijkstra.setHash(ubicaciones);
        setDataCenter(null);

        //aca se inicializa
        //se pone todas las distancias en max value
        //se setea visitados a false.
        for (int i = 0; i < getDistancia().length; i++) {
            getDistancia()[i] = Integer.MAX_VALUE;
            getVisitado()[i] = false;
        }

        getDistancia()[origen] = 0; //distancia 0 entre orig y orig.
        getVisitado()[origen] = true; //el origen queda como vistado
        
        //llamo a recursivo que devuelve un dc. Viendo lo que hicieron en clase esto es diferente, 
        //en clase devuelven un int que es la distancia a la posicion destino
        //DataCenter dc = dijkstra(origen, esfuerzoCPUrequeridoEnHoras);
        //return dc;
	
	}
	
	public void dijkstra(int p, int esfuerzoCPUrequeridoEnHoras) {
	    boolean notFound = true;
	    //DataCenter dc = null;
	    //Busco vertices adyacentes del origen
	    ILista l = obtenerAdyacentes(p);
	
	    //aca saco el vert que esta a  menor distancia
	    int vertMenorDist = obtenerVerticeMenorDistancia(l, p);
	
	    if (vertMenorDist != -1) {
	        p = getPrevio()[vertMenorDist];
	        int peso = getGrafo().devolverDistancia(vertMenorDist, p);
	
	        //Modifico listas con el punto que selecciono
	        getVisitado()[vertMenorDist] = true;
	        getPrevio()[vertMenorDist] = p;
	        getDistancia()[vertMenorDist] = getDistancia()[p] + peso;
	
	        setPunto(getHash().puntoPorPosicion(vertMenorDist));
	        setDataCenter(devolverDataCenter(getPunto()));
	
	        if (getDataCenter() != null) {	                
	                notFound = false;
	                dataCenter = getDataCenter();
	                
	        }
		        
	        if (notFound || (dataCenter.getCapacidadCPUenHoras()<esfuerzoCPUrequeridoEnHoras)) {
	            dijkstra(vertMenorDist, esfuerzoCPUrequeridoEnHoras);
	        }
	    }
        //return dc;
	 }
	
	
	public int generarInformeDistanciaTotal()
	{
		int distanciaTotal= 0;
		for(int i=0; i<distancia.length; i++)
		{
			distanciaTotal+=distancia[i];
		}
		
		return distanciaTotal;
	}
	
//no se si esto asi va a funcionar...
	 private DataCenter devolverDataCenter(Punto punto2) {
		 return (DataCenter)punto2; // cambié esto
	 }


	private int obtenerVerticeMenorDistancia(ILista l, int p) {
		int verticeMenor = -1;
		int vertice = 0;
     	int pesoaux = 0;
     	int pesoMenor = Integer.MAX_VALUE;
     	NodoLista aux = l.getInicio();
	
     	while (aux != null) {
     		vertice = (Integer) aux.getDato();
     		if (!getVisitado()[vertice]) {
     			pesoaux = getGrafo().devolverDistancia(vertice, p);
     			if (getDistancia()[p] + pesoaux < getDistancia()[vertice]) {
     				getPrevio()[vertice] = p;
     				getDistancia()[vertice] = getDistancia()[p] + pesoaux;
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
