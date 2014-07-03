package lx.zv.model.persistence.vo;

public class Object3DOld {
	

	
		private int id;
		private int version;
	
	  	private Integer idObject3D;	
	    private byte[] obj;	 
	    private byte[] mtl;	   
	    private byte[] image;	 
	    private String extension;
	    
	    private String baseUrl;
	    private String baseFileName;
	    
	    
	    /*private String urlObj;
	    private String urlMtl;
	    private String urlImage;
	    */
	    /*
	    public String getUrlObj() {
			return urlObj;
		}
		public void setUrlObj(String urlObj) {
			this.urlObj = urlObj;
		}
		public String getUrlMtl() {
			return urlMtl;
		}
		public void setUrlMtl(String urlMtl) {
			this.urlMtl = urlMtl;
		}
		public String getUrlImage() {
			return urlImage;
		}
		public void setUrlImage(String urlImage) {
			this.urlImage = urlImage;
		}
		*/
	    
	    
	    public Integer getIdObject3D() {
			return idObject3D;
		}
		public void setIdObject3D(Integer idObject3D) {
			this.idObject3D = idObject3D;
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
		public byte[] getImage() {
			return image;
		}
		public void setImage(byte[] image) {
			this.image = image;
		}
		public String getExtension() {
			return extension;
		}
		public void setExtension(String extension) {
			this.extension = extension;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getId() {
			return id;
		}
		public void setVersion(int version) {
			this.version = version;
		}
		public int getVersion() {
			return version;
		}
		public void setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
		}
		public String getBaseUrl() {
			return baseUrl;
		}
		public void setBaseFileName(String baseFileName) {
			this.baseFileName = baseFileName;
		}
		public String getBaseFileName() {
			return baseFileName;
		}
		
}
