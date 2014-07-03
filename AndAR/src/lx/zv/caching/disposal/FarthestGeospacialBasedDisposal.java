package lx.zv.caching.disposal;

import java.util.ArrayList;
import java.util.Collections;

import lx.zv.caching.disposal.beans.FarthestGeospacialBasedVO;

import lx.zv.exceptions.JaguARException;
import lx.zv.jaguar.business.sensors.GeospatialManager;


public class FarthestGeospacialBasedDisposal extends GeospatialDisposal{


	private ArrayList<FarthestGeospacialBasedVO> keys = new ArrayList<FarthestGeospacialBasedVO>();	
	
	
	FarthestGeospacialBasedVO fgbTemp = new FarthestGeospacialBasedVO();
	

	public FarthestGeospacialBasedDisposal() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void disposeObjectBasedInMaxMeters() {
		
	
	 @SuppressWarnings("unchecked")
	ArrayList<FarthestGeospacialBasedVO>  geoSM =	 
		(ArrayList<FarthestGeospacialBasedVO>) GeospatialManager.getLocationWithDistancesLessThan(keys, this.maximunMeters);
	 this.deleteObjects(geoSM);
		
		
	}

	@Override
	protected void disposeObjects() {
	ArrayList<FarthestGeospacialBasedVO> fgbal = getToBeDisposedKeys();
		this.deleteObjects(fgbal);
	}
	
	
	public void deleteObjects(ArrayList<FarthestGeospacialBasedVO> fgbal){
for(FarthestGeospacialBasedVO fgb : fgbal){
			
			this.oc.cachingObjects.remove(fgb.key);
			this.ckl.keySet.remove(fgb.key);
			this.keys.remove(fgb.key);
	
		}
		
	}

	@Override
	/*
	 * @param args latitude in the first position and longitude in the second
	 * */
	protected void setKeyStatistic(Object key, Object[] args) throws JaguARException {
		
		
		
		fgbTemp.key = key;
		
		
		
		if(!keys.contains(fgbTemp)){
			
			if(args.length != 2 ||  !(args[0] instanceof Double) || !(args[1] instanceof Double)){
				throw new JaguARException("latitude and longitud must be in the args and must be Double");
			}
			
			double latitude= (Double) args[0];
			double longitude= (Double) args[1];
			
			FarthestGeospacialBasedVO fgb = keys.get(keys.indexOf(fgbTemp));
			
			fgb.key = key;
			fgb.latitud = latitude;
			fgb.longitud = longitude;
	
			keys.add(fgb);
		}
		
		
	}



	@Override
	protected ArrayList<FarthestGeospacialBasedVO> getToBeDisposedKeys() {
		ArrayList<FarthestGeospacialBasedVO> toBeDeletedKeys = new ArrayList<FarthestGeospacialBasedVO>();
		
		Collections.sort(keys);
		int i =0;
		for(FarthestGeospacialBasedVO fgsb : keys){			
			toBeDeletedKeys.add(fgsb);		
			if(i >= this.maxObjects){
				break;
			}
		}
		
		return toBeDeletedKeys;
	}




	@Override
	public void deleteKeyStatistic(Object key) {
		
		fgbTemp.key = key;
		keys.remove(key);

	}





}
