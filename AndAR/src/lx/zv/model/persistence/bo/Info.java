package lx.zv.model.persistence.bo;

import android.location.Location;


public class Info implements Localizable{
	
	
	public int id;
	public int idParent;
	public int idMarcador;
	public String data;
	public int order;
	
	public double latitud = Double.MIN_VALUE;
	public double longitud = Double.MIN_VALUE;
	public double altitud = Double.MIN_VALUE;
	
	private Location loc;
	
	

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
		this.latitud = longitud;
		
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
	public void setLocation( Location loc) {
		
		
		this.altitud = loc.getAltitude();
		this.longitud = loc.getLongitude();
		this.latitud = loc.getLatitude();
		
	}





}
