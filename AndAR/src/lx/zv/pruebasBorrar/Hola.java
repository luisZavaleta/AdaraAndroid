package lx.zv.pruebasBorrar;

//import org.springframework.http.client.CommonsClientHttpRequestFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

public class Hola {
	
	Activity thizz;
	
	public Hola(Activity thizz) {
		this.thizz = thizz;
	}
	
	//private static final String BASE_URL = null;

	public Documents getPlayedMatches() {
		
		
	
			// The URL for making the GET request
		final String url = "http://10.48.169.75:8080/example/hola/otro.json";
			
			// Set the Accept header for "application/json"
			HttpHeaders requestHeaders = new HttpHeaders();
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(acceptableMediaTypes);
			
			// Populate the headers in an HttpEntity object to use for the request
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
			
			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			
			// Perform the HTTP GET request
			ResponseEntity<State[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, State[].class);
	
			List<State> stList = Arrays.asList(responseEntity.getBody());
			
			
			
			
			//FileInputStream fis;
			try {
			   
				  String path = "/sdcard/AndArHolaMundo.txt"; 
			        FileOutputStream stream = new FileOutputStream(path); 
			        stream.write(stList.get(0).getContent());
			        State st;
			        stream.flush();
			        stream.close();
				
				
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
			
				e.printStackTrace();
			}	
			
			
		/*	File file = new File("hola");
			try {
				AssetManager am = thizz.getAssets();
				InputStream is = am.open("filename");
				
				InputStream in = new FileInputStream(file);
				byte[] bytes = new byte[(int) file.length()];
				in.read(bytes);
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			/*
			 * File file = new File("afile");
InputStream in = new FileInputStream(file);
byte[] bytes = new byte[file.length()];
in.read(bytes);
in.close();
			 * */
			/*
			
			Document doc = Document.findDocument(id);
	    	

	        try {
	            response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getFilename()+ "\"");

	            OutputStream out = response.getOutputStream();
	            response.setContentType(doc.getContentType());

	           IOUtils.copy( new ByteArrayInputStream(doc.getContent()) , out);
	            out.flush();
	         
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
			*/
			
			
			
			
			
			
			return null;	
	}

}
