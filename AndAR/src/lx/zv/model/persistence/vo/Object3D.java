package lx.zv.model.persistence.vo;

public class Object3D {
	
	public int id;
	public String baseFileName;
	public String imageExtension;
	public String baseURL;
	
	/*
	public byte[] img;
	public byte[] obj;
	public byte[] mtl;
	 */	
	
	public String img;
	public String obj;
	public String mtl;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBaseFileName() {
		return baseFileName;
	}
	public void setBaseFileName(String baseFileName) {
		this.baseFileName = baseFileName;
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
/*
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public byte[] getObj() {
		return obj;
	}
	public void setObj(byte[] obj) {
		this.obj = obj;
	}
	public byte[] getMtl() {
		return mtl;
	}
	public void setMtl(byte[] mtl) {
		this.mtl = mtl;
	}
*/
	
}
