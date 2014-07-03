package lx.zv.model.persistence.bo;

import java.util.Locale;

import android.location.Location;

public interface Localizable {

	public double getLatitud();
	public void setLatitud(double latitud);
	public double getLongitud();
	public void setLongitud(double longitud);
	public double getAltitud();
	public void setAltitud(double altitud);
	
	public Location getLocation();	
	public void setLocation(Location loc);
	
	
	
	

}
