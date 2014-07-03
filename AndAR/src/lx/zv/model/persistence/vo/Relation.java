package lx.zv.model.persistence.vo;

public class Relation {
	

	public Object3D object3D;
	public Menu menu;
	public Marker marker;
	public Localization localization;
	
	
	public Object3D getObject3D() {
		return object3D;
	}
	public void setObject3D(Object3D object3d) {
		object3D = object3d;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Marker getMarker() {
		return marker;
	}
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	public Localization getLocalization() {
		return localization;
	}
	public void setLocalization(Localization localization) {
		this.localization = localization;
	}
}
