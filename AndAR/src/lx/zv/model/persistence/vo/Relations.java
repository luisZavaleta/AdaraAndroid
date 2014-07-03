package lx.zv.model.persistence.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"class"})
public class Relations {

	List relation;

	public List getRelation() {
		return relation;
	}

	public void setRelation(List relation) {
		this.relation = relation;
	}
	
}
