package lx.zv.model.persistence.vo;

public class Menu {

	int id;
	int parentMenu;
	int order;	
	String text;
	MenuChild childs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(int parentMenu) {
		this.parentMenu = parentMenu;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public MenuChild getChilds() {
		return childs;
	}
	public void setChilds(MenuChild childs) {
		this.childs = childs;
	}
	
	
	
	
}
