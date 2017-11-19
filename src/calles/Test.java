package calles;

import java.io.File;
import java.io.IOException;

public class Test {
	
	public static void main (String[] args) throws IOException {
		File dir = new File(".//IN//");
		
		// para generar caso de fatiga
		/*GeneradorFatiga generador = new GeneradorFatiga(new File(".//IN//casoFatiga.in"));
		generador.generar();*/
		
		for (File arch : dir.listFiles()) {
			Grafo grafo = new Grafo(arch);
			grafo.resolver();
			grafo.imprimir(new File(".//OUT//" + arch.getName().replace(".in", ".out")));
		}
	}
}
