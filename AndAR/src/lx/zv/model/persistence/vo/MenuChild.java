package lx.zv.model.persistence.vo;

public class MenuChild {
	Menu menu;
	MenuChild childs;
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public MenuChild getChilds() {
		return childs;
	}
	public void setChilds(MenuChild childs) {
		this.childs = childs;
	}
 
}
