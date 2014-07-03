package lx.zv.model.persistence.vo.sincronization;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"class"})
public class Object3DVO {

	int id;
	byte[] obj;
	byte[] img;
	byte[] mtl;
	String imageExtension;
	String baseURL;
	String baseFileName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getObj() {
		return obj;
	}
	public void setObj(byte[] obj) {
		this.obj = obj;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public byte[] getMtl() {
		return mtl;
	}
	public void setMtl(byte[] mtl) {
		this.mtl = mtl;
	}
	public String getImageExtension() {
		return imageExtension;
	}
	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}
	public String getBaseURL() {
		return baseURL;
	}
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	public String getBaseFileName() {
		return baseFileName;
	}
	public void setBaseFileName(String baseFileName) {
		this.baseFileName = baseFileName;
	}
	
	

}
