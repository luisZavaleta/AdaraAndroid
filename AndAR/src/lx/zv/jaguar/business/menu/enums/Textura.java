package lx.zv.jaguar.business.menu.enums;

public enum Textura{
	HORIZONTAL1(0),
	HORIZONTAL2(1),
	HORIZONTAL4(2),
	HORIZONTAL6(3),
	VERTICAL1(4),
	VERTICAL2(5),
	VERTICAL4(6),
	VERTICAL6(7);
	
	private final int pos;
	Textura(int pos){
	this.pos = pos;
	}
	
	public int getPosition(){
		return pos;
	}
}