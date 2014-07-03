package lx.zv.model.persistence.vo;

public class MenuTextVO {
	
	
	Integer id;
	Integer version;
	Integer idMenueText;
	Integer idParent;
	String texto;
	Integer orden;
	double latitud;
	double longitud;
	double altitud;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getIdMenueText() {
		return idMenueText;
	}
	public void setIdMenueText(Integer idMenueText) {
		this.idMenueText = idMenueText;
	}
	public Integer getIdParent() {
		return idParent;
	}
	public void setIdParent(Integer idParent) {
		this.idParent = idParent;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getAltitud() {
		return altitud;
	}
	public void setAltitud(double altitud) {
		this.altitud = altitud;
	}
	
	
	
	
}
