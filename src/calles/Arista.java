package calles;

public class Arista {
	private int nodoO;
	private int nodoD;
	private int costo;
	private int nombreArista;
	
	public Arista(int nodoO, int nodoD, int costo, int nombreArista) {
		super();
		this.nodoO = nodoO;
		this.nodoD = nodoD;
		this.costo = costo;
		this.nombreArista = nombreArista;
	}

	public int getNodoO() {
		return nodoO;
	}

	public void setNodoO(int nodoO) {
		this.nodoO = nodoO;
	}

	public int getNodoD() {
		return nodoD;
	}

	public void setNodoD(int nodoD) {
		this.nodoD = nodoD;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getNombreArista() {
		return nombreArista;
	}

	public void setNombreArista(int nombreArista) {
		this.nombreArista = nombreArista;
	}

	@Override
	public String toString() {
		return "Arista [nodoO=" + nodoO + ", nodoD=" + nodoD + ", costo=" + costo + ", nombreArista=" + nombreArista
				+ "]";
	}

}
