package lx.zv.caching.disposal;

import java.util.ArrayList;
import java.util.Collections;


import lx.zv.caching.CacheKeys;
import lx.zv.caching.CacheObjects;
import lx.zv.caching.disposal.beans.DisposalVO;
import lx.zv.caching.disposal.beans.LastFrequentlyUsedVO;

public class LastFrequentlyUsedDisposal extends BaseDisposal{

	//HashMap<Object, Integer> keysStatistics = new HashMap<Object,Integer> ();
	
	private ArrayList<LastFrequentlyUsedVO> keys = new ArrayList<LastFrequentlyUsedVO>();
	
	//for storing a temporal key with only the key object for comparing 
	//(to avoid the creation of one object each time we use the setStatistics method)
	private LastFrequentlyUsedVO lfuTemp = new LastFrequentlyUsedVO();

//	private HashSet<LastFrequentlyUsed> keysObj = new HashSet<LastFrequentlyUsed>();
	


	public LastFrequentlyUsedDisposal() {
		super();
	}

	@Override
	protected void disposeObjects() {
		ArrayList<LastFrequentlyUsedVO> allfu = getToBeDisposedKeys();
		
		
		for(LastFrequentlyUsedVO lfu : allfu){
			
			this.oc.cachingObjects.remove(lfu.key);
			this.ckl.keySet.remove(lfu.key);
			this.keys.remove(lfu.key);
	
		}
		
		/**/
		
	}


	
	@Override
	protected void setKeyStatistic(Object key, Object[] args) {
	
		lfuTemp.key = key;
		
		if(keys.contains(lfuTemp)){	

			LastFrequentlyUsedVO luf = keys.get(keys.indexOf(key));			
			luf.requests++;
			
			
		}else{
			
			LastFrequentlyUsedVO luf = new LastFrequentlyUsedVO();
			luf.key =key;
			luf.requests = 1;
			
			keys.add(luf);
		
		}
		
	}
	
	@Override
	protected ArrayList<LastFrequentlyUsedVO> getToBeDisposedKeys(){
		ArrayList<LastFrequentlyUsedVO> toBeDeletedKeys = new ArrayList<LastFrequentlyUsedVO>();
		
		Collections.sort(keys);
		
		
		int i =0;
		for(LastFrequentlyUsedVO lxfu : keys){
			
			toBeDeletedKeys.add(lxfu);
			
		
			if(i >= this.maxObjects){
				break;
			}
		}
		
		
	
	return toBeDeletedKeys;

}

	@Override
	public void deleteKeyStatistic(Object key) {
		lfuTemp.key = key;
		keys.remove(lfuTemp);
		
	}
	
}
	
	



