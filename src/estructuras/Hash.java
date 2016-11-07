package estructuras;

import dominio.Punto;

public class Hash {

	// hash de DC capaz?
	//si no vamos a guardar las ciudades en el hash si, me parece mas generico que sean puntos.
	private Punto[] table;
    private int sizeTable;

    public Punto[] getTable() {
        return table;
    }

    public void setTable(Punto[] tabla) {
        this.table = tabla;
    }

    public int getSizeTable() {
        return sizeTable;
    }

    public void setSizeTable(int tamanioTabla) {
        this.sizeTable = tamanioTabla;
    }

    /*
     * Constructor
     */
    public Hash(int t) {
        this.sizeTable = t;
        this.table = new Punto[t];
    }
//Esto hay que verlo en clase
    public int hashCode(double coordX, double coordY) {
        String tostring = String.valueOf(coordX) + String.valueOf(coordY);
        int num = 0;
        for (int i = 0; i < tostring.length(); i++) {
        	num += (int) tostring.charAt(i);
        }
        return num;
         //return num % sizeTable;
    }
    
	public boolean perteneceAHash(double x, double y) {
		int posicion = hashCode(x, y);
        boolean aux = true;
        int contador = 0;
        while (aux) {
            if (contador < table.length) {
            	contador += 1;
                if (table[posicion] != null) {
                    if (!(table[posicion].getCoordX().equals(0.0) && table[posicion].getCoordY().equals(0.0))) {
                        if (table[posicion].getCoordX() == x && table[posicion].getCoordY() == y) {
                            return true;
                        } else {
                        	posicion += 1;
                            if (posicion == table.length) {
                            	posicion = 0;
                            }
                        }
                    } else {
                    	posicion += 1;
                        if (posicion == table.length) {
                        	posicion = 0;
                        }
                    }
                } else {
                	aux = false;
                }
            } else {
            	aux = false;
            }
        }
        return false;
	}

	public int insertarEnHash(Punto v) {
		int posicion = hashCode(v.getCoordX(), v.getCoordY());
        boolean aux = true;
        while (aux) {
            if (table[posicion] != null) {
                if (!(table[posicion].getCoordX().equals(0.0) && table[posicion].getCoordY().equals(0.0))) {
                	posicion += 1;
                    if (posicion == table.length) {
                    	posicion = 0;
                    }
                } else {
                	aux = false;
                }
            } else {
            	aux = false;
            }
        }
        table[posicion] = v;
        return posicion;
	}

	public int posicionPorCoord(Double x, Double y) {
		int posicion = hashCode(x, y);
		boolean aux = true;
		int contador = 0;
		while (aux) {
			if (contador < table.length) {
				contador += 1;
				if (table[posicion] != null) {
					if (table[posicion].getCoordX() == x && table[posicion].getCoordY() == y) {
						return posicion;
					} else {
						posicion += 1;
						if (posicion == table.length) {
							posicion = 0;
						}
					}
				} 
				else 
				{
					aux = false;
				}
			} 
			else 
			{
				aux = false;
			}
		}
		return -1;
	}
	
	//no estoy seguro del if acá.

	public Punto puntoPorPosicion(int posicion) {
		 if (table[posicion] != null && !(table[posicion].getCoordX() == 0.0 && table[posicion].getCoordY() == 0.0)) {
	            return table[posicion];
	        } else {
	            return null;
	        }
	}

	public void eliminarPunto(int posicion) {
		this.table[posicion] = null;
		
	}

	public void buscar(String nombre) {
		// TODO Auto-generated method stub
		
	}

}
