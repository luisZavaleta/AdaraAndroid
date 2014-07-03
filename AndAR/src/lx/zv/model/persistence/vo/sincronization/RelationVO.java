package lx.zv.model.persistence.vo.sincronization;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties({"class"})
public class RelationVO {

	int id;
	Object3DVO object3D;
	MenuVO menu;
	MarkerVO marker;
	LocalizationVO localization;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Object3DVO getObject3D() {
		return object3D;
	}
	public void setObject3D(Object3DVO object3d) {
		object3D = object3d;
	}
	public MenuVO getMenu() {
		return menu;
	}
	public void setMenu(MenuVO menu) {
		this.menu = menu;
	}
	public MarkerVO getMarker() {
		return marker;
	}
	public void setMarker(MarkerVO marker) {
		this.marker = marker;
	}
	public LocalizationVO getLocalization() {
		return localization;
	}
	public void setLocalization(LocalizationVO localization) {
		this.localization = localization;
	}
	
}
