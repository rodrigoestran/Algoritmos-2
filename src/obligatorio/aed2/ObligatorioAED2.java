/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.aed2;

import dominio.Ciudad;
import dominio.DataCenter;
import dominio.Empresa;
import dominio.Punto;
import sistema.Sistema;

/**
 *
 * @author alumnoFI
 */
public class ObligatorioAED2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sistema s = new Sistema();
        s.inicializarSistema(15);
        Punto[] testPuntos = new Punto[]{ new DataCenter("CarmenDataCenters", new Empresa("Empower", "", "", "blue", "Unsure"), 100, 100, -24.90, -55.16),
        		new DataCenter("DCStores", new Empresa("Empower", "", "", "blue", "Unsure"), 100, 100, -34.90, -53.16),
        		new DataCenter("DreamCastleDC", new Empresa("Crrrawl", "", "", "green", "Unsure"), 100, 100, -20.90, -50.16),
        		new DataCenter("DallasCoasters", new Empresa("ClassyCat", "", "", "red", "Unsure"), 100, 100, -25.90, -55.16),
        		new DataCenter("DogoCat", new Empresa("ZazzyPants", "", "", "purple", "Unsure"), 100, 100, 47.0, -122.0),
        		new Ciudad(47.606210, -122.332071, "Seattle"),
        		new Ciudad(37.774930, -122.419416, "San_Francisco"),
        		new Ciudad(16.853109, -99.823653, "Acapulco"),
        		new Ciudad(-34.603684, -58.381559, "Buenos_Aires"),
        		new Ciudad(-0.180653, -78.467838, "Quito")
        		};		
        s.registrarCiudad("Chicago", 41.878114, -87.629798);
        
        
        s.mapaEstado();
    }
    
}
