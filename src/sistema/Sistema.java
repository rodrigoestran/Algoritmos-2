package sistema;

import dominio.Ciudad;
import dominio.DataCenter;
import dominio.Empresa;
import dominio.SSDataCenter;
import estructuras.ABB;
import interfaces.ILista;
import interfaces.ISistema;
import sistema.Retorno.Resultado;


public class Sistema implements ISistema {
	private int cantPuntos;
	private SSDataCenter ssdc;
	private ABB empresas; // las empresas son un arbol
	
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
		Empresa newEmp = new Empresa(nombre, direccion, email_contacto, color, pais);
		if (buscarEmpresa(nombre) != null){
			if (newEmp.validar()) {
				this.empresas.insertar(newEmp);
				return new Retorno(Resultado.OK);
			}
			else 
				return new Retorno(Resultado.ERROR_1);
		}
		else return new Retorno(Resultado.ERROR_2);
	}

	@Override
	public Retorno registrarCiudad(String nombre, Double coordX, Double coordY) {
		if (ssdc.getMapa().tieneLugarDisponible() != -1){
			Ciudad p = new Ciudad(coordX, coordY, nombre);
			boolean r = ssdc.agregarPunto(p);
			if (r) return new Retorno(Resultado.OK);
			return new Retorno(Resultado.ERROR_2);
		}
		return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno registrarDC(String nombre, Double coordX, Double coordY,
			String empresa, int capacidadCPUenHoras, int costoCPUporHora) {
		if (ssdc.getMapa().tieneLugarDisponible() != -1){
			if (capacidadCPUenHoras > 0){
				Empresa e = buscarEmpresa(nombre);
				if (e != null){
					DataCenter p = new DataCenter(nombre, e, capacidadCPUenHoras, costoCPUporHora, coordX, coordY);
					boolean r = ssdc.agregarPunto(p);
					if (r) return new Retorno(Resultado.OK);
					return new Retorno(Resultado.ERROR_3);
				}
				return new Retorno(Resultado.ERROR_4);	
			}
			return new Retorno(Resultado.ERROR_2);
		}
		return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi,
			Double coordXf, Double coordYf, int peso) {
		return ssdc.registrarTramo(coordXi, coordYi, coordXf, coordYf, peso);

	}

	@Override
	public Retorno eliminarTramo(Double coordXi, Double coordYi,
			Double coordXf, Double coordYf) {
		return ssdc.eliminarTramo(coordXi, coordYi, coordXf, coordYf);
	}

	@Override
	public Retorno eliminarPunto(Double coordX, Double coordY) {
		return ssdc.eliminarPunto(coordX, coordY);
	}

	@Override
	public Retorno mapaEstado() {
		ssdc.crearMapa();
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno procesarInformacion(Double coordX, Double coordY,
			int esfuerzoCPUrequeridoEnHoras) {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno listadoRedMinima() {
		return new Retorno(Resultado.OK, ssdc.listarRedMinima(), 0);
	}

	@Override
	public Retorno listadoEmpresas() {
		// TODO Auto-generated method stub
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	
	public Empresa buscarEmpresa(String nom){
		return this.empresas.pertenece(nom);
	}
}
