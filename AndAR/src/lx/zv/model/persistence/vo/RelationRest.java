package lx.zv.model.persistence.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"class"})
public class RelationRest {
	boolean success;
	Relations data;

	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Relations getData() {
		return data;
	}
	public void setData(Relations data) {
		this.data = data;
	}
	
}
