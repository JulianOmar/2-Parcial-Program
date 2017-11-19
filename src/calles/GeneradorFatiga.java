package calles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GeneradorFatiga {

	File archivoOut;
	
	public GeneradorFatiga (File arch) {
		this.archivoOut = arch;
	}
	
	// genera un grafo con CANT_ESQUINAS de nodos y CANT_CALLES de aristas
	// los largos de las calles son aleatorios por lo que el camino final es impredecible
	public void generar () throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(archivoOut));
		Random ran = new Random();
		
		final int CANT_ESQUINAS = 80000;
		final int CANT_CALLES = 250000;
		final int ESQ_COLECTIVO = 200;
		final int ESQ_ESCUELA = 65575;
		
		out.println(CANT_ESQUINAS + " " + ESQ_COLECTIVO + " " + ESQ_ESCUELA);
		out.println(CANT_CALLES);
		for(int i = 0; i < CANT_CALLES; i++) {
			out.print((i % CANT_ESQUINAS) + 1);
			out.print(" ");
			out.print(((((i + 1) % CANT_ESQUINAS) + 1) + (i / CANT_ESQUINAS)) % CANT_ESQUINAS + 1);
			out.print(" ");
			out.println(ran.nextInt(50) + 1);
		}
		
		out.close();
	}
}
