package calles;

public class NodoConCosto implements Comparable<NodoConCosto>{
	private int nodo;
	private int costo;
	
	public NodoConCosto(int nodo, int costo) {
		super();
		this.nodo = nodo;
		this.costo = costo;
	}

	public int getNodo() {
		return nodo;
	}

	public void setNodo(int nodo) {
		this.nodo = nodo;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	@Override
	public int compareTo(NodoConCosto o) {
		return ((Integer)this.costo).compareTo(o.costo);
	}
	
}
