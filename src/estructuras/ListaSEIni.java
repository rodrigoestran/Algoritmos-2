package estructuras;

import java.util.Iterator;

import interfaces.ILista;

public class ListaSEIni implements ILista {

	public NodoLista inicio;
	
	public NodoLista getInicio() {
		return this.inicio;
	}

	@Override
	public void insertarInicio(Object elem) {
		inicio = new NodoLista(elem, inicio);
		
	}

	@Override
	public void borrarInicio() {
		if (!this.esVacia()){
            this.inicio=this.inicio.getSig();
        }
	}

	@Override
	public boolean esVacia() {
		 if (this.inicio==null)
            return true;
        else
            return false;
	}

	@Override
	public void vaciarLista() {
		inicio = null;
		
	}

	@Override
	public int largo() {
		int cont=0;
        if (!this.esVacia()){
            NodoLista aux=this.inicio;
            while (aux!=null){
                 aux=aux.getSig();
                 cont ++;
            }
        }
        return cont;
	}

	@Override
	public boolean existe(Object elem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Object> iterator() {
		return new Iterator<Object>() {
			
			private NodoLista aux = inicio;
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public Object next() {
				Object actual = aux.getDato();
				aux = aux.getSig();
				return actual;
			}
			
			@Override
			public boolean hasNext() {
				return aux != null;
			}
			
		};
	}

	@Override
	public String informe() {
		NodoLista aux = inicio;
		String informe = aux.getDato().toString();
		aux = aux.getSig();
		while (aux != null){
			informe += "|" + aux.getDato().toString();
			aux = aux.getSig();
		}
		return informe;
	}

}
