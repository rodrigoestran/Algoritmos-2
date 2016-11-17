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
/*	
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
	 
	 ///////// MAPA ////////////////////////////
	 
	 @Test
     public void mapaEstadoOK(){
		 registrarTramoOK();
		 s.mapaEstado();
	 }*/
	
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
		
		
	}
	

	@Test
    public void registrarDCOK(){
    	registrarCiudadOK();
    	assertEquals(Resultado.OK,s.registrarDC("A", 47.0, -122.0, "ZazzyPants", 100, 10).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("B", -15.90, -55.16, "ClassyCat", 30, 2).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("C", -20.90, -50.16, "ZazzyPants", 200, 10).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("D", -25.93, -55.11, "ClassyCat", 400, 2).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("E", -30.0, -55.3, "ZazzyPants", 50, 10).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("F", -25.93, -100.11, "ZazzyPants", 20, 10).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("G", -28.6, -22.1, "ClassyCat", 1000, 2).resultado);
    	assertEquals(Resultado.OK,s.registrarDC("H", -14.93, -42.9, "ClassyCat", 1200, 2).resultado);
    }
	 
	public void registrarTramoOK(){
    	registrarDCOK();
    	//para despues probar que lija camino directo de A-B, y no A-Chic-B
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, -15.90, -55.16, 8).resultado); // A -- B
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, 41.878114, -87.629798, 7).resultado); // A -- Chicago
    	assertEquals(Resultado.OK,s.registrarTramo(-15.90, -55.16, 41.878114, -87.629798, 6).resultado); // B -- Chicago
    	
    	//para despues probar que elija A-Seattle-C que va a ser 101, porque A-Seattle-D es 102 por el consto al ser de diferente empresa.
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, 47.606210, -122.332071, 1).resultado); // A -- Seattle
    	assertEquals(Resultado.OK,s.registrarTramo(-25.93, -55.11, 47.606210, -122.332071, 99).resultado);//D--Seattle
    	assertEquals(Resultado.OK,s.registrarTramo(-20.90, -50.16, 47.606210, -122.332071, 100).resultado);//C--Seattle
    	
    	//pr depues probar que saque esta conexión en red minima, antes de probar eso se va atener que generar tramo que una E a C.
    	assertEquals(Resultado.OK,s.registrarTramo(41.878114, -87.629798, 47.606210, -122.332071, 1).resultado);//Chicago--Seattle
    	assertEquals(Resultado.OK,s.registrarTramo(-34.603684, -58.381559, 47.606210, -122.332071, 1).resultado);//BsAs--Seattle
    	assertEquals(Resultado.OK,s.registrarTramo(-34.603684, -58.381559, 37.774930, -122.419416, 100).resultado);//BsAs--San francisco
    	
    	//todo lo relacionado a G tiene como obj probar que si mando una solic por 800 desde A, no va a llegar a G porque el grafo: E-G-H- Acapulco
    	//no esta conectado con A-Chicago-B-C-D-BsAs-San Francisco
    	
    	//descomento y agrego en segunda corrida, antes comento test para Error2, asi pruebo que G se elija cuando mando solicitud de 800 desde A.
    	assertEquals(Resultado.OK,s.registrarTramo(47.0, -122.0, -30.0, -55.3, 9).resultado); // A -- E
    	assertEquals(Resultado.OK,s.registrarTramo(-30.0, -55.3, -28.6, -22.1, 1).resultado);//E--G
    	assertEquals(Resultado.OK,s.registrarTramo(-14.93, -42.9, -28.6, -22.1, 11).resultado);//H--G
    	assertEquals(Resultado.OK,s.registrarTramo(-14.93, -42.9, 16.853109, -99.823653, 1).resultado);//H--Acapulco
    	
    	//assertEquals(Resultado.OK,s.registrarTramo(-15.90, -55.16, 37.774930, -122.419416, 100).resultado);//B--San francisco
    	assertEquals(Resultado.OK,s.registrarTramo(37.774930, -122.419416, 16.853109, -99.823653, 1).resultado);//San Francisco--Acapulco
    
    	
    }
	
	

	

	
	
	
//47.0, -122.0
	 @Test
    public void procesarInformacionOK() {
       registrarTramoOK();
        Retorno ret = s.procesarInformacion(-15.90, -55.16, 80);//s.procesarInformacion(-15.90, -55.16, 1100);//DESDE B TIENE QUE DEVOLVER A|10
        System.out.println(ret.valorString);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        /************************************************************************************/
        /*ret = s.procesarInformacion(47.0, -122.0, 120);//TIENE QUE DEVOLVER C|101
        System.out.println(ret.valorString);
        assertEquals(Retorno.Resultado.OK, ret.resultado);*/
        /************************************************************************************/
        
        /*ret = s.procesarInformacion(47.0, -122.0, 800);//ACA PRUEBO QUE G SE CONSIDERA PORQUE AGREGUÉ CONEXIÓN, COMENTAR ERROR 2 AGREGAR TRAMO COMENTADO.
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        System.out.println(ret.valorString);*/
        
    }
	 
	 
	/* 
	 public void procesarInformacionError1() {
         registrarTramoOK();
         Retorno ret = s.procesarInformacion(1.0, 1.65, 100);
         assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
         System.out.println(ret.valorString);
     }
	 
	 
	 @Test
	 // capacidad mayor que cualquiera
     public void procesarInformacionError2() {
         registrarTramoOK();
         Retorno ret = s.procesarInformacion(47.0, -122.0, 800);//ACA PRUEBO QUE G NO SE CONSIDERA COMO OPTION PORQUE NO HAY CONEXION.
         assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
         System.out.println(ret.valorString); // null
     }*/
	 
	/* @Test
	 public void listadoRedMinima(){
		 registrarTramoOK();
		 Retorno r = s.listadoRedMinima();
		 assertEquals(Retorno.Resultado.OK, r.resultado);
		 System.out.println(r.valorString);
	 }*/

}
