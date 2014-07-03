package lx.zv.caching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;



public class CacheKeys {
	


	//no getters or setters too improve performance
	public HashSet<Object> keySet = new HashSet<Object>();

	/*
	
	 public void setKey(Object key){		 
		keySet.add(key);		 
	 } 
	

	public boolean containsKey(Object key){	
		return keySet.contains(key);		
	}
	
	
	public void releaseKey(Object key){
		keySet.remove(key);
	}
	*/
	
}
