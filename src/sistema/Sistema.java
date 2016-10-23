package sistema;

import dominio.SSDataCenter;
import interfaces.ISistema;
import sistema.Retorno.Resultado;


public class Sistema implements ISistema {
	private int cantPuntos;
	private SSDataCenter ssdc;
	
	@Override
	public Retorno inicializarSistema(int cantPuntos) {
		if (cantPuntos > 0){
			this.cantPuntos = cantPuntos;
			ssdc = new SSDataCenter(this.cantPuntos);
			return new Retorno(Resultado.OK);
		}
		return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno destruirSistema() {
		ssdc.destruir();
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarEmpresa(String nombre, String direccion,
			String pais, String email_contacto, String color) {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarCiudad(String nombre, Double coordX, Double coordY) {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarDC(String nombre, Double coordX, Double coordY,
			String empresa, int capacidadCPUenHoras, int costoCPUporHora) {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi,
			Double coordXf, Double coordYf, int peso) {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno eliminarTramo(Double coordXi, Double coordYi,
			Double coordXf, Double coordYf) {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno eliminarPunto(Double coordX, Double coordY) {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno mapaEstado() {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno procesarInformacion(Double coordX, Double coordY,
			int esfuerzoCPUrequeridoEnHoras) {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno listadoRedMinima() {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno listadoEmpresas() {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

		
}
