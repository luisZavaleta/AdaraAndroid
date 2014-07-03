package lx.zv.exceptions;

import edu.dhbw.andar.exceptions.AndARException;

public class JaguARException extends AndARException{

	private static final long serialVersionUID = -7341063284616174346L;
	
	
	public JaguARException(String msg){
		super(msg);
		
	}

	public JaguARException(){
		super("JagARException");		
	}
	
	
	
	
}