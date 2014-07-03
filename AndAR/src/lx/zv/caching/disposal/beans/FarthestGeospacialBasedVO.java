package lx.zv.caching.disposal.beans;

import lx.zv.jaguar.business.sensors.GeospatialManager;
import lx.zv.model.persistence.bo.Localizable;
import android.location.Location;

public class FarthestGeospacialBasedVO  implements Comparable<FarthestGeospacialBasedVO>,Localizable, DisposalVO{
	
	public Object key;
	public double latitud = Double.NaN;
	public double longitud = Double.NaN;
	public double altitud = Double.NaN;	
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
	public Location getLocation() {
	if(loc == null){
		
		if(longitud != Double.NaN && altitud != Double.NaN){
			loc = new Location("");
			
			loc.setLongitude(longitud);
			loc.setLatitude(latitud);			
			if(altitud != Double.NaN){
				loc.setAltitude(altitud);
			}
			
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FarthestGeospacialBasedVO other = (FarthestGeospacialBasedVO) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public int compareTo(FarthestGeospacialBasedVO another) {
		return (int)GeospatialManager.compareTo(getLocation(), another.getLocation());
		
	}


	
}