package lx.zv.caching;

import java.util.HashMap;

import lx.zv.exceptions.JaguARException;

public class CacheObjects {
	
	//no getters or setters too improve performance
	public HashMap<Object,Object> cachingObjects = new HashMap<Object,Object>();
	
	/*
	public Object getObject(Object key){		
		return cachingObjects.get(key);
	}
	
	
	public void setObject(Object key, Object value){
		
		Object obj = cachingObjects.put(key, value);
		//obj allways must be null 'cause we previously check that the key doesn exist
		assert obj != null; 
	}

	
	public void releaseObject(Object key){
		cachingObjects.remove(key);
	}
	
	
	public int getSize(){
		return cachingObjects.size();
	}
	*/
	
	
}
