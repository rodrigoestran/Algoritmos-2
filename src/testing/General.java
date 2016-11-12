package testing;

import static org.junit.Assert.*;
import org.junit.Test;
import sistema.*;
import sistema.Retorno.Resultado;

public class General {
	Sistema s = new Sistema();
	
	@Test
	public void inicializarOK(){
		Resultado r = s.inicializarSistema(15).resultado;
		assertEquals(Resultado.OK, r);
	}
	
	@Test
	public void registrarEmpresaOK(){
		inicializarOK();
		assertEquals(Resultado.OK, s.registrarEmpresa("ZazzyPants", "", "", "empresa@gmail.com", "purple").resultado);
		assertEquals(Resultado.OK,s.registrarEmpresa("ClassyCat", "", "", "email@email.com", "red").resultado);
	}
	
	@Test
	public void registrarCiudadOK(){
		registrarEmpresaOK();
		assertEquals(Resultado.OK,s.registrarCiudad("Chicago", 41.878114, -87.629798).resultado);
		assertEquals(Resultado.OK,s.registrarCiudad("Seattle", 47.606210, -122.332071).resultado);
		assertEquals(Resultado.OK,s.registrarCiudad( "San_Francisco", 37.774930, -122.419416).resultado);
		assertEquals(Resultado.OK,s.registrarCiudad("Acapulco", 16.853109, -99.823653).resultado);
		assertEquals(Resultado.OK,s.registrarCiudad("Buenos_Aires", -34.603684, -58.381559).resultado);
		assertEquals(Resultado.OK,s.registrarCiudad("Quito", -0.180653, -78.467838).resultado);
		
	}
    
	@Test
    public void registrarDCOK(){
    	registrarEmpresaOK();
    	assertEquals(Resultado.OK,s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("B", -25.90, -55.16, "ClassyCat", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("C", -20.90, -50.16, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("D", -25.93, -55.11, "ClassyCat", 100, 100).resultado);
    }
	
	@Test
    public void registrarTramoOK(){
    	registrarDCOK();
    	
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, -25.90, -55.16, 25).resultado); // A -- B
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, -25.93, -55.11, 25).resultado); // A -- D
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0,  41.878114, -87.629798, 20).resultado); // A -- Chicago
    	assertEquals(Resultado.OK,s.registrarTramo(-20.90, -50.16,  41.878114, -87.629798, 9).resultado); // C -- Chicago
    	assertEquals(Resultado.OK,s.registrarTramo(-20.90, -50.16,  -25.90, -55.16, 11).resultado); // C -- B
    	assertEquals(Resultado.OK,s.registrarTramo(-25.93, -55.11,  47.606210, -122.332071, 10).resultado); // D -- Seattle
    	assertEquals(Resultado.OK,s.registrarTramo(-20.90, -50.16,  47.606210, -122.332071, 9).resultado); // Seattle -- C
    	assertEquals(Resultado.OK,s.registrarTramo(41.878114, -87.629798,  47.606210, -122.332071, 5).resultado); // Seattle -- Chicago
    	
    }

	


	
    //s.mapaEstado();
    //s.listadoEmpresas();
}
