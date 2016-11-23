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
import estructuras.Dijkstra;
import estructuras.Grafo;
import interfaces.IGrafo;
import interfaces.ILista;
import sistema.Retorno;
import sistema.Retorno.Resultado;

/**
 *
 * @author alumnoFI
 */

public class SSPuntos {
	
    private IGrafo mapa;

	// constructor //
    public SSPuntos(int size) {
        this.mapa = new Grafo(size);
    }
    
    // "destructor" //
    public void destruir() {
        this.mapa = null;
    }
	
    // getters y setters //
    public IGrafo getMapa() {
        return mapa;
    }

    public void setMapa(Grafo m) {
        this.mapa = m;
    }

    // el punto especifico se lo da el DC o Ciudad que correspondan desde Sistema
    public boolean agregarPunto(Punto p) {
        if (mapa.getVertices().posicionPorCoord(p.getCoordX(), p.getCoordY())==-1) {
            this.mapa.agregarVertice(p);
            return true;
        }
        return false;
    }

    public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int peso) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        if (peso > 0) {
            ret.setResultado(Resultado.ERROR_2);
            Punto ini = ((Grafo)mapa).obtenerPunto(coordXi, coordYi);
            Punto fin = ((Grafo)mapa).obtenerPunto(coordXf, coordYf);
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
        int pos = ((Grafo)mapa).getVertices().posicionPorCoord(coordX, coordY);
        if (pos != -1) {
            ((Grafo)mapa).eliminarVertice(pos);
            ((Grafo)mapa).getVertices().eliminarPunto(pos);
            ret.setResultado(Resultado.OK);
        }
        return ret;
    }

    public Retorno eliminarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        Punto ini = ((Grafo)mapa).obtenerPunto(coordXi, coordYi);
        Punto fin = ((Grafo)mapa).obtenerPunto(coordXf, coordYf);
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
    
    // PRE: Las coordenadas de origen existen. Las coordenadas de origen corresponden a un DC y no una Ciudad
    public Retorno procesarInformación(Double coordX, Double coordY, int esfuerzoCPUrequeridoEnHoras) {
    	Retorno ret = new Retorno(Resultado.ERROR_1);
        DataCenter dcOrigen = null;
	    int ori = ((Grafo)mapa).getVertices().posicionPorCoord(coordX, coordY);
	    int costoTotal = 0;
	    if (ori != -1) {
	        Punto punto = ((Grafo)mapa).getVertices().puntoPorPosicion(ori);
	        dcOrigen = (DataCenter) punto; //PRE
        	if (dcOrigen.getCapacidadCPUenHoras() < esfuerzoCPUrequeridoEnHoras) // Si el origen lo puede procesar el solo
        	{
        		Dijkstra dDcMP = new Dijkstra(mapa, mapa.getVertices());                
        		dDcMP.dijkstra(ori, esfuerzoCPUrequeridoEnHoras);
        		// Pide la empresa porque lo tiene que comparar para calcular el costo final;
        		DataCenter dcDestino = dDcMP.obtenerDCcapableConMenorDistancia(dcOrigen.getEmpresa(), esfuerzoCPUrequeridoEnHoras);
        		costoTotal = dDcMP.getCostoActual();
        		if(dcDestino != null){
        			ret.valorString = modifDCyArmarRetorno(dcDestino, costoTotal, esfuerzoCPUrequeridoEnHoras);
    				ret.setResultado(Resultado.OK);
        		}
        		else 
        			ret.setResultado(Resultado.ERROR_2);
        	}
        	else {
        		ret.valorString = modifDCyArmarRetorno(dcOrigen, 0, esfuerzoCPUrequeridoEnHoras);
        		ret.setResultado(Resultado.OK);
        	}	        
	    }
	    else ret.valorString = "No existen las coordenadas";
        return ret;
    }
    
    public String modifDCyArmarRetorno(DataCenter dc, int costo, int esfuerzo){
    	dc.setCapacidadCPUenHoras(dc.getCapacidadCPUenHoras() - esfuerzo);
		dc.setEsfuerzoEnUso(dc.getEsfuerzoEnUso() + esfuerzo);
    	return dc.getNombre()+ ";" + costo;
    }
    
    
    public void crearMapa() {
    	String mapaURL = "http://maps.googleapis.com/maps/api/staticmap?size=1200x600&maptype=roadmap&sensor=false";
		mapaURL += ((Grafo)mapa).buildMapString();
		try {
			Desktop.getDesktop().browse(new URI(mapaURL));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
    public String[] listarRedMinima(){
    	ILista red = obtenerRedMinima();
    	return red.informe();
    }
    
    public ILista obtenerRedMinima(){
    	return mapa.actualizarRedMinima();
    }    
}
