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
    	assertEquals(Resultado.OK,s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 40, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("B", -15.90, -55.16, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("C", -20.90, -50.16, "ClassyCat", 100, 3).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("D", -25.93, -55.11, "ClassyCat", 100, 100).resultado);
    }
	
	@Test
	// En el sistema ya hay registrados cantPuntos
	public void registrarDCError1(){
		puntos = 3;
		registrarEmpresaOK();
		assertEquals(Resultado.OK,s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("B", -15.90, -55.16, "ClassyCat", 100, 100).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("C", -20.90, -50.16, "ZazzyPants", 100, 100).resultado);
    	assertEquals(Resultado.ERROR_1,s.registrarDC("D", -25.93, -55.11, "ClassyCat", 100, 100).resultado);
		puntos = 15;
	}
	
	@Test
	// capacidad es <= 0
	public void registrarDCError2(){
		registrarEmpresaOK();
		assertEquals(Resultado.ERROR_2,s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 0, 100).resultado);
    	assertEquals(Resultado.ERROR_2,s.registrarDC("B", -15.90, -55.16, "ClassyCat", -100, 100).resultado);
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
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, -15.90, -55.16, 270).resultado); // A -- B
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, -25.93, -55.11, 25).resultado); // A -- D
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0,  41.878114, -87.629798, 20).resultado); // A -- Chicago
    	assertEquals(Resultado.OK,s.registrarTramo(-20.90, -50.16,  41.878114, -87.629798, 9).resultado); // C -- Chicago
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, 37.774930, -122.419416, 3).resultado); // A -- SF
    	assertEquals(Resultado.OK,s.registrarTramo(-15.90, -55.16, 37.774930, -122.419416, 5).resultado); // B -- SF
    	assertEquals(Resultado.OK,s.registrarTramo(37.774930, -122.419416, 41.878114, -87.629798, 2).resultado); // SF -- Chicago    	
    	assertEquals(Resultado.OK,s.registrarTramo(-20.90, -50.16,  -15.90, -55.16, 11).resultado); // C -- B
    	assertEquals(Resultado.OK,s.registrarTramo(-25.93, -55.11,  47.606210, -122.332071, 10).resultado); // D -- Seattle
    	assertEquals(Resultado.OK,s.registrarTramo(-20.90, -50.16,  47.606210, -122.332071, 9).resultado); // Seattle -- C
    	assertEquals(Resultado.OK,s.registrarTramo(41.878114, -87.629798,  47.606210, -122.332071, 5).resultado); // Seattle -- Chicago
    }
	
	@Test
	// Peso es <= 0
	public void registrarTramoError1(){
		registrarDCOK();
		assertEquals(Resultado.ERROR_1,s.registrarTramo(47.0, -122.0, -15.90, -55.16, 0).resultado); // A -- B
    	assertEquals(Resultado.ERROR_1,s.registrarTramo(47.0, -122.0, -25.93, -55.11, -10).resultado); // A -- D
	}

	@Test
	// coordenada X o Y no existe
	public void registrarTramoError2(){
		assertEquals(Resultado.ERROR_2,s.registrarTramo(47.0, -122.0, -25.10, -55.16, 20).resultado); // A -- B
    	assertEquals(Resultado.ERROR_2,s.registrarTramo(47.0, -122.0, -25.93, -25.11, 10).resultado); // A -- D
	}
	
	@Test
	// ya existe un tramo que va de X a Y
	public void registrarTramoError3(){
		assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, -15.90, -55.16, 25).resultado); // A -- B
    	assertEquals(Resultado.ERROR_3,s.registrarTramo(47.0, -122.0, -15.90, -55.16, 25).resultado); // A -- D
	}
	
	@Test
	public void eliminarTramoOK(){
		registrarTramoOK();
		assertEquals(Resultado.OK,s.eliminarTramo(-20.90, -50.16,  41.878114, -87.629798).resultado);
	}
	
	@Test
	// No existen algunos de los puntos coordI o coordF
	public void eliminarTramoError1(){
		registrarTramoOK();
		assertEquals(Resultado.ERROR_1,s.eliminarTramo(-20.90, -51.16,  41.878114, -87.629798).resultado);
		assertEquals(Resultado.ERROR_1,s.eliminarTramo(-20.90, -50.16,  41.88810, -87.629798).resultado); 
	}
	
	@Test
	// No existe un tramo registrado desde los puntos inicio a fin
	public void eliminarTramoError2(){
		registrarTramoOK();
		assertEquals(Resultado.ERROR_2,s.eliminarTramo(47.0, -122.0, 47.606210, -122.332071).resultado); // A -- Seattle
	}
	
	
	//////////// PUNTOS //////////// 

	@Test
	public void eliminarPuntoOK(){
		registrarDCOK();
		assertEquals(Resultado.OK, s.eliminarPunto(47.0, -122.0).resultado); 
	}

	@Test
	public void eliminarPuntoError1(){
		registrarDCOK();
		assertEquals(Resultado.ERROR_1, s.eliminarPunto(43.0, -162.0).resultado); 
	}
	
	
	///////////PROCESAR INFORMACION///////////
	
	
	 @Test
     public void procesarInformacionOK() {
         registrarTramoOK();
         Retorno ret = s.procesarInformacion(47.0, -122.0, 40);
         System.out.println(ret.valorString);
         assertEquals(Retorno.Resultado.OK, ret.resultado);
         ret = s.procesarInformacion(47.0, -122.0, 80);
         System.out.println(ret.valorString);
         assertEquals(Retorno.Resultado.OK, ret.resultado);
     }
	 
	 
	 @Test
	 // no existe la coordenada
     public void procesarInformacionError1() {
         registrarTramoOK();
         Retorno ret = s.procesarInformacion(467.0, 232.65, 100);
         assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
         System.out.println(ret.valorString);
     }
	
	 @Test
	 // capacidad mayor que cualquiera
     public void procesarInformacionError2() {
         registrarTramoOK();
         Retorno ret = s.procesarInformacion(47.0, -122.0, 140);
         assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
         System.out.println(ret.valorString); // null
     }
	 
	 
	 /////////// RED MINIMA /////////////////////
	 @Test
	 public void listadoRedMinima(){
		 registrarTramoOK();
		 Retorno r = s.listadoRedMinima();
		 assertEquals(Retorno.Resultado.OK, r.resultado);
		 System.out.println(r.valorString);
	 }
	 
	 /////////// LISTADO EMPRESAS ///////////////
	    //s.listadoEmpresas();
	 
	 ///////// MAPA //////////////////////////
	 
	 @Test
     public void mapaEstadoOK(){
		 registrarTramoOK();
		 s.mapaEstado();
	 }
	 

}
