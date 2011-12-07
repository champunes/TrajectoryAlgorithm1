package datos;

import java.util.ArrayList;
/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class Iris {
	
	private Integer instancia;
	private ArrayList atributos;
	private Integer clase;
	
	public Iris(int inst,String[] atr){
		
		instancia = new Integer(inst);
		atributos = new ArrayList();
		for(int i=0;i<atr.length-1;i++){
			atributos.add(new Float(atr[i]));
		}			
		clase = new Integer(atr[atr.length-1]);

		
		/*System.out.println("Soy la instancia "+instancia.toString()+". Mi clase es:"+clase.toString());
		System.out.println("Atributos:");
		System.out.print(atributos.get(0).toString()+"\t");
		System.out.print(atributos.get(1).toString()+"\t");
		System.out.print(atributos.get(2).toString()+"\t");
		System.out.print(atributos.get(3).toString()+"\t\n\n");*/
	}
	
	public float getAtributo(int i){
		return (Float)atributos.get(i);
	}
	
	public void setAtributo(int i, float val){
		atributos.set(i, new Float(val));
	}
	
	public int getNumAtributos(){
		return atributos.size();
	}
	
}
