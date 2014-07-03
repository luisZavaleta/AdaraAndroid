package lx.zv.model.persistence.vo;



public class RelationsOld {

	private int id;
	private int version;
	private int idUsuario;
	private int id3DObject;
	private int idMarker;
	private int idMenu;
	
	//markerName is temporal
	private String markerName;
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getId3DObject() {
		return id3DObject;
	}
	public void setId3DObject(int id3dObject) {
		id3DObject = id3dObject;
	}
	public void setIdMarker(int idMarker) {
		this.idMarker = idMarker;
	}
	public int getIdMarker() {
		return idMarker;
	}
	public void setMarkerName(String markerName) {
		this.markerName = markerName;
	}
	public String getMarkerName() {
		return markerName;
	}
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}
	public int getIdMenu() {
		return idMenu;
	}

	

	
	
	
	
	
	
	
}
