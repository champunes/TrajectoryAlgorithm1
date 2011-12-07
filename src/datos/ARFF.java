package datos;

/**
 *
 * @author champunes
 */
public interface ARFF {
	
	public float getAtributo(int i);
	public void setAtributo(int i,float val);
	public int getNumAtributos();
	public int getClase();
	
}
