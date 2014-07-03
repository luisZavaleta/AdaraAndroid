package lx.zv.model.persistence.vo.sincronization;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties({"class"})
public class DataVO {
	List<RelationVO> relation;

	public List<RelationVO> getRelation() {
		return relation;
	}

	public void setRelation(List<RelationVO> relation) {
		this.relation = relation;
	}
	
	
}
