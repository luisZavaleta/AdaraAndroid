package lx.zv.caching;

import lx.zv.caching.disposal.BaseDisposal;
import lx.zv.exceptions.JaguARException;

/*Si entra aquó es por que noxiste, por eso verifique en */
public abstract class BaseCaching<T extends Object> extends BCaching{
	
//Quitar el public, temporal
public CacheKeys ckl = new CacheKeys();	
public CacheObjects oc = new CacheObjects();

public BaseDisposal baseDisposal = null;

public BaseCaching(){}



	



	//constructs the object that will be stored in the database based in the parameters
abstract protected Object createObject(T object) throws JaguARException;


//abstract protected boolean containsKey(Object key);
abstract protected Object generatesKey(T object) throws JaguARException;

//thows JagARException
public Object getObject(T object) throws JaguARException{
	
	Object result  = null;
	
	Object key = this.generatesKey(object);
	
	if(ckl.keySet.contains(key)){
		return oc.cachingObjects.get(key);		
	}else{
		result = this.createObject(object);	
		ckl.keySet.add(key);
		if(result != null){
			oc.cachingObjects.put(key, result);
		}
		return result;
	}

}




public void releaseObject(Object key){	
	ckl.keySet.remove(key);
	oc.cachingObjects.remove(key);
	baseDisposal.deleteKeyStatistic(key);	
}




}
