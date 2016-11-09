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
        s.registrarEmpresa("ZazzyPants", "", "", "empresa@gmail.com", "purple");
        s.registrarEmpresa("ClassyCat", "", "", "email@email.com", "red");
        s.registrarCiudad("Chicago", 41.878114, -87.629798);
        s.registrarCiudad("Seattle", 47.606210, -122.332071);
		s.registrarCiudad( "San_Francisco", 37.774930, -122.419416);
		s.registrarCiudad("Acapulco", 16.853109, -99.823653);
		s.registrarCiudad("Buenos_Aires", -34.603684, -58.381559);
		s.registrarCiudad("Quito", -0.180653, -78.467838);
		s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 100, 100);
		s.registrarDC("B", -25.90, -55.16, "ClassyCat", 100, 100);
		s.registrarDC("C", -20.90, -50.16, "ZazzyPants", 100, 100);
		s.registrarDC("D", -25.93, -55.11, "ClassyCat", 100, 100);

		//s.registrarTramo(47.0, -122.0, -25.90, -55.16, 25);
		//s.registrarTramo(47.0, -122.0, -25.90, -55.16, 25);

		
        //s.mapaEstado();
        s.listadoEmpresas();
    }
    
}
