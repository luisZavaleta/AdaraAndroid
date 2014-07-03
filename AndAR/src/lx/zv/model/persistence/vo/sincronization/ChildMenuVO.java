package lx.zv.model.persistence.vo.sincronization;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties({"class"})
public class ChildMenuVO {
	MenuVO menu;
	List<ChildMenuVO> childs;
	
	
	public MenuVO getMenu() {
		return menu;
	}
	public void setMenu(MenuVO menu) {
		this.menu = menu;
	}
	public List<ChildMenuVO> getChilds() {
		return childs;
	}
	public void setChilds(List<ChildMenuVO> childs) {
		this.childs = childs;
	}
	
	

}
