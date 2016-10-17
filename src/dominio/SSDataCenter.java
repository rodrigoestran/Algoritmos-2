/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import estructuras.ABB;
import estructuras.Nodo;
import sistema.Retorno;
import sistema.Retorno.Resultado;

/**
 *
 * @author alumnoFI
 */
public class SSDataCenter {
	
	  private ABB arbolDataCenter;
	    public String informe = "";

	    public ABB getArbolDataCenter() {
	        return arbolDataCenter;
	    }

	    public void setArbolDataCenter(ABB arbolDataCenter) {
	        this.arbolDataCenter = arbolDataCenter;
	    }

	    public SSDataCenter() {
	        this.arbolDataCenter = new ABB();
	    }

	    public Retorno registrarDataCenter(String nombre, String empresa, int capCPRHoras, int costCPUHora) {
	        Retorno ret = new Retorno(Resultado.ERROR_1);
	        if (!this.arbolDataCenter.existeElemento(nombre)) {
	            DataCenter dc = new DataCenter(nombre, empresa, capCPRHoras, costCPUHora);
	            this.arbolDataCenter.insertar(dc);
	            ret.setResultado(Resultado.OK);
	        }
	        return ret;
	    }   
	

	    public void destruir() {
	        this.arbolDataCenter = new ABB();
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
    
}
