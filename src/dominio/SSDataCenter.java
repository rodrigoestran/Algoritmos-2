/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;


import java.util.ArrayList;
import estructuras.DjikstraDCMasProximo;
import estructuras.Grafo;
import estructuras.ListaSEIni;
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


    public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int peso) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        if (peso > 0) {
            ret.setResultado(Resultado.ERROR_2);
            Punto ini = mapa.obtenerPunto(coordXi, coordYi);
            Punto fin = mapa.obtenerPunto(coordXf, coordYf);
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
        int pos = mapa.getVertices().posicionPorCoord(coordX, coordY);
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
        Punto ini = mapa.obtenerPunto(coordXi, coordYi);
        Punto fin = mapa.obtenerPunto(coordXf, coordYf);
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
 
    public Retorno procesarInformación(Double coordX, Double coordY, int esfuerzoCPUrequeridoEnHoras) {
    	Retorno ret = new Retorno(Resultado.ERROR_1);
        DataCenter dcOrigen = null;
        String string = "";
		    int p = this.mapa.getVertices().posicionPorCoord(coordX, coordY);
		    int distanciaTotal = 0;
		    if (p != -1) {
		        Punto punto = this.mapa.getVertices().puntoPorPosicion(p);
		        dcOrigen = (DataCenter) punto;
		        if(dcOrigen!= null)
		        {
		        	if (dcOrigen.getCapacidadCPUenHoras()<esfuerzoCPUrequeridoEnHoras) 
		        	{
		        		DjikstraDCMasProximo dDcMP = new DjikstraDCMasProximo();                
		        		dDcMP.dijkstra(mapa, mapa.getVertices(), p, esfuerzoCPUrequeridoEnHoras);
		        		DataCenter dcDestino = dDcMP.getDataCenter();
		        		distanciaTotal = dDcMP.generarInformeDistanciaTotal();
		        		if(dcDestino != null)
		        		{
		        			if(!dcDestino.getEmpresa().equals(dcOrigen.getEmpresa()))
		        			{
		        				
		        				int costo = distanciaTotal + (esfuerzoCPUrequeridoEnHoras * dcDestino.getCostoCPUporHora());
		        				string = dcDestino.getNombre()+ "|" + costo;
		        				dcDestino.setCapacidadCPUenHoras(dcDestino.getCapacidadCPUenHoras()-esfuerzoCPUrequeridoEnHoras);
		        				dcDestino.setEsfuerzoEnUso(dcDestino.getEsfuerzoEnUso()+esfuerzoCPUrequeridoEnHoras);
		        				ret.setResultado(Resultado.OK);
		        				ret.valorString = string;
		        			}
		        			else
		        			{
		        				           				
		        				string = dcDestino.getNombre()+ "|" + distanciaTotal;
		        				dcDestino.setCapacidadCPUenHoras(dcDestino.getCapacidadCPUenHoras()-esfuerzoCPUrequeridoEnHoras);
		        				dcDestino.setEsfuerzoEnUso(dcDestino.getEsfuerzoEnUso()+esfuerzoCPUrequeridoEnHoras);
		        				ret.setResultado(Resultado.OK);
		        				ret.valorString = string;
		        			}
		        		}
		        		else
		        		{
		        			ret.setResultado(Resultado.ERROR_2);
		        			
		        		}		        			
		        	
		        	}
		        	else
		        	{
		        		string = dcOrigen.getNombre() + "|" + "0";
		        		dcOrigen.setCapacidadCPUenHoras(dcOrigen.getCapacidadCPUenHoras()-esfuerzoCPUrequeridoEnHoras);
		        		dcOrigen.setEsfuerzoEnUso(dcOrigen.getEsfuerzoEnUso()+esfuerzoCPUrequeridoEnHoras);
		        		ret.setResultado(Resultado.OK);
		        		ret.valorString = string;
		        	}
		        }
		        
		    }
		System.out.println(ret.valorString);
        return ret;
    }
    
    
    

//    public void crearMapa() {
//    	String mapaURL = "http://maps.googleapis.com/maps/api/staticmap?size=1200x600&maptype=roadmap&sensor=false";
//		mapaURL += buildMapString();
//		try {
//			Desktop.getDesktop().browse(new URI(mapaURL));
//		} catch (IOException | URISyntaxException e) {
//			e.printStackTrace();
//		}
//    }
    
//    public String buildMapString(){
//    	String mapString = "";
//    	for (int i=0; i<testPuntos.length; i++){
//    		if (testPuntos[i] instanceof Ciudad)	
//    			mapString += "&markers=color:yellow";
//    		else     			
//    			mapString += "&markers=color:" + ((DataCenter)testPuntos[i]).getEmpresa().getColor();
//    		
//    		mapString += "%7Clabel:" + testPuntos[i].getNombre() + 
//    					 "%7C" + testPuntos[i].getCoordX() + "," + testPuntos[i].getCoordY() ;
//    		
//    	}
//    	return mapString;
//    }
    
   
    
    public String listarRedMinima(){
    	ILista red = obtenerRedMinima();
    	return red.informe();
    }
    
    public ILista obtenerRedMinima(){
    	return mapa.actualizarRedMinima();
    }

  
    
    //?
    public void DepthFirstSearch(int v, boolean[] visitados, int radio) {
    	
    }
    
}
