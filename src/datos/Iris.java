package datos;

import java.util.ArrayList;
/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class Iris implements ARFF{
	
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

	}
	
	@Override
	public float getAtributo(int i){
		return (Float)atributos.get(i);
	}
	
	@Override
	public void setAtributo(int i, float val){
		atributos.set(i, new Float(val));
	}
	
	@Override
	public int getNumAtributos(){
		return atributos.size();
	}
	
	@Override
	public int getClase(){
		return clase;
	}
	
}
