package testing;

import static org.junit.Assert.*;
import org.junit.Test;
import sistema.*;
import sistema.Retorno.Resultado;

public class General {
	static Sistema s = new Sistema();
	static int puntos = 15;
	
	//////////// INICIALIZACION //////////// 
	
	@Test
	public void inicializarOK(){
		Resultado r = s.inicializarSistema(puntos).resultado;
		assertEquals(Resultado.OK, r);
	}

	/*public static void main(String[] args) {
		s.inicializarSistema(puntos);
		s.registrarEmpresa("ZazzyPants", "", "", "empresa@gmail.com", "purple");
		s.registrarEmpresa("ClassyCat", "", "", "email@email.com", "red");
		
		s.registrarCiudad("Chicago", 41.878114, -87.629798);
		s.registrarCiudad("Seattle", 47.606210, -122.332071);
		s.registrarCiudad( "San_Francisco", 37.774930, -122.419416);
		
		s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 100, 100);
    	s.registrarDC("B", -25.90, -55.16, "ClassyCat", 100, 100);
    	s.registrarDC("C", -20.90, -50.16, "ZazzyPants", 100, 100);
    	s.registrarDC("D", -25.93, -55.11, "ClassyCat", 100, 100);
    	
    	s.registrarTramo(47.0,-122.0,-25.90,-55.16, 25); // A -- B
    	s.registrarTramo(47.0, -122.0, -25.93, -55.11, 25); // A -- D
    	s.registrarTramo(47.0, -122.0,  41.878114, -87.629798, 20); // A -- Chicago
    	s.registrarTramo(-20.90, -50.16,  41.878114, -87.629798, 9); // C -- Chicago
    	
    	
    	

    }*/
    
	
	
	
	
	@Test
	// Cantidad de puntos <= 0
	public void inicializarError1(){
		Resultado r = s.inicializarSistema(-3).resultado;
		assertEquals(Resultado.ERROR_1, r);
		r = s.inicializarSistema(0).resultado;
		assertEquals(Resultado.ERROR_1, r);
	}
	
	//////////// EMPRESAS //////////// 
	
	@Test
	public void registrarEmpresaOK(){
		inicializarOK();
		assertEquals(Resultado.OK, s.registrarEmpresa("ZazzyPants", "", "", "empresa@gmail.com", "purple").resultado);
		assertEquals(Resultado.OK,s.registrarEmpresa("ClassyCat", "", "", "email@email.com", "red").resultado);
	}
	
	@Test
	// Si no se valida el mail
	public void registrarEmpresaError1(){
		inicializarOK();
		assertEquals(Resultado.ERROR_1, s.registrarEmpresa("ZazzyPants", "", "", "empresa@.gmail.com", "purple").resultado);
		assertEquals(Resultado.ERROR_1, s.registrarEmpresa("ZazzyPants", "", "", "empresa@@gmail.com", "purple").resultado);
		assertEquals(Resultado.ERROR_1, s.registrarEmpresa("ZazzyPants", "", "", "empresa.gmail.com", "purple").resultado);
		assertEquals(Resultado.ERROR_1, s.registrarEmpresa("ZazzyPants", "", "", "empresa@gmailcom", "purple").resultado);
	}
	
	@Test
	// Si nombre de empresa ya existe
	public void registrarEmpresaError2(){
		inicializarOK();
		s.registrarEmpresa("ZazzyPants", "", "", "empresa@gmail.com", "purple");
		assertEquals(Resultado.ERROR_2,s.registrarEmpresa("ZazzyPants", "", "", "email@email.com", "red").resultado);
	}
	
	//////////// CIUDADES //////////// 
	
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
	// En el sistema ya hay registrados cantPuntos
	public void registrarCiudadError1(){
		puntos = 5;
		inicializarOK();
		assertEquals(Resultado.OK,s.registrarCiudad("Chicago", 41.878114, -87.629798).resultado);
		assertEquals(Resultado.OK,s.registrarCiudad("Seattle", 47.606210, -122.332071).resultado);
		assertEquals(Resultado.OK,s.registrarCiudad( "San_Francisco", 37.774930, -122.419416).resultado);
		assertEquals(Resultado.OK,s.registrarCiudad("Acapulco", 16.853109, -99.823653).resultado);
		assertEquals(Resultado.OK,s.registrarCiudad("Buenos_Aires", -34.603684, -58.381559).resultado);
		assertEquals(Resultado.ERROR_1,s.registrarCiudad("Quito", -0.180653, -78.467838).resultado);
		puntos = 15;
	}
	
	@Test
	// Ya existe un punto con las mismas coordenadas
	public void registrarCiudadError2(){
		inicializarOK();
		assertEquals(Resultado.OK,s.registrarCiudad("Chicago", 41.878114, -87.629798).resultado);
		assertEquals(Resultado.ERROR_2,s.registrarCiudad("Seattle", 41.878114, -87.629798).resultado);
	}
	
	//////////// DATA CENTERS //////////// 
	
	@Test
    public void registrarDCOK(){
    	registrarCiudadOK();
    	assertEquals(Resultado.OK,s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("B", -25.90, -55.16, "ClassyCat", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("C", -20.90, -50.16, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("D", -25.93, -55.11, "ClassyCat", 100, 100).resultado);
    }
	
	@Test
	// En el sistema ya hay registrados cantPuntos
	public void registrarDCError1(){
		puntos = 3;
		registrarEmpresaOK();
		assertEquals(Resultado.OK,s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("B", -25.90, -55.16, "ClassyCat", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("C", -20.90, -50.16, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.ERROR_1,s.registrarDC("D", -25.93, -55.11, "ClassyCat", 100, 100).resultado);
		puntos = 15;
	}
	
	@Test
	// capacidad es <= 0
	public void registrarDCError2(){
		registrarEmpresaOK();
		assertEquals(Resultado.ERROR_2,s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 0, 100).resultado);
    	assertEquals(Resultado.ERROR_2,s.registrarDC("B", -25.90, -55.16, "ClassyCat", -100, 100).resultado);
	}
	
	@Test
	// ya existe un punto con las mismas coordenadas, sea DC o Ciudad
	public void registrarDCError3(){
		registrarEmpresaOK();
		assertEquals(Resultado.OK,s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.ERROR_3,s.registrarDC("B", 47.0, -122.0, "ClassyCat", 100, 100).resultado);
    	registrarCiudadOK();
    	// DC con coord IGUALES a una ciudad
    	assertEquals(Resultado.ERROR_3,s.registrarDC("B", 41.878114, -87.629798, "ClassyCat", 100, 100).resultado);
	}
	
	@Test
	// No existe una empresa con el nombre ingresado
	public void registrarDCError4(){
		registrarEmpresaOK();
		assertEquals(Resultado.ERROR_4,s.registrarDC("A", 47.0, -122.0, "Moriarty", 100, 100).resultado);
	}
	
	//////////// TRAMOS //////////// 
	
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

	//////////// PUNTOS //////////// 

	@Test
	public void eliminarPuntoOK(){
		registrarDCOK();
		assertEquals(Resultado.OK, s.eliminarPunto(47.0, -122.0).resultado); // esta dando error esto tambien, por las mismas razones

	}

	
    //s.mapaEstado();
    //s.listadoEmpresas();
}
