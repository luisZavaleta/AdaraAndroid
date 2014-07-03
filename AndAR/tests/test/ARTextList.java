package lx.zv.test;

import java.util.ArrayList;

public class ARTextList extends ArrayList<String>{

	private static final long serialVersionUID = 1223677892160371139L;

	
	
@Override
public boolean add(String s) {
	return false;	
}

	/*
@Override
public boolean add(Object element) {
   // adding(element);
    return false;
}
*/

	
	
	
	/*
	private ArrayList<String> texto = new ArrayList<String>();

	
	
	public ArrayList<String> getTexto() {
		return texto;
	}
	
	public String getSingleTexto(int position) {
		return texto.get(position);

	}
	

	public void setTexto(ArrayList<String> texto) {
		this.texto = texto;
	}
	
	
	
	public boolean addTexto(String texto) {		
			this.texto.add(texto);		
			return true;	
		
	}
	
	
	public boolean addTexto(String texto, int position) {
		boolean ok = true;
		
		try{
			this.texto.remove(position);
			this.texto.add(position, texto);		
		} catch(IndexOutOfBoundsException ioe){
			ok = false;
		}
		
		return ok;
	}
	
	

	public int getSize(){
		
		return texto.size();
	}
	
*/
}
