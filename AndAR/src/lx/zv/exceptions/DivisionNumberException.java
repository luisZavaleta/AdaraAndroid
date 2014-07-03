package lx.zv.exceptions;

public class DivisionNumberException  extends Exception{

	private static final long serialVersionUID = -7341063284616174346L;
	
	public DivisionNumberException(String msg){
		super(msg);
		
	}

	public DivisionNumberException(){
		super("Division number must be between 1 and 6");		
	}
	
}
