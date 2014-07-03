package lx.zv.pruebasBorrar;

import java.util.ArrayList;

public class Document {

/*{"name":"x",
 * "id":1,
 * "content":"
 * 
 * /9j/4AAQSkZJRgABAQEAYABgAAD//gATQ3JlYXRlZCB3aXRoIEdJTVD/2wBDAAEBAQEBAQEBAQ
*/
	/*
	
	{"name":"x",
		"id":1,
		"content":null,
		"contentType":"image/jpeg",
		"url":"http://localhost:8080/example/documents/showdoc/1",
		"description":"",
		"version":0,
		"filename":"fichaTecLuis.jpg",
		"tamano":172022}*/
	
	
		private String name;
		private Integer id;
	    private String content;
	    private String contentType;
	    private String url;
	    private String description;
	    private Integer version;
	    private String filename;
	    private Integer tamano;
	    
		
	    public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
		public String getContentType() {
			return contentType;
		}
		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Integer getVersion() {
			return version;
		}
		public void setVersion(Integer version) {
			this.version = version;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public Integer getTamano() {
			return tamano;
		}
		public void setTamano(Integer tamano) {
			this.tamano = tamano;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getId() {
			return id;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getContent() {
			return content;
		}
	
	    
	 
	  
	   

}