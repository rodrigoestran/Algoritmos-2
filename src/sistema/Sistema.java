package sistema;

import dominio.Ciudad;
import dominio.DataCenter;
import dominio.Empresa;
import dominio.SSPuntos;
import estructuras.ABB;
import interfaces.IABB;
import interfaces.ISistema;
import sistema.Retorno.Resultado;


public class Sistema implements ISistema {
	private int cantPuntos;
	private SSPuntos ssp;
	private IABB empresas; // las empresas son un arbol
	
	@Override
	// PRE: ?
	// POS: sistema inicializado con cantPuntos nodos máximos en mapa
	public Retorno inicializarSistema(int cantPuntos) {
		if (cantPuntos > 0){
			this.cantPuntos = cantPuntos;
			ssp = new SSPuntos(this.cantPuntos);
			empresas = new ABB();
			return new Retorno(Resultado.OK);
		}
		return new Retorno(Resultado.ERROR_1);
	}

	@Override
	// PRE: El sistema esta inicializado 
	// POS: Sistema queda sin datos
	public Retorno destruirSistema() {
		ssp.destruir();
		empresas = null;
		return new Retorno(Resultado.OK);
	}

	@Override
	// PRE: lista de empresas ya fue inicializada.
	// POS: Se agregó una nueva instancia de empresa en la lista de empresas
	public Retorno registrarEmpresa(String nombre, String direccion,
			String pais, String email_contacto, String color) {
		Retorno r = new Retorno();
		Empresa newEmp = new Empresa(nombre, direccion, email_contacto, color, pais);
		if (buscarEmpresa(nombre) == null){
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
	// PRE: ??
	// POS: Se ingresa una ciudad como punto del mapa
	public Retorno registrarCiudad(String nombre, Double coordX, Double coordY) {
		if (ssp.getMapa().tieneLugarDisponible() != -1){
			Ciudad p = new Ciudad(coordX, coordY, nombre);
			boolean r = ssp.agregarPunto(p);
			if (r) return new Retorno(Resultado.OK);
			return new Retorno(Resultado.ERROR_2);
		}
		return new Retorno(Resultado.ERROR_1);
	}

	@Override
	// PRE: ??
	// POS: Se ingresa un Data Center como punto del mapa
	public Retorno registrarDC(String nombre, Double coordX, Double coordY,
			String empresa, int capacidadCPUenHoras, int costoCPUporHora) {
		if (ssp.getMapa().tieneLugarDisponible() != -1){
			if (capacidadCPUenHoras > 0){
				Empresa e = buscarEmpresa(empresa);
				if (e != null){
					DataCenter p = new DataCenter(nombre, e, capacidadCPUenHoras, costoCPUporHora, coordX, coordY);
					boolean r = ssp.agregarPunto(p);
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
	// PRE: ???
	// POS: Agrega un Arco que conecta inicio a fin en el mapa
	public Retorno registrarTramo(Double coordXi, Double coordYi,
			Double coordXf, Double coordYf, int peso) {
		return ssp.registrarTramo(coordXi, coordYi, coordXf, coordYf, peso);

	}

	@Override
	// PRE: ???
	// POS: Vacía un Arco desde inicio a fin, de forma de eliminar su conexión
	public Retorno eliminarTramo(Double coordXi, Double coordYi,
			Double coordXf, Double coordYf) {
		return ssp.eliminarTramo(coordXi, coordYi, coordXf, coordYf);
	}

	@Override
	public Retorno eliminarPunto(Double coordX, Double coordY) {
		return ssp.eliminarPunto(coordX, coordY);
	}

	@Override
	public Retorno mapaEstado() {
		ssp.crearMapa();
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno procesarInformacion(Double coordX, Double coordY,
			int esfuerzoCPUrequeridoEnHoras) {
		return this.ssp.procesarInformación(coordX, coordY, esfuerzoCPUrequeridoEnHoras);
	}

	@Override
	public Retorno listadoRedMinima() {
		return new Retorno(Resultado.OK, ssp.listarRedMinima(), 0);
	}

	@Override
	public Retorno listadoEmpresas() {
		String info = empresas.devolverInforme();
		System.out.println(info);
		Retorno r = new Retorno(Resultado.OK);
		r.valorString = info;
		return r;
	}

	
	public Empresa buscarEmpresa(String nom){
		return (Empresa)this.empresas.pertenece(nom);
	}
}
