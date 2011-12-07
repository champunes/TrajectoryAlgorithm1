package datos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class GestorARFF {
	
	private String nombreBD;
	ArrayList instanciasEntr;
	ArrayList instanciasTest;
		
	
	private void cargarFicheroARFF(ArrayList instancias, String t_fich) throws IOException{
		
		FileReader isr = new FileReader("ficheros/ARFF/"+nombreBD+t_fich);
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
					if(nombreBD.startsWith("iris"))
						instancias.add(new Iris(instancia,atributos));
					else{
						if(nombreBD.startsWith("pima"))
							instancias.add(new Pima(instancia,atributos));
						else{
							if(nombreBD.startsWith("wine"))
								instancias.add(new Wine(instancia,atributos));
						}
					}
					instancia++;
				}
				
			}
		}
		
		bf.close();
		isr.close();
		
	}
	
	private void normalizarDatos(ArrayList datos){
		
		float min = 100000000;
		float max = 0;
		
		ARFF ins = (ARFF)datos.get(0);
		for(int j=0;j<ins.getNumAtributos();j++){			
			for(int i=0;i<datos.size();i++){
				ins = (ARFF)datos.get(i);
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
				ins = (ARFF)datos.get(i);
				ins.setAtributo(j, (ins.getAtributo(j)-d1)/(d2-d1));
			}
		}
		
	}
	
	private void exportarFichero(ArrayList datos, String t_fich) throws IOException{
		
		FileWriter fw = new FileWriter("ficheros/"+nombreBD+t_fich,false);
		String linea = "";
		ARFF ins;
		
		for(int i=0;i<datos.size();i++){
			ins = (ARFF)datos.get(i);
			linea += ins.getAtributo(0);
			for(int j=1;j<ins.getNumAtributos();j++){
				linea += ","+ins.getAtributo(j);
			}
			linea += ","+ins.getClase() + "\n";
			fw.write(linea);
			linea = "";
		}
		
		fw.close();
		
	}
	
	public GestorARFF(String nombre) throws IOException{
		
		nombreBD = nombre;
		instanciasEntr = new ArrayList();
		instanciasTest = new ArrayList();
		
		cargarFicheroARFF(instanciasEntr,".tra");
		cargarFicheroARFF(instanciasTest,".tst");
		
		normalizarDatos(instanciasEntr);
		normalizarDatos(instanciasTest);
		
		exportarFichero(instanciasEntr,".ent");
		exportarFichero(instanciasTest,".tes");
		
	}
	
	public GestorARFF() throws IOException{
		
	}
	
}
