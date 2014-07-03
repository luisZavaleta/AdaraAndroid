package lx.zv.exceptions;

public class JaguARRuntimeException extends RuntimeException{
	
	private static final long serialVersionUID = 6578454509699935897L;
	
	

	public JaguARRuntimeException(String msg){
		super(msg);
		
	}

	public JaguARRuntimeException(){
		super("JagARRuntimeException");		
	}
	
	
	

}
