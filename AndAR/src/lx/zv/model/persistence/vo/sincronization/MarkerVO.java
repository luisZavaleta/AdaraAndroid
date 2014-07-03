package lx.zv.model.persistence.vo.sincronization;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"class"})
public class MarkerVO {
	int id;
	String name;
	byte[] pattern;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getPattern() {
		return pattern;
	}
	public void setPattern(byte[] pattern) {
		this.pattern = pattern;
	}
	
	
}
