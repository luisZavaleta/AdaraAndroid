package lx.zv.model.persistence.vo.sincronization;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"class"})
public class LocalizationVO {
	int id;
	Long altitude;
	Long latitude;
	Long longitude;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getAltitude() {
		return altitude;
	}
	public void setAltitude(Long altitude) {
		this.altitude = altitude;
	}
	public Long getLatitude() {
		return latitude;
	}
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	public Long getLongitude() {
		return longitude;
	}
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	
	
	

}
