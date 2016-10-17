/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import dominio.DataCenter;
import interfaces.IABB;

/**
 *
 * @author alumnoFI
 */
public class ABB implements IABB{
	
	private Nodo raiz;
    private String informe;

    public ABB() {
        this.raiz = null;
        this.informe = "";
    }

    @Override
    public Nodo getRaiz() {
        return raiz;
    }

    @Override
    public boolean esArbolVacio() {
        return (raiz == null);
    }

    @Override
    public void mostrarPreOrder() {
        mostrarPreOrder(this.raiz);
    }

    @Override
    public void mostrarPreOrder(Nodo a) {
        if (a != null) {
            System.out.print(a.getDato() + "   ");
            mostrarPreOrder(a.getIzq());
            mostrarPreOrder(a.getDer());
        }
    }

   
    @Override
    public void devolverInforme() {
        devolverInforme(this.raiz);
    }

  
    
    @Override
    public void devolverInforme(Nodo a) {
        
        if (a != null) {
            devolverInforme(a.getIzq());
            this.informe+=a.getDato().toString() +  " | ";
            devolverInforme(a.getDer());
        }
       
    }

    @Override
    public void mostrarPosOrder() {
        mostrarPosOrder(this.raiz);
    }

    @Override
    public void mostrarPosOrder(Nodo a) {
        if (a != null) {
            mostrarPosOrder(a.getIzq());
            mostrarPosOrder(a.getDer());
            System.out.print(a.getDato() + "  ");
        }
    }

    @Override
    public boolean existeElemento(String e) {
        Nodo nodo = obtenerElemento(e, raiz);

        if (nodo != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existe(String e, Nodo a) {
        boolean existe;
        String nombre = a.getDato().getNombre();
        if (a == null) {
            existe = false;
        } else {
            if (e.equalsIgnoreCase(nombre)) {
                existe = true;
            } else if (e.compareTo(nombre)<0) {
                existe = existe(e, a.getIzq());
            } else {
                existe = existe(e, a.getDer());
            }
        }
        return existe;
    }

    @Override
    public Nodo obtenerElemento(String n, Nodo nodo) {
        
        if (nodo == null) {
            return nodo;
        } else {
            String nombre = nodo.getDato().getNombre();
            if (n.equalsIgnoreCase(nombre)){
                return nodo;
            } else if (n.compareTo(nombre) < 0) {
                return obtenerElemento(n, nodo.getIzq());
            } else {
                return obtenerElemento(n, nodo.getDer());
            }
        }
    }

    @Override
    public int cantNodos(Nodo nodo) {
        int cont = 0;
        if (nodo != null) {
            cont += cantNodos(nodo.getIzq()); 	
            cont++; 							
            cont += cantNodos(nodo.getDer());	

        }
        return cont;
    }

    @Override
    public int obtenerPeso(Nodo nodo) {
        int peso = 0;
        int peso_izq = 0;
        int peso_der = 0;

        if (nodo != null) {
            peso_izq = cantNodos(nodo.getIzq());
            peso_der = cantNodos(nodo.getDer());
            peso = peso_izq + peso_der;

        }
        return peso;
    }    
    
    @Override
    public void insertarElemento(DataCenter dc, Nodo nodo) {
        Nodo nuevo = null;

        if (this.esArbolVacio()) {
            this.raiz = new Nodo(dc);
        } else if (dc.compareTo(nodo) <0 ) {  
            if (nodo.getIzq() == null) {
                nuevo = new Nodo(dc);
                nodo.setIzq(nuevo);
            } else {
                insertarElemento(dc, nodo.getIzq());
            }
        } else if (dc.compareTo(nodo) > 0) { 
            if (nodo.getDer() == null) {
                nuevo = new Nodo(dc);
                nodo.setDer(nuevo);
            } else {
                insertarElemento(dc, nodo.getDer());
            }
        }
    }
    
    
    @Override
    public int cantHojas(Nodo nodo) {
        if (nodo.getDer() == null) {
            if (nodo.getIzq() == null) {
                return 1;
            } else {
                return cantHojas(nodo.getIzq());
            }
        } else if (nodo.getIzq() == null) {
            return cantHojas(nodo.getDer());
        } else {
            return cantHojas(nodo.getIzq()) + cantHojas(nodo.getDer());
        }
    }

    @Override
    public Nodo borrarMinimo(Nodo nodo) {
        if (nodo == null) {
            return nodo;
        }
        if (nodo.getIzq() != null) {
            nodo.setIzq(borrarMinimo(nodo.getIzq()));
            return nodo;
        } else {
            return nodo.getDer();
        }
    }

    @Override
    public void insertar(DataCenter dc) {
        raiz = insertar(dc, raiz);
    }

    private Nodo insertar(DataCenter dc, Nodo a) {
        if (a == null) {
            a = new Nodo(dc);
        } else if (dc.compareTo(a) < 0) {
            a.setIzq(insertar(dc, a.getIzq())); 
        } else if (dc.compareTo(a) > 0) {
            a.setDer(insertar(dc, a.getDer()));	
        }
        return a;
    }

    @Override
    public int altura() {
        // TO-DO
        return 0;
    }
    
    public Nodo Buscar(String s) {
		if(this.raiz != null){
			return Buscar(s, this.raiz);
		}
		return null;
	}
	
	
	public Nodo Buscar(String s, Nodo n){
        if(n == null){
            return null;
        }
        if(n.getDato().getNombre().equals(s)){
            return n;
        }
         		  		
        if(s.compareTo(n.getDato().getNombre()) < 0){
        	return Buscar(s, n.getIzq());
        }else{
            return  Buscar(s, n.getDer());
        }
    }
    
    public void eliminar(String s ) {
        raiz = eliminar(s, raiz );
    }
    public void eliminar(DataCenter dc ) {
        raiz = remove(dc, raiz );
    }
    
    private Nodo eliminar( String s, Nodo a ) {
        if( a == null ) {
            return a; 
        }

        if(s.compareTo(a.getDato().getNombre())<0 ) {
            a.setIzq(eliminar(s, a.getIzq()));
        } else if( s.compareTo(a.getDato().getNombre()) > 0 ) {
            a.setDer(eliminar(s, a.getDer() ));
        } else { 
            if( a.getIzq() != null && a.getDer() != null ) {
                a = Minimo(a.getDer());
                a.setDer(borrarMinimo(a.getDer()));
            }
            else {
                a = ( a.getIzq() != null ) ? a.getIzq() : a.getDer();
            }
        }
        return a;
    }   
    
    
    public boolean remove(String value) {
        if (raiz == null)
              return false;
        else {
              if (raiz.getDato().getNombre().equals(value)) {
                    Nodo aux = this.Minimo(raiz);
                    aux.setIzq(raiz);
                    boolean result = raiz.remove(value, aux);
                    raiz = raiz.getIzq();
                    return result;
              } else {
                    return raiz.remove(value, null);
              }
        }
  }

private Nodo remove( DataCenter dc, Nodo n ){
    if( n == null ){
        return n;
    }
    int compareResult = dc.compareTo(n);
    if( compareResult < 0 ){
        n.setIzq(remove( dc, n.getIzq()) );
    } else if( compareResult > 0 ){
        n.setDer(remove( dc, n.getDer()) );
    }else if( n.getIzq() != null && n.getDer() != null ){
        n.setDato(Minimo(n.getDer()).getDato());
        n.setDer(remove( n.getDato(), n.getDer()) );
    }else{
        n = ( n.getIzq() != null ) ? n.getIzq() : n.getDer();
    }
    return n;
}

private Nodo Minimo( Nodo nodo )
{
    if(nodo != null )
        while( nodo.getIzq() != null )
            nodo = nodo.getIzq();
    return nodo;
}

private Nodo Maximo( Nodo nodo )
{
    if(nodo != null )
        while( nodo.getDer() != null )
            nodo = nodo.getDer();
    return nodo;
}

@Override
public void mostrarInOrder() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public void mostrarInOrder(Nodo a) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

public String getInforme() {
    this.devolverInforme();
    return this.informe;
}
    
}
