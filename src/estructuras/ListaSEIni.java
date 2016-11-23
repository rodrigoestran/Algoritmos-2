package estructuras;

import interfaces.ILista;

public class ListaSEIni implements ILista {

	public NodoLista inicio;

	// GETTERS AND SETTERS//

	public NodoLista getInicio() {
		return this.inicio;
	}

	// COMPORTAMIENTO//

	@Override
	public void insertarInicio(Object elem) {
		inicio = new NodoLista(elem, inicio);

	}

	@Override
	public boolean esVacia() {
		if (this.inicio == null)
			return true;
		else
			return false;
	}

	@Override
	public void vaciarLista() {
		inicio = null;

	}
	@Override
	public String[] informe() {
		NodoLista aux = inicio;
		int costo = ((Arco)aux.getDato()).getDistancia();
		String informe = aux.getDato().toString();
		aux = aux.getSig();
		while (aux != null) {
			informe += "|" + aux.getDato().toString();
			costo += ((Arco)aux.getDato()).getDistancia();
			aux = aux.getSig();
		}
		return new String[]{informe, costo + ""};
	}

}
