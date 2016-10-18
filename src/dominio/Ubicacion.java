/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import estructuras.Punto;

/**
 *
 * @author alumnoFI
 */
public class Ubicacion extends Punto{
	
	private DataCenter dc;
	private Ciudad c;

    public DataCenter getDataCenter() {
        return dc;
    }

    public void setDataCenter(DataCenter dataCenter) {
        this.dc = dataCenter;
    }
    
	public Ciudad getCiudad() {
        return dc;
    }

    public void setCiudad(Ciudad ciudad) {
        this.c = ciudad;
    }
    
    public Ubicacion(Double coordX, Double coordY) {
        super(coordX, coordY);
        this.dc = null;
		this.c = null;
    }
    
}
