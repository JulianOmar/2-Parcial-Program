package calles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Grafo {

	public static final int INFINITO = 9999;
	private int cantNodos;
	private int nodoInicio;
	private int nodoFin;
	private int costoFinal;
	Map<Integer, Arista> aristasOriginales;
	Map<Integer, Nodo> nodos;
	List<PadreConArista> camino;
	List<Integer> callesACambiar;
	
	public Grafo(File arch) throws IOException {
		Scanner sc = new Scanner(arch);
		cantNodos = sc.nextInt();
		nodoInicio = sc.nextInt() - 1;
		nodoFin = sc.nextInt() - 1;
		int cantAristas = sc.nextInt();
		
		nodos = new HashMap<Integer, Nodo>();
		aristasOriginales = new HashMap<Integer, Arista>();
		
		for (int i = 0; i < cantNodos; i++) {
			nodos.put(i, new Nodo(i));
		}
		
		for (int i = 0; i < cantAristas; i++) { 
			int nodoO = sc.nextInt() - 1;
			int nodoD = sc.nextInt() - 1;
			int costo = sc.nextInt();
			Arista arista = new Arista(nodoO, nodoD, costo, i);
			aristasOriginales.put(i, arista);
			nodos.get(nodoO).agregarArista(arista);
			nodos.get(nodoD).agregarArista(new Arista(nodoD, nodoO, costo, i));
		}
		
		sc.close();
	}

	/*
	 * El método resolver se basa en dijkstra con recorrido.
	 * Como lo implementa con cola de prioridad, la complejidad es O(A.log(N))
	 * Donde A = cantidad de aristas y N = cantidad de nodos
	 * Teniendo en cuenta que:
	 * cola.remove = O(log(N))
	 * visitados.add = O(1)
	 * nodos.get = O(1)
	 */
	public void resolver() {
		long tInicial = System.currentTimeMillis();
		int[] costos = new int[cantNodos];
		Set<Integer> visitados = new HashSet<Integer>();
		Queue<NodoConCosto> cola = new PriorityQueue<NodoConCosto>();
		PadreConArista[] padresConArista = new PadreConArista[cantNodos];
		
		camino = new LinkedList<PadreConArista>();
		
		for (int i = 0; i < cantNodos; i++) {
			costos[i] = INFINITO;
			padresConArista[i] = new PadreConArista(0, 0);
		}
		costos[nodoInicio] = 0;
		cola.add(new NodoConCosto(nodoInicio, 0));
		
		while(!cola.isEmpty()) {
			NodoConCosto nodoConCosto = cola.poll();
			int nombreNodo = nodoConCosto.getNodo();
			visitados.add(nombreNodo);
			Nodo nodo = nodos.get(nombreNodo);
			List<Arista> aristas = nodo.getAristas();
			
			for (Arista arista : aristas) {
				int nodoD = arista.getNodoD();
				int costoNuevo = nodoConCosto.getCosto() + arista.getCosto();
				if (!visitados.contains(nodoD) && costos[nodoD] > costoNuevo) {
					costos[nodoD] = costoNuevo;
					padresConArista[nodoD].setNodoPadre(nombreNodo);
					padresConArista[nodoD].setNombreArista(arista.getNombreArista());
					cola.add(new NodoConCosto(nodoD, costoNuevo));
				}
			}
		}
		
		// guardo el costo del recorrido más eficiente
		costoFinal = costos[nodoFin];
		
		// tengo que reconstruir el camino
		List<Arista> recorridoFinal = new LinkedList<Arista>();
		PadreConArista nodoPadre = padresConArista[nodoFin];
		int nodoAnterior = nodoFin;
		
		while(nodoAnterior != nodoInicio) { 
			// no asigno el costo porque ya sé que este es el camino más eficiente
			// solo me interesa el sentido de la calle
			recorridoFinal.add(new Arista(nodoPadre.getNodoPadre(), nodoAnterior, 0, nodoPadre.getNombreArista()));
			nodoAnterior = nodoPadre.getNodoPadre();
			nodoPadre = padresConArista[nodoAnterior];
		}
		
		// busco en el recorrido final que aristas deben cambiar de mano
		callesACambiar = new LinkedList<Integer>();
		for (int i = 0; i < recorridoFinal.size(); i++) {
			Arista aristaNueva = recorridoFinal.get(i);
			Arista aristaOriginal = aristasOriginales.get(aristaNueva.getNombreArista());
			
			// si la esquina de origen es distinta, entonces esa calle hay que cambiarla
			// de mano
			if (aristaNueva.getNodoO() != aristaOriginal.getNodoO()) {
				callesACambiar.add(aristaNueva.getNombreArista() + 1);
			}
		}

		long tFinal = System.currentTimeMillis();
		System.out.println("Tiempo de ejecución: " + (tFinal - tInicial));
	}

	public void imprimir(File arch) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(arch));
		// imprimo costo del recorrido óptimo
		out.println(costoFinal);
		// imprimo calles a cambiar
		for (int i = 0; i < callesACambiar.size(); i++) {
			out.print(callesACambiar.get(i) + " ");
		}
		
		out.close();
	}

}
