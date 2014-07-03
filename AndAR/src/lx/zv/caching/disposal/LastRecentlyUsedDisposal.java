package lx.zv.caching.disposal;

import java.util.ArrayList;
import java.util.Collections;

import lx.zv.caching.CacheKeys;
import lx.zv.caching.CacheObjects;
import lx.zv.caching.disposal.beans.DisposalVO;
import lx.zv.caching.disposal.beans.LastFrequentlyUsedVO;
import lx.zv.caching.disposal.beans.LastRecentlyUsedVO;


public class LastRecentlyUsedDisposal extends BaseDisposal{

	

	//ArrayList<Object> keys = new ArrayList<Object>();
	
	ArrayList<LastRecentlyUsedVO> keys = new ArrayList<LastRecentlyUsedVO>();
	

	//for storing a temporal key with only the key object for comparing 
	//(to avoid the creation of one object each time we use the setStatistics method)
	private LastRecentlyUsedVO lruTemp = new LastRecentlyUsedVO();

	

	public LastRecentlyUsedDisposal() {
		super();
	}

	
	

	@Override
	protected void disposeObjects() {
		ArrayList<LastRecentlyUsedVO> disposedKeys = this.getToBeDisposedKeys();
		
		
		for(LastRecentlyUsedVO lru : disposedKeys){
			this.oc.cachingObjects.remove(lru.key);
			this.ckl.keySet.remove(lru.key);
			this.keys.remove(lru.key);
	
		}
		
	}
	

	
	@Override
	public void setKeyStatistic(Object key, Object[] args){	
	lruTemp.key = key;
		
		if(keys.contains(lruTemp)){	

			LastRecentlyUsedVO lru = keys.get(keys.indexOf(key));			
			lru.date  = System.currentTimeMillis();
			
		}else{
			
			LastRecentlyUsedVO  lru = new LastRecentlyUsedVO ();
			lru.key =key;
			lru.date  = System.currentTimeMillis();
			keys.add(lru);
		
		}	
	}
	


	@Override
	protected ArrayList<LastRecentlyUsedVO> getToBeDisposedKeys() {
		ArrayList<LastRecentlyUsedVO> toBeDeletedKeys = new ArrayList<LastRecentlyUsedVO>();
		
		
	Collections.sort(keys);
		
		
		int i =0;
		for(LastRecentlyUsedVO lru : keys){
			
			toBeDeletedKeys.add(lru);
			
		
			if(i >= this.maxObjects){
				break;
			}
		}
		
		
	
	return toBeDeletedKeys;
		

	}



	@Override
	public void deleteKeyStatistic(Object key) {
		keys.remove(key);
		
	}



		
	

}
