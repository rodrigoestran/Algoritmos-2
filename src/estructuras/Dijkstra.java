package estructuras;

import dominio.DataCenter;
import dominio.Punto;
import interfaces.ILista;

public class Dijkstra {
	
	private  Grafo grafo;
	//array para guardar distancias visitadas
    private  int[] distancia;
  //array para guardar puntos visitados
    private  int[] previo;
  //array para guardar estado
    private  boolean[] visitado;
    
    private  Hash hash;
    private  DataCenter dataCenter;
    private  Punto punto;
    private  int DESTINO;
    private  int ORIGEN;
   
    public  int[] getDistancia() {
        return distancia;
    }
   
    public  void setDistancia(int[] d) {
        distancia = d;
    }
   
    public  int[] getPrevio() {
        return previo;
    }
   
    public  void setPrevio(int[] p) {
        previo = p;
    }
   
    public  boolean[] getVisitado() {
        return visitado;
    }
    
    public  void setVisitado(boolean[] aVisitado) {
        visitado = aVisitado;
    }
    
    public  int getDESTINO() {
        return DESTINO;
    }
   
    public  void setDESTINO(int d) {
        DESTINO = d;
    }
    
    public  int getORIGEN() {
        return ORIGEN;
    }

    public  void setORIGEN(int o) {
        ORIGEN = o;
    }
  
    public  Grafo getGrafo() {
        return grafo;
    }
   
    public  void setGrafo(Grafo g) {
        grafo = g;
    }

    public  Hash getHash() {
        return hash;
    }
   
    public  void setHash(Hash h) {
        hash = h;
    }
    
    public  DataCenter getDataCenter() {
        return dataCenter;
    }
    
    public  void setDataCenter(DataCenter dc) {
        dataCenter = dc;
    }
    
    public  Punto getPunto() {
        return punto;
    }
   
    public  void setPunto(Punto aPunto) {
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
        this.setHash(ubicaciones);
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
        dijkstra(origen, esfuerzoCPUrequeridoEnHoras);
        
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
     	//int pesoMenor = Integer.MAX_VALUE;
     	NodoLista aux = l.getInicio();
	
     	while (aux != null) {
     		vertice = (Integer) aux.getDato();
     		if (!visitado[vertice] && grafo.sonAdyacentes(vertice, p)) {
     			pesoaux = getGrafo().devolverDistancia(vertice, p);
     			if (getDistancia()[p] + pesoaux < getDistancia()[vertice]) {
     				previo[vertice] = p;
     				distancia[vertice] = getDistancia()[p] + pesoaux;
     				visitado[vertice] = true;
     			}
     			else
     			{
     				distancia[vertice] = Integer.MAX_VALUE; 
     			}
     		} 
     		aux = aux.getSig();
	     }
	     /*for(int i = 0; i< distancia.length;i++){
	         if(distancia[i]<pesoMenor && !getVisitado()[i]){
	             verticeMenor = i;
	             pesoMenor = getDistancia()[i];
	         }
	     }*/    	
     	
	     return verticeMenor = distanciaMasCorta();
	}
	
	
	public int distanciaMasCorta() {
		int verticeMenor = -1;
		int pesoMenor = Integer.MAX_VALUE;
		for(int i = 1; i< visitado.length; i++){
			if(!visitado[i]){
				if(distancia[i] < pesoMenor){
					pesoMenor = distancia[i];
					verticeMenor = i;
				}
			}
		}
		return verticeMenor;
	}
	
	
	

	private ILista obtenerAdyacentes(int u) {
		 return getGrafo().obtenerVerticesAdyacentes(u);
	}
	
	
	

}
