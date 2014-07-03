package lx.zv.model.persistence.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lx.zv.model.persistence.vo.Object3DOld;
import lx.zv.model.persistence.vo.RelationsOld;
import lx.zv.util.JaguARFileUtil;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Object3DFromServerDAO {
	
	
	String urlChange = "object3ds";
	String ip = "10.48.184.138:8080";
	
	
	final String url = "http://"+ip+"/server/"+urlChange+"/send/all";
	
	
public List<Object3DOld> getAll(){
	
		
		
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
		ResponseEntity<Object3DOld[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object3DOld[].class);
		
		List<Object3DOld> o3d = Arrays.asList(responseEntity.getBody());
		
		JaguARFileUtil jfu = new 	JaguARFileUtil();
		
		
		
		for(Object3DOld od: o3d){
			String time = String.valueOf(System.currentTimeMillis());
			
			String basePath = jfu.writeFile(od.getObj(), JaguARFileUtil.OBJ , time);
			
			od.setBaseUrl(basePath);
			
			jfu.writeFile(od.getMtl(), JaguARFileUtil.MTL , time);
			jfu.writeFile(od.getImage(), "."+od.getExtension() , time);
			
		
		
		
		}
		
		
		return o3d;
		
		
	}

}
