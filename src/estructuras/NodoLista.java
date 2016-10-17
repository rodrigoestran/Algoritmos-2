package estructuras;

public class NodoLista {
	

    private Object dato;
    private NodoLista sig;

    public NodoLista(Object d) {
        this.dato = d;
    }

    public NodoLista(Object d, NodoLista s) {
        this.dato = d;
        this.sig = s;
    }

    public Object getDato() {
        return this.dato;
    }

    public void setDato(Object d) {
        this.dato = d;
    }

    public NodoLista getSig() {
        return this.sig;
    }

    public void setSig(NodoLista s) {
        this.sig = s;
    }

}
