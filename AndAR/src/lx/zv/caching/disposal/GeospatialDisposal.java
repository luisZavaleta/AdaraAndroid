package lx.zv.caching.disposal;


import java.util.Observable;
import java.util.Observer;

import lx.zv.caching.CacheKeys;
import lx.zv.caching.CacheObjects;
import lx.zv.jaguar.business.sensors.GeospatialManager;

public abstract class GeospatialDisposal extends BaseDisposal implements Observer{
	

	public long maximunMeters = Long.MAX_VALUE;
	boolean maxMetersDisposal = false;
	
	GeospatialManager geom = GeospatialManager.getInstance();



	
	
	public GeospatialDisposal() {
		super();
		GeospatialManager.getInstance().addObserver(this);
	}

	public abstract void disposeObjectBasedInMaxMeters();

	@Override
	public void update(Observable observable, Object data) {	
		
		if(maxMetersDisposal)
		{
			disposeObjectBasedInMaxMeters();
		}		
	}



	

}
