package lx.zv.model.persistence.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lx.zv.model.persistence.vo.MenuTextVO;
import lx.zv.model.persistence.vo.RelationRest;

import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import android.util.Log;
import lx.zv.model.persistence.vo.Relation;
import lx.zv.model.persistence.vo.sincronization.RestResponseVO;

public class DataFromServer {

	//int role = 1;
	//Poner en un archivo de configuraci—n si da tiempo
	String urlServer = "http://192.168.1.112:7070/AdaraAdministratorPlugin/";
	
	String path = "rest/roleData/";
	
	
	
	public RestResponseVO getDataByIdRole(int idRole){
		
		String url =  urlServer + path + idRole;
		
		Log.i("Sync","URL--->"+url);
		
		HttpHeaders requestHeaders = new HttpHeaders();
		
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(acceptableMediaTypes);
	
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(1000000000);
		
		
		Log.i("Sync","requestFactory--->"+requestFactory);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.setRequestFactory(requestFactory);
		RestResponseVO rr =  null;
		try{
		
		ResponseEntity<RestResponseVO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, RestResponseVO.class);	
		
		Log.i("Sync","responseEntity--->"+responseEntity);
		
		
		rr =  responseEntity.getBody();		
		Log.i("Sync","responseEntity.getBody()--->"+responseEntity.getBody());
		}catch(ResourceAccessException rae){
			Log.e("Sync", rae.getMessage(),rae);
			
			return null;
		}
	
		
		 //restTemplate.exchange(url,  HttpMethod.GET, JSONArray.class);
		
		return rr;	
	}
	
	/*
	 * DataFromServer dfs = new DataFromServer();
		
		dfs.getDataByIdRole(1);
	 * */
	
	
}
