package lx.zv.model.persistence.vo.sincronization;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties({"class"})
public class MenuVO {
	long id;
	long parentMenu;
	int order;
	String text;
	List<ChildMenuVO> childs;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(long parentMenu) {
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
	public List<ChildMenuVO> getChilds() {
		return childs;
	}
	public void setChilds(List<ChildMenuVO> childs) {
		this.childs = childs;
	}
	
	
	
}
