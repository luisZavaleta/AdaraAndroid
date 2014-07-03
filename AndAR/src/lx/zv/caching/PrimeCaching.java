package lx.zv.caching;

import android.util.Log;



public abstract class PrimeCaching<T extends Object> extends BaseCaching<T>{
	
	public PrimeCaching() {		
	
	}

	public abstract void preLoadElements() throws Exception;

}
