package lx.zv.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JaguARFileUtil {
	
		public final static int OBJ = 0;
		public final static int MTL = 1;
		public final static int JPG= 2;
		public final static int PNG= 3;
	
	
	
		public String writeFile(byte[] file, String extension, String uri){
			
			try {
				String basePath = "/sdcard/Jaguar"+uri;
				
			  String path = basePath+extension; 
		        FileOutputStream stream = new FileOutputStream(path); 
		        stream.write(file);
		        
		        stream.flush();
		        stream.close();
			
			return basePath;
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return null;
			
		}
		
		
	
	public String writeFile(byte[] file, int TYPE, String uri){
		
		
			   
			String ext = ".obj";
			switch(TYPE){
			case 0:
				break;
			case 1: ext = ".mtl";
				break;
			case 2: ext = ".jpg";
				break;
			case 3: ext = ".png";
				break;			
			}
			
			return this.writeFile(file, ext, uri);
				
	}

}
