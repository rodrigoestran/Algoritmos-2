/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;

import estructuras.ABB;
import estructuras.DjikstraDCMasProximo;
import estructuras.Grafo;
import estructuras.Hash;
import estructuras.Lista;
import estructuras.Nodo;
import estructuras.Punto;
import sistema.Retorno;
import sistema.Retorno.Resultado;

/**
 *
 * @author alumnoFI
 */
public class SSDataCenter {
	
	private Hash puntos;
    private Grafo Mapa;
    private Lista dataCenterEnRadio;
	private ABB arbolDataCenter;
	public String informe = "";

	// constructor //
    public SSDataCenter(int size) {
        this.arbolDataCenter = new ABB();
        this.puntos = new Hash(size);
        this.Mapa = new Grafo(size);
        this.dataCenterEnRadio = new Lista();
    }
    
    // "destructor" //
    public void destruir() {
        this.arbolDataCenter = null;
        this.Mapa = null;
        this.puntos = null;
        this.dataCenterEnRadio = null;
        this.informe = null;
    }
	
    // getters y setters //
	public ABB getArbolDataCenter() {
        return arbolDataCenter;
    }

    public void setArbolDataCenter(ABB arbolDataCenter) {
        this.arbolDataCenter = arbolDataCenter;
    }

    public Hash getUbicaciones() {
        return puntos;
    }

    public void setUbicaciones(Hash u) {
        this.puntos = u;
    }

    public Grafo getMapa() {
        return Mapa;
    }

    public void setMapa(Grafo m) {
        this.Mapa = m;
    }

    public Lista getDataCenterEnRadio() {
        return dataCenterEnRadio;
    }

    public void setDataCenterEnRadio(Lista dcEnRadio) {
        this.dataCenterEnRadio = dcEnRadio;
    }

    // comportamiento
    public Retorno registrarDataCenter(String nombre, Empresa empresa, int capCPRHoras, int costCPUHora, 
    		Double coordX, Double coordY) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        if (!this.arbolDataCenter.existeElemento(nombre)) {
            DataCenter dc = new DataCenter(nombre, empresa, capCPRHoras, costCPUHora, coordX, coordY);
            this.arbolDataCenter.insertar(dc);
            ret.setResultado(Resultado.OK);
        }
        return ret;
    }   

    public DataCenter buscar(String nombre) {
        DataCenter dc = null;
        Nodo n = this.arbolDataCenter.Buscar(nombre);
        if(n!=null){
        	dc = n.getDato();
        }
        return dc;
    }

    public Retorno buscarDataCenter(String nombre) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        DataCenter dc = this.buscar(nombre);
        if(dc != null){
            ret.setResultado(Resultado.OK);
            ret.valorString = dc.toString();
            System.out.println(dc.toString());
        }
        return ret;
    }

    public Retorno mostrarInOrden() {
        Retorno ret = new Retorno(Resultado.OK);	        
        this.informe = this.arbolDataCenter.getInforme();
        ret.valorString = this.informe;
        return ret;
    }
    
    public void crearInforme(String s){
        this.informe += s;
    }
	
//    public boolean agregarUbicacion(double coordX, double coordY) {
//        boolean ret = false;
//        Punto p = new Ubicacion(coordX, coordY);
//        if (this.Mapa.hayLugar()) {
//            if (!this.ubicaciones.pertenece(coordX, coordY)) {
//                int pos = this.ubicaciones.insertar(p);
//                this.Mapa.agregarVertice(pos);
//                ret = true;
//            }
//        }
//        return ret;
//    }

    public boolean existe(Double coordX, Double coordY) {
        return this.puntos.pertenece(coordX, coordY);
    }

    public Punto buscar(Double coordX, Double coordY) {
        Punto p = null;
        int pos = this.puntos.devolverPosActual(coordX, coordY);
        if (pos != -1) {
            p = (Punto) this.puntos.devolverPuntoPorPosicion(pos);
        }
        return p;
    }

    // Esto capaz puede servir para la Ciudad
//    public boolean asignarDataCenter(DataCenter dc, double coordX, double coordY) {
//        boolean ret = false;
//        if (this.existe(coordX, coordY)) {
//            Ubicacion u = this.buscar(coordX, coordY);
//            if (u != null) {                
//                if (u.getDataCenter() == null) {
//                    u.setDataCenter(dc);
//                    dc.setUbicacion(u);
//                    ret = true;
//                }
//
//            }
//        }
//        return ret;
//    }

    public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int peso) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        if (peso > 0) {
            ret.setResultado(Resultado.ERROR_2);
            int posicionIni = this.puntos.devolverPosActual(coordXi, coordYi);
            int posicionFin = this.puntos.devolverPosActual(coordXf, coordYf);
            if (posicionIni != -1 && posicionFin != -1) {
                ret.setResultado(Resultado.ERROR_3);
                boolean existe = this.Mapa.existeArista(posicionIni, posicionFin, coordXi, coordYi, coordXf, coordYf);
                if (!existe) {
                    ret.setResultado(Resultado.OK);
                    this.Mapa.agregarArista(posicionIni, posicionFin, peso, coordXi, coordYi, coordXf, coordYf);
                }
            }
        }
        return ret;
    }

    public Retorno eliminarUbicacion(Double coordX, Double coordY) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        int pos = this.puntos.devolverPosActual(coordX, coordY);
        if (pos != -1) {
            ret.setResultado(Resultado.ERROR_2);
            Ubicacion u = (Ubicacion) this.puntos.devolverPuntoPorPosicion(pos);
            if (u.getDataCenter() == null) {
                this.Mapa.eliminarVertice(pos);
                this.puntos.eliminarPunto(pos);
                ret.setResultado(Resultado.OK);
            }
        }
        return ret;
    }

    public Retorno eliminarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        int posicionIni = this.puntos.devolverPosActual(coordXi, coordYi);
        int posicionFin = this.puntos.devolverPosActual(coordXf, coordYf);
        if (posicionIni != -1 && posicionFin != -1) {
            ret.setResultado(Resultado.ERROR_2);
            boolean existe = this.Mapa.existeArista(posicionIni, posicionFin, coordXi, coordYi, coordXf, coordYf);
            if (existe) {
                this.Mapa.eliminarArista(posicionIni, posicionFin);
                ret.setResultado(Resultado.OK);
            }
        }
        return ret;
    }
 
    public DataCenter dcMasProximo(Double coordX, Double coordY) {
        DataCenter dc = null;
        int u = this.puntos.devolverPosActual(coordX, coordY);
        if (u != -1) {
            Punto punto = this.puntos.devolverPuntoPorPosicion(u);
            Ubicacion ubic = (Ubicacion) punto;
            dc = ubic.getDataCenter();
            if (dc == null) {
                DjikstraDCMasProximo dDcMP = new DjikstraDCMasProximo();                
                 dc = dDcMP.dijkstra(Mapa, puntos, u);                
            } 
        }        
        return dc;
    }

    //falta implementar
   
    
    public Retorno crearMapa() {
		return null;
	}
    
    public ArrayList<DataCenter> DataCentersEnRadio(double x, double y, int distancia){
		return null;
	}

    private int distanciaMasCorta(int[] costo, boolean[] visitado) {
		return 0;
	}
    
    public void DepthFirstSearch(int v, boolean[] visitados, int radio) {
    	
    }
    
}
