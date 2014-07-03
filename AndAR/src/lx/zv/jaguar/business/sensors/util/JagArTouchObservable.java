package lx.zv.jaguar.business.sensors.util;

import java.util.Observable;

public class JagArTouchObservable extends Observable{

	
	public void changed(int x, int y){
		
	
		int[] coord = new int[2];
		coord[0] = x;
		coord[1] = y;
		
		    
		    
		this.setChanged();
		this.notifyObservers(coord);
		
	}
	
	
}
