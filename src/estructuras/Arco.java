package estructuras;

public class Arco {
	
	private Integer distancia;
	private Double coordXi;
	private Double coordYi;
	private Double coordXf;
	private Double coordYf;
	private boolean existe;
	
	public Integer getDistancia() {
		return this.distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}
	
	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}
	
	public Arco(){
		this.existe = false;
	}
	
	public Arco(Integer dist, Double coordXi, Double coordYi, Double coordXf, Double coordYf){
		this.distancia = dist;
		this.coordXi = coordXi;
		this.coordYi = coordYi;
		this.coordXf = coordXf;
		this.coordYf = coordYf;
		this.existe = true;
	}

	

	public Double getCoordXi() {
		return coordXi;
	}

	public void setCoordXi(Double coordXi) {
		this.coordXi = coordXi;
	}

	public Double getCoordYi() {
		return coordYi;
	}

	public void setCoordYi(Double coordYi) {
		this.coordYi = coordYi;
	}

	public Double getCoordXf() {
		return coordXf;
	}

	public void setCoordXf(Double coordXf) {
		this.coordXf = coordXf;
	}

	public Double getCoordYf() {
		return coordYf;
	}

	public void setCoordYf(Double coordYf) {
		this.coordYf = coordYf;
	}

}
