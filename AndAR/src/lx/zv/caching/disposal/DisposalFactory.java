package lx.zv.caching.disposal;

import lx.zv.caching.CacheKeys;
import lx.zv.caching.CacheObjects;

public class DisposalFactory {
	
	
public static BaseDisposal	getDisposal(DisposalEnum de){
	
	
	switch(de){

		case LAST_FREQUENTLY_USED: return new LastFrequentlyUsedDisposal();
		
		case LAST_RECENTLY_USED: return new LastRecentlyUsedDisposal();
		
		case FARTHEST_GEOSPATIALLY_BASED: return new FarthestGeospacialBasedDisposal();
		
		default: return null;
	
	}
	

	
}
	
	

}
