package lx.zv.caching.disposal;

import java.util.ArrayList;
import java.util.HashMap;

import lx.zv.caching.CacheKeys;
import lx.zv.caching.CacheObjects;
import lx.zv.caching.disposal.beans.DisposalVO;
import lx.zv.caching.disposal.beans.LastFrequentlyUsedVO;
import lx.zv.exceptions.JaguARException;

public abstract class BaseDisposal {
	
	public int maxObjects = Integer.MAX_VALUE;
		/*Number of objects removed each time that we execute disposeObjects method.
		the number of objects after execution the disposeObjects method
		will be equals to maximunObjects minus objectsRemoved*/
	int objectsRemoved = 1;
	
	
	//HashMap<Object,Object> objects;
	CacheKeys ckl = new CacheKeys();	
	CacheObjects oc = new CacheObjects();
	
	
	
	
	public BaseDisposal(){}
	
	//ObjectCaching oc,CacheKeyList ckl
	public void setDisposalParameters(CacheObjects oc,CacheKeys ckl){
		this.ckl = ckl;
		this.oc = oc;
	}
	
	

	

	protected abstract void disposeObjects();
	
	public abstract void deleteKeyStatistic(Object key);
	
	protected abstract ArrayList<? extends DisposalVO> getToBeDisposedKeys();
	
	/*
	 * Call when a object is accessed to maintain an statistic used for 
	 * deleting objects when the cache is full
	 * 
	 * */
	protected abstract void setKeyStatistic(Object key,Object[] args) throws JaguARException;
	
	public void verifyCapacity(){
		if(oc.cachingObjects.size() >= maxObjects){
			this.disposeObjects();
		}
	}

	
	
	
}
