package calles;

public class PadreConArista {
	private int nodoPadre;
	private int nombreArista;
	
	public PadreConArista(int nodoPadre, int nombreArista) {
		super();
		this.nodoPadre = nodoPadre;
		this.nombreArista = nombreArista;
	}

	public int getNodoPadre() {
		return nodoPadre;
	}

	public void setNodoPadre(int nodoPadre) {
		this.nodoPadre = nodoPadre;
	}

	public int getNombreArista() {
		return nombreArista;
	}

	public void setNombreArista(int nombreArista) {
		this.nombreArista = nombreArista;
	}

	@Override
	public String toString() {
		return "(P=" + nodoPadre + ", A=" + nombreArista + ")";
	}
}
