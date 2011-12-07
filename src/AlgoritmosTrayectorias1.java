

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

import datos.*;
import algoritmos.*;

/**
 *
 * @author champunes
 */
public class AlgoritmosTrayectorias1 {

	private static void cargarFichero(ArrayList instancias, String n_fich) throws IOException{
		
		FileReader isr = new FileReader(n_fich);
		BufferedReader bf = new BufferedReader(isr);
		
		HashMap<String,Integer> tradClases = new HashMap<String,Integer>();
		
		while(bf.ready()){
			String cadena = bf.readLine();
			if(cadena.startsWith("@attribute class")){
				//Seleccionamos el contenido entre {}
				String cls = cadena.substring(18, cadena.length()-1);
				//Limpiamos la cadena de espacios
				StringTokenizer st = new StringTokenizer(cls);
				cls="";
				while(st.hasMoreElements()){
					cls += st.nextToken(); 
				}				
				//Separamos la cadena en un vector de String (por las comas)
				String[] clases = cls.split(",");
			
				
				/*System.out.println("Numero de clases: "+clases.length);
				for(int i=0;i<clases.length;i++)
					System.out.println(clases[i]);*/
			
				
				//Creamos un traductor de clases texto a numeros
				for(int i=0;i<clases.length;i++){
					tradClases.put(clases[i], Integer.valueOf(i+1));
				}
				
			}
			
			if(cadena.startsWith("@data")){				
				int instancia = 1;
				while(bf.ready()){
					cadena = bf.readLine();
					//Limpiamos la cadena de espacios
					StringTokenizer st = new StringTokenizer(cadena);
					cadena="";					
					while(st.hasMoreElements()){
						cadena += st.nextToken(); 
					}
					//Separamos la cadena en un vector de String (por las comas)
					String[] atributos = cadena.split(",");
					//Traducimos la clase a un numero
					atributos[atributos.length-1] = tradClases.get(atributos[atributos.length-1]).toString();
					instancias.add(new Iris(instancia,atributos));
					instancia++;
				}
				
			}
		}
		
	}
	
	private static void normalizarDatos(ArrayList datos){
		
		float min = 100000000;
		float max = 0;
		
		Iris ins = (Iris)datos.get(0);
		for(int j=0;j<ins.getNumAtributos();j++){			
			for(int i=0;i<datos.size();i++){
				ins = (Iris)datos.get(i);
				float flot = ins.getAtributo(j);
				if(flot < min){
					min = flot;
				}
				if(flot > max){
					max = flot;
				}
			}
			float d1 = min, d2 = max;
			for(int i=0;i<datos.size();i++){
				ins = (Iris)datos.get(i);
				ins.setAtributo(j, (ins.getAtributo(j)-d1)/(d2-d1));
			}
		}
		
	}
	
	private static void exportarFichero(ArrayList datos, String n_fich){
		
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException{
		// TODO code application logic here
		
		ArrayList instanciasEntr = new ArrayList();
		ArrayList instanciasTest = new ArrayList();
		
		cargarFichero(instanciasEntr,args[0]);
		cargarFichero(instanciasTest,args[1]);
		
		normalizarDatos(instanciasEntr);
		normalizarDatos(instanciasTest);
	}
}
