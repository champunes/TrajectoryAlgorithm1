import java.io.IOException;

import datos.*;
import algoritmos.*;

/**
 *
 * @author champunes
 */
public class AlgoritmosTrayectorias1 {
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException{
		// TODO code application logic here
		
		//Codigo para adaptar y normalizar los datos de una BD ARFF
		/*GestorARFF iris = new GestorARFF("iris");
		GestorARFF pima = new GestorARFF("pima");
		GestorARFF wine = new GestorARFF("wine");*/
		
		GestorARFF iris = new GestorARFF(1);
		GestorARFF pima = new GestorARFF(2);
		GestorARFF wine = new GestorARFF(3);
		
	}
}
