package lx.zv.model.persistence.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lx.zv.jaguar.business.opengl.menu.MenuText;
import lx.zv.model.persistence.vo.MenuTextVO;
import lx.zv.pruebasBorrar.State;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MenuTextFromServerDAO {
	
	
	String urlChange = "menutexts";
	String ip = "10.48.184.138:8080";
	
	
	final String url = "http://"+ip+"/server/"+urlChange+"/send/all";
	
	
	
	
	
	
	public List<MenuTextVO> getAllMenueText(){
	
		
		
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
		ResponseEntity<MenuTextVO[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, MenuTextVO[].class);
		
		List<MenuTextVO> mtList = Arrays.asList(responseEntity.getBody());
		
		
		return mtList;
		
		
	}
	
	
	

	
	
	
	
}
