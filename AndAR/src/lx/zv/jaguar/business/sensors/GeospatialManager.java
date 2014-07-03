package lx.zv.jaguar.business.sensors;

import java.util.ArrayList;
import java.util.Observable;

import lx.zv.model.persistence.bo.Localizable;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public class GeospatialManager extends Observable implements  LocationListener{


static GeospatialManager geom = null;




public static GeospatialManager getInstance(){
	
	if(geom == null){
		geom = new GeospatialManager();
	}
	
	return geom;
}

	
	private GeospatialManager(){}
	
	static Location  actualLocation = null;
	

	
	
	
	public static Location getFartherLocation(ArrayList<Location> locations){		
		
		Location farther = null;
		int distance = Integer.MAX_VALUE;
		
		for(Location loc: locations){
			
			if(actualLocation == null){
				return null;
			}else{
				int locsDistance = Math.round(actualLocation.distanceTo(loc));
			if(locsDistance < distance){
				distance = locsDistance;
				farther = loc;
			} 
			}
			
		}
		
		return farther;
		
	}
	
	
	
	public static ArrayList<? extends Localizable> getLocationWithDistancesLessThan( ArrayList<? extends Localizable> locObjects,long distanceInMeters){
		
		ArrayList<Localizable> ret = new ArrayList<Localizable>();
	
		
		for(Localizable lo: locObjects){

			if(Math.round(actualLocation.distanceTo(lo.getLocation())) < distanceInMeters){
				ret.add(lo);
			}	
		}
		
		return ret;
		
	}
	

	/*
	 * @returs returns a positive number if the first location is farther, 
	 * 0 if the distances are equal, a negative number if the second location is farther;
	 * */
	
	public static long compareTo(Location loc1, Location loc2){		
		return (long)(actualLocation.distanceTo(loc1) - actualLocation.distanceTo(loc2));
	}
	
	
	
	@Override
	public void onLocationChanged(Location location) {
	
	/*	
		//Temporal for testing
		ArrayList<Location> locations = new ArrayList<Location>();

		//Azteca stadium XD
	double latitudDummie = 19.303711292445566;
	double longitudeDummie = -99.15467977523804;
		
		for(int i=0; i<10;i++){
			Location loc = new Location("");
			
			loc.setLatitude(latitudDummie);
			loc.setLongitude(longitudeDummie);
			
			latitudDummie = latitudDummie + .1;
			longitudeDummie = longitudeDummie + .1;
			
			locations.add(loc);
		}
		*/
	
		
		actualLocation = location;
		
		/*
		
		Location farfaraway = this.getFartherLocation(locations);
		
		ArrayList<Location> wawa = this.getLocationWithDistancesLessThan(locations, 90000);
		// TODO Auto-generated method stub
		*/
		
		this.setChanged();
		this.notifyObservers();
		
	}



	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
