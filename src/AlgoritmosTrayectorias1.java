

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author champunes
 */
public class AlgoritmosTrayectorias1 {

	private static void cargarFichero(String n_fich) throws IOException{
		
		FileReader isr = new FileReader(n_fich);
		BufferedReader bf = new BufferedReader(isr);
		
		while(bf.ready()){
			String cadena = bf.readLine();
			if(cadena.startsWith("@attribute class")){
				String cls = cadena.substring(18, cadena.length()-1);
				StringTokenizer st = new StringTokenizer(cls);
				cls="";
				while(st.hasMoreElements()){
					cls += st.nextToken(); 
				}				
				String[] clases = cls.split(",");
				System.out.println("Numero de clases: "+clases.length);
				for(int i=0;i<clases.length;i++)
					System.out.println(clases[i]);
			}
			
			if(cadena.startsWith("@data")){
				cadena = bf.readLine();
				
				System.out.println(cadena);
			}
		}
		
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException{
		// TODO code application logic here
		
		cargarFichero(args[0]);
		
	}
}
