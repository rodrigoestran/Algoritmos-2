/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import estructuras.ABB;
import estructuras.Arco;
import estructuras.DjikstraDCMasProximo;
import estructuras.Grafo;
import estructuras.Hash;
import estructuras.ListaSEIni;
import estructuras.Nodo;
import interfaces.ILista;
import sistema.Retorno;
import sistema.Retorno.Resultado;

/**
 *
 * @author alumnoFI
 */
public class SSDataCenter {
	
    private Grafo mapa;
    private ILista dataCenterEnRadio;
	public String informe = ""; // ?

	// datos de prueba //
	Punto[] testPuntos = new Punto[]{ new DataCenter("CarmenDataCenters", new Empresa("Empower", "", "", "blue", "Unsure"), 100, 100, -24.90, -55.16),
		new DataCenter("DCStores", new Empresa("Empower", "", "", "blue", "Unsure"), 100, 100, -34.90, -53.16),
		new DataCenter("DreamCastleDC", new Empresa("Crrrawl", "", "", "green", "Unsure"), 100, 100, -20.90, -50.16),
		new DataCenter("DallasCoasters", new Empresa("ClassyCat", "", "", "red", "Unsure"), 100, 100, -25.90, -55.16),
		new DataCenter("DogoCat", new Empresa("ZazzyPants", "", "", "purple", "Unsure"), 100, 100, 47.0, -122.0),
		new Ciudad(41.878114, -87.629798, "Chicago"),
		new Ciudad(47.606210, -122.332071, "Seattle"),
		new Ciudad(37.774930, -122.419416, "San_Francisco"),
		new Ciudad(16.853109, -99.823653, "Acapulco"),
		new Ciudad(-34.603684, -58.381559, "Buenos_Aires"),
		new Ciudad(-0.180653, -78.467838, "Quito")
		};		
	// Eliminar luego
	
	
	// constructor //
    public SSDataCenter(int size) {
        this.mapa = new Grafo(size);
        this.dataCenterEnRadio = new ListaSEIni();
    }
    
    // "destructor" //
    public void destruir() {
        this.mapa = null;
        this.dataCenterEnRadio = null;
        this.informe = null;
    }
	
    // getters y setters //
    
    public Grafo getMapa() {
        return mapa;
    }

    public void setMapa(Grafo m) {
        this.mapa = m;
    }

    public ILista getDataCenterEnRadio() {
        return dataCenterEnRadio;
    }

    public void setDataCenterEnRadio(ILista dcEnRadio) {
        this.dataCenterEnRadio = dcEnRadio;
    }

    // comportamiento
//    public Retorno registrarDataCenter(String nombre, Empresa empresa, int capCPRHoras, int costCPUHora, Double coordX, 
//    		Double coordY) {
//    	
//        Retorno ret = new Retorno(Resultado.ERROR_1);
//        // busca directamente en el hash sel grafo, por las coord del DC, que no se puede repetir
//        if (!this.mapa.estaVertice(coordX, coordY)) {
//            DataCenter dc = new DataCenter(nombre, empresa, capCPRHoras, costCPUHora, coordX, coordY);
//            // se fija si hay lugares disponibles (ya que hay un tope)
//            int posicionLibre = this.mapa.tieneLugarDisponible();
//            if (posicionLibre != -1){
//            	this.mapa.agregarVertice(posicionLibre, dc);
//	            ret.setResultado(Resultado.OK);
//            }
//        }
//        return ret;
//    }   

    public Punto buscar(Double x, Double y) {
        return this.mapa.buscarPunto(x, y);
    }
    

    public Retorno buscarDataCenter(Double x, Double y) {
        Retorno ret = new Retorno(Resultado.ERROR_1); // este error1 a que requerimiento esta relacionado?
        Punto dc = this.buscar(x, y);
        if(dc != null && dc instanceof DataCenter){
            ret.setResultado(Resultado.OK);
            ret.valorString = dc.toString();
            System.out.println(dc.toString());
        }
        return ret;
    }

    // Para que es esto?
    public Retorno mostrarInOrden() {
        Retorno ret = new Retorno(Resultado.OK);	        
        //this.informe = this.mapa.getInforme();
        ret.valorString = this.informe;
        return ret;
    }
    
    public void crearInforme(String s){
        this.informe += s;
    }

    // el punto especifico se lo da el DC o Ciudad que correspondan desde Sistema
    public boolean agregarPunto(Punto p) {
        if (!mapa.getVertices().perteneceAHash(p.getCoordX(), p.getCoordY())) {
            this.mapa.agregarVertice(p);
            return true;
        }
        return false;
    }

    public boolean existe(Double coordX, Double coordY) {
        return mapa.existe(coordX, coordY);
    }

    // no andaria como esta
    public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int peso) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        if (peso > 0) {
            ret.setResultado(Resultado.ERROR_2);
            Punto ini = mapa.obtenerPunto(coordXi, coordXf);
            Punto fin = mapa.obtenerPunto(coordYi, coordYf);
            if (ini != null && fin != null) {
                ret.setResultado(Resultado.ERROR_3);
                boolean existe = this.mapa.existeArista(ini, fin);
                if (!existe) {
                    ret.setResultado(Resultado.OK);
                    this.mapa.agregarArista(peso, ini, fin);
                }
            }
        }
        return ret;
    }

    public Retorno eliminarPunto(Double coordX, Double coordY) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        int pos = mapa.getVertices().posicionActual(coordX, coordY);
        if (pos != -1) {
            Punto p = this.mapa.getVertices().puntoPorPosicion(pos);
            this.mapa.eliminarVertice(pos);
            this.mapa.getVertices().eliminarPunto(pos);
            ret.setResultado(Resultado.OK);
        }
        return ret;
    }

    public Retorno eliminarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        Punto ini = mapa.obtenerPunto(coordXi, coordXf);
        Punto fin = mapa.obtenerPunto(coordYi, coordYf);
        if (ini != null && fin != null) {
            ret.setResultado(Resultado.ERROR_2);
            boolean existe = this.mapa.existeArista(ini, fin);
            if (existe) {
                this.mapa.eliminarArista(ini, fin);
                ret.setResultado(Resultado.OK);
            }
        }
        return ret;
    }
 
    public DataCenter dcMasProximo(Double coordX, Double coordY) {
        DataCenter dc = null;
        int u = this.mapa.getVertices().posicionActual(coordX, coordY);
        if (u != -1) {
            Punto punto = this.mapa.getVertices().puntoPorPosicion(u);
            dc = (DataCenter) punto;
            if (dc == null) {
                DjikstraDCMasProximo dDcMP = new DjikstraDCMasProximo();                
                 dc = dDcMP.dijkstra(mapa, mapa.getVertices(), u);                
            } 
        }        
        return dc;
    }

    public void crearMapa() {
    	String mapaURL = "http://maps.googleapis.com/maps/api/staticmap?size=1200x600&maptype=roadmap&sensor=false";
		mapaURL += buildMapString();
		try {
			Desktop.getDesktop().browse(new URI(mapaURL));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
    public String buildMapString(){
    	String mapString = "";
    	for (int i=0; i<testPuntos.length; i++){
    		if (testPuntos[i] instanceof Ciudad)	
    			mapString += "&markers=color:yellow";
    		else     			
    			mapString += "&markers=color:" + ((DataCenter)testPuntos[i]).getEmpresa().getColor();
    		
    		mapString += "%7Clabel:" + testPuntos[i].getNombre() + 
    					 "%7C" + testPuntos[i].getCoordX() + "," + testPuntos[i].getCoordY() ;
    		
    	}
    	return mapString;
    }
    
    public ArrayList<DataCenter> DataCentersEnRadio(double x, double y, int distancia){
		return null;
	}
    
    public String listarRedMinima(){
    	ILista red = obtenerRedMinima();
    	return red.informe();
    }
    
    public ILista obtenerRedMinima(){
    	return mapa.actualizarRedMinima();
    }

    //?
    private int distanciaMasCorta(int[] costo, boolean[] visitado) {
		return 0;
	}
    
    //?
    public void DepthFirstSearch(int v, boolean[] visitados, int radio) {
    	
    }
    
}
