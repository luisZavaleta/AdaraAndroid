package lx.zv.jaguar.business.sensors.helper;

import lx.zv.exceptions.JaguARException;
import android.location.Location;

public class LocationHelper {

	public static Location getLocation(double latitud,double longitud) throws JaguARException{

		Location location;

		if(longitud != Double.NaN && longitud != Double.NaN){
			location = new Location("");

			location.setLongitude(longitud);
			location.setLatitude(latitud);			

			return location;
		}

		throw  new JaguARException ("The location couldn't be retrived due to error in the parameters");
	}

	public static Location getLocation(double latitud,double longitud,double altitude) throws JaguARException{

		if(altitude != Double.NaN){	
			Location location = LocationHelper.getLocation(latitud, longitud);
			location.setAltitude(altitude);			
			return location;				
		}
		throw  new JaguARException ("The location couldn't be retrived due to error in the parameters");
	}

}
