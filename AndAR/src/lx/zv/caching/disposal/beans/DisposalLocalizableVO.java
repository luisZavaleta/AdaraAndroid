package lx.zv.caching.disposal.beans;

import android.location.Location;
import lx.zv.exceptions.JaguARException;
import lx.zv.jaguar.business.sensors.helper.LocationHelper;
import lx.zv.model.persistence.bo.Localizable;

public class DisposalLocalizableVO implements Localizable, DisposalVO{
	
	public double latitud = Double.NaN;
	public double longitud = Double.NaN;
	public double altitud = Double.NaN;
	public Object key;
	private Location loc = null;

	@Override
	public Object getKey() {
		return key;
	}

	@Override
	public void setKey(Object key) {
	this.key = key;
		
	}

	@Override
	public double getLatitud() {
			return latitud;
	}

	@Override
	public void setLatitud(double latitud) {
		this.latitud = latitud;
		
	}

	@Override
	public double getLongitud() {
		return longitud;
	}

	@Override
	public void setLongitud(double longitud) {
		this.longitud = longitud;
		
	}

	@Override
	public double getAltitud() {
		return altitud;
	}

	@Override
	public void setAltitud(double altitud) {
		this.altitud = altitud;
	}

	@Override
	public Location getLocation(){
	if(loc == null){
		try{
			return LocationHelper.getLocation(latitud, longitud);		
		}catch(JaguARException je){
			return null;
		}
		
		
	}
	
		return loc;
	}

	@Override
	public void setLocation(Location loc) {		
		altitud = loc.getAltitude();
		longitud = loc.getLongitude();
		altitud = loc.getAltitude();
	}


}
