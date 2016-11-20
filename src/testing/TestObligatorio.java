package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import sistema.Retorno;
import sistema.Sistema;

public class TestObligatorio {
	
		@Test
		public void testInicializarDestruirSistema() {
			Sistema s = new Sistema();
			s.inicializarSistema(1);
			s.destruirSistema();
			s.inicializarSistema(1);
		}

		// Tests Empresas
		@Test
		public void testregistrarEmpresa() {
			Sistema s = new Sistema();
			s.inicializarSistema(1);

			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Amazon", "2021 7th Ave, Seattle, WA 98121", "USA", "info@amazon.com", "YELLOW").resultado);
		}
		
		// Email invalido
		@Test
		public void testregistrarEmpresaEmailIncorrecto() {
			Sistema s = new Sistema();
			s.inicializarSistema(1);

			assertEquals(Retorno.Resultado.ERROR_1, 
					s.registrarEmpresa("Amazon", "2021 7th Ave, Seattle, WA 98121", "USA", "email invalido", "YELLOW").resultado);
		}

		// Empresa duplicada
		@Test
		public void testregistrarEmpresaError2() {
			Sistema s = new Sistema();
			s.inicializarSistema(1);

			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Amazon", "2021 7th Ave, Seattle, WA 98121", "USA", "info@amazon.com", "YELLOW").resultado);
			assertEquals(Retorno.Resultado.ERROR_2, 
					s.registrarEmpresa("Amazon", "A", "A", "info@amazon.com", "YELLOW").resultado);
		}
		
		// Listado de empresas
		@Test
		public void testListadoEmpresa() {
			Sistema s = new Sistema();
			s.inicializarSistema(1);
			
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Microsoft", "1000 Town Center Dr., Suite 1930, Southfield, MI 48075", "USA", "hello@microsoft.com", "WHITE").resultado);
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Amazon", "2021 7th Ave, Seattle, WA 98121", "USA", "info@amazon.com", "YELLOW").resultado);
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Antel", "Guatemala 1075", "Uruguay", "info@antel.uy", "BLUE").resultado);
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("IBM", "1000 Town Center Dr., Suite 1930, Southfield, MI 48075", "USA", "hello@ibm.com", "BLACK").resultado);
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Heroku", "En algun lugar", "England", "hi@heorku.com", "RED").resultado);
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Elastic", "Otro lado", "Ireland", "hi@elastic.com", "ORANGE").resultado);
			
			Retorno res = s.listadoEmpresas(); 
			assertEquals(Retorno.Resultado.OK, res.resultado);
			
			assertTrue(res.valorString.contains("Amazon"));
			assertTrue(res.valorString.contains("Elastic"));
			assertTrue(res.valorString.contains("Antel"));
			assertTrue(res.valorString.contains("IBM"));
			assertTrue(res.valorString.contains("Microsoft"));
			assertTrue(res.valorString.contains("Heroku"));
			
		}
		
		// Listado de empresas Ordenado
			@Test
			public void testListadoEmpresaOrdenado() {
				Sistema s = new Sistema();
				s.inicializarSistema(1);
				
				assertEquals(Retorno.Resultado.OK, 
						s.registrarEmpresa("Microsoft", "1000 Town Center Dr., Suite 1930, Southfield, MI 48075", "USA", "hello@microsoft.com", "WHITE").resultado);
				assertEquals(Retorno.Resultado.OK, 
						s.registrarEmpresa("Amazon", "2021 7th Ave, Seattle, WA 98121", "USA", "info@amazon.com", "YELLOW").resultado);
				assertEquals(Retorno.Resultado.OK, 
						s.registrarEmpresa("Antel", "Guatemala 1075", "Uruguay", "info@antel.uy", "BLUE").resultado);
				assertEquals(Retorno.Resultado.OK, 
						s.registrarEmpresa("IBM", "1000 Town Center Dr., Suite 1930, Southfield, MI 48075", "USA", "hello@ibm.com", "BLACK").resultado);
				assertEquals(Retorno.Resultado.OK, 
						s.registrarEmpresa("Heroku", "En algun lugar", "England", "hi@heorku.com", "RED").resultado);
				assertEquals(Retorno.Resultado.OK, 
						s.registrarEmpresa("Elastic", "Otro lado", "Ireland", "hi@elastic.com", "ORANGE").resultado);
				
				Retorno res = s.listadoEmpresas(); 
				assertEquals(Retorno.Resultado.OK, res.resultado);
				
				assertTrue(res.valorString.contains("Amazon"));
				assertTrue(res.valorString.contains("Elastic"));
				assertTrue(res.valorString.contains("Antel"));
				assertTrue(res.valorString.contains("IBM"));
				assertTrue(res.valorString.contains("Microsoft"));
				assertTrue(res.valorString.contains("Heroku"));
				// Chequear que esten en orden alfabetico
				assertTrue(res.valorString.indexOf("Amazon")	< res.valorString.indexOf("Antel"));
				assertTrue(res.valorString.indexOf("Antel")		< res.valorString.indexOf("Elastic"));
				assertTrue(res.valorString.indexOf("Elastic")	< res.valorString.indexOf("Heroku"));
				assertTrue(res.valorString.indexOf("Heroku")	< res.valorString.indexOf("IBM"));
				assertTrue(res.valorString.indexOf("IBM")		< res.valorString.indexOf("Microsoft"));
			
			}
			
			
		// Helper para usar en otros TESTS
		private void registrarEmpresas(Sistema s) {
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Amazon", "2021 7th Ave, Seattle, WA 98121", "USA", "info@amazon.com", "YELLOW").resultado);
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Antel", "Guatemala 1075", "Uruguay", "info@antel.uy", "BLUE").resultado);
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("Microsoft", "1000 Town Center Dr., Suite 1930, Southfield, MI 48075", "USA", "hello@microsoft.com", "WHITE").resultado);
			assertEquals(Retorno.Resultado.OK, 
					s.registrarEmpresa("IBM", "1000 Town Center Dr., Suite 1930, Southfield, MI 48075", "USA", "hello@ibm.com", "BLACK").resultado);
		}

		// TEST CIUDADES
		@Test
		public void testRegistrarCiudad() {
			
			Sistema s = new Sistema();
			s.inicializarSistema(1);
			assertEquals(Retorno.Resultado.OK, s.registrarCiudad("Paysandu", -32.3105104,-58.0759192).resultado);

		}
		
		@Test
		public void testRegistrarCiudadErrorGrafoCompleto() {
			Sistema s = new Sistema();
			s.inicializarSistema(1);
			assertEquals(Retorno.Resultado.OK, s.registrarCiudad("Paysandu", -32.3105104,-58.0759192).resultado);
			assertEquals(Retorno.Resultado.ERROR_1, s.registrarCiudad("Salto", -32.00,-58.00).resultado);
		}
		
		@Test
		public void testRegistrarCiudadDuplicada() {
			Sistema s = new Sistema();
			s.inicializarSistema(2);
			assertEquals(Retorno.Resultado.OK, s.registrarCiudad("Paysandu", -32.3105104,-58.0759192).resultado);
			assertEquals(Retorno.Resultado.ERROR_2, s.registrarCiudad("Salto", -32.3105104,-58.0759192).resultado);
		}


		// DATACENTERS
		@Test
		public void testRegistrarCentroOK() {
			Sistema s = new Sistema();
			s.inicializarSistema(10);
			registrarEmpresas(s);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("San Pablo", -23.6821604,-46.8754984, "Amazon", 100, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("Montevideo", -34.8198625,-56.3702923, "Antel", 10, 10).resultado);
		}


		// Cantidad maxima de puntos alcanzada
		@Test
		public void testRegistrarCentroError1() {
			Sistema s = new Sistema();
			s.inicializarSistema(1);
			registrarEmpresas(s);
			s.registrarCiudad("San Pablo", -23.6821604,-46.8754984);
			assertEquals(Retorno.Resultado.ERROR_1, s.registrarDC("San Pablo", -23.6821604,-46.8754984, "Amazon", 100, 5).resultado);
		}
		
		// Capacidad incorrecta
		@Test
		public void testRegistrarCentroError2() {
			Sistema s = new Sistema();
			s.inicializarSistema(2);
			registrarEmpresas(s);
			assertEquals(Retorno.Resultado.ERROR_2, s.registrarDC("Centro 1", -32.3105104,-58.0759192,"Amazon",-1, 10).resultado);
			assertEquals(Retorno.Resultado.ERROR_2, s.registrarDC("Centro 1", -32.3105104,-58.0759192,"Amazon", 0, 10).resultado);
		}
		
		//Ya existe punto
		@Test
		public void testRegistrarCentroError3() {
			Sistema s = new Sistema();
			s.inicializarSistema(2);
			registrarEmpresas(s);
			s.registrarCiudad("Paysandu", -32.3105104,-58.0759192);
			assertEquals(Retorno.Resultado.ERROR_3, s.registrarDC("Centro 1", -32.3105104,-58.0759192, "Amazon", 10, 10).resultado);
		}
		
		//No existe empresa
		@Test
		public void testRegistrarCentroError4() {
			Sistema s = new Sistema();
			s.inicializarSistema(2);
			assertEquals(Retorno.Resultado.ERROR_4, s.registrarDC("Centro 1", -32.3105104,-58.0759192, "Armazon", 10, 10).resultado);
		}
		
		// TRAMOS
		@Test
		public void testRegistrarTramoOK() {
			Sistema s = new Sistema();
			s.inicializarSistema(10);
			registrarEmpresas(s);
			s.registrarCiudad("Paysandu", -32.3105104,-58.0759192);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("San Pablo", -23.6821604,-46.8754984, "Amazon", 100, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("Montevideo", -34.8198625,-56.3702923, "Antel", 10, 10).resultado);
			
			assertEquals(Retorno.Resultado.OK, s.registrarTramo(-32.3105104,-58.0759192, -23.6821604,-46.8754984, 10).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarTramo(-32.3105104,-58.0759192, -34.8198625,-56.3702923, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarTramo(-23.6821604,-46.8754984, -34.8198625,-56.3702923, 3).resultado);
		}

		//Peso incorrecto
		@Test
		public void testRegistrarTramoError1() {
			Sistema s = new Sistema();
			s.inicializarSistema(10);
			registrarEmpresas(s);
			assertEquals(Retorno.Resultado.OK, s.registrarCiudad("Paysandu", -32.3105104,-58.0759192).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("San Pablo", -23.6821604,-46.8754984, "Amazon", 100, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("Montevideo", -34.8198625,-56.3702923, "Antel", 10, 10).resultado);
			assertEquals(Retorno.Resultado.ERROR_1, s.registrarTramo(-32.3105104,-58.0759192, -32.3105100,-58.0759192, -1).resultado);
		}
		
		//Punto no existe
		@Test
		public void testRegistrarTramoError2() {
			Sistema s = new Sistema();
			s.inicializarSistema(10);
			registrarEmpresas(s);
			assertEquals(Retorno.Resultado.OK, s.registrarCiudad("Paysandu", -32.3105104,-58.0759192).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("San Pablo", -23.6821604,-46.8754984, "Amazon", 100, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("Montevideo", -34.8198625,-56.3702923, "Antel", 10, 10).resultado);
			assertEquals(Retorno.Resultado.ERROR_2, s.registrarTramo(-32.99999,-58.0759192, -32.3105100,-58.0759192, 10).resultado);
			assertEquals(Retorno.Resultado.ERROR_2, s.registrarTramo(-32.3105100,-58.0759100, -32.999999,-58.0759192, 10).resultado);
		}

		// Tramo ya existe
		@Test
		public void testRegistrarTramoError3() {
			Sistema s = new Sistema();
			s.inicializarSistema(10);
			registrarEmpresas(s);
			assertEquals(Retorno.Resultado.OK, s.registrarCiudad("Paysandu", -32.3105104,-58.0759192).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("San Pablo", -23.6821604,-46.8754984, "Amazon", 100, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("Montevideo", -34.8198625,-56.3702923, "Antel", 10, 10).resultado);

			assertEquals(Retorno.Resultado.OK, s.registrarTramo(-32.3105104,-58.0759192, -23.6821604,-46.8754984, 10).resultado);
			assertEquals(Retorno.Resultado.ERROR_3, s.registrarTramo(-32.3105104,-58.0759192, -23.6821604,-46.8754984, 10).resultado);
			assertEquals(Retorno.Resultado.ERROR_3, s.registrarTramo(-23.6821604,-46.8754984, -32.3105104,-58.0759192, 10).resultado);
		}


		// MAPA estado
		@Test
		public void testMapaMundial() {
			Sistema s = new Sistema();
			s.inicializarSistema(10);
			registrarEmpresas(s);
			assertEquals(Retorno.Resultado.OK, s.registrarCiudad("SantiagodeChile", -33.4724727,-70.9100258).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarCiudad("Cordoba", -31.3993438,-64.3344307).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("Montevideo", -34.8198625,-56.3702923, "Antel", 10, 10).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("SanPablo", -23.6821604,-46.8754984, "Amazon", 100, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("WestVirginia", 38.8986941,-82.4253711, "Amazon", 100, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("Dublin", 53.3242381,-6.3857874, "Amazon", 100, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.registrarDC("Richmond", 37.5246403,-77.5633018, "Microsoft", 100, 5).resultado);
			assertEquals(Retorno.Resultado.OK, s.mapaEstado().resultado);
		}
		
		
		// TODO: Eliminar Tramo
		
		
		// TODO: Eliminar Punto

		
		// TODO: Procesar Informacion
		
		
		// TODO: Listado red minima
		
		

}
