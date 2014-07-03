package lx.zv.model.persistence.vo.sincronization;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"class"})
public class RestResponseVO {
	
	boolean success = true;
	DataVO data;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public DataVO getData() {
		return data;
	}
	public void setData(DataVO data) {
		this.data = data;
	}
	
	 
}
