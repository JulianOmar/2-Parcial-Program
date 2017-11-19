package calles;

import java.util.LinkedList;
import java.util.List;

public class Nodo {
	private int nombre;
	private List<Arista> aristas;
	
	public Nodo(int nombre) {
		super();
		aristas = new LinkedList<Arista>();
		this.nombre = nombre;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public List<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(List<Arista> aristas) {
		this.aristas = aristas;
	}
	
	public void agregarArista(Arista arista) {
		this.aristas.add(arista);
	}

	@Override
	public String toString() {
		return "Nodo [nombre=" + nombre + ", aristas=" + aristas + "]";
	}
	
}
