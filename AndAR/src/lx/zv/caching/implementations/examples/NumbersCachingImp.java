package lx.zv.caching.implementations.examples;

import lx.zv.caching.PrimeCaching;
import lx.zv.caching.annotations.Disposal;
import lx.zv.caching.disposal.DisposalEnum;
import lx.zv.exceptions.JaguARException;

public  class NumbersCachingImp extends PrimeCaching<Integer>{
	

	
	public NumbersCachingImp() {	
		config(this);
	}

	
	
	
	
	@Override
	public void preLoadElements() throws Exception {
		this.getObject(7);
		this.getObject(8);
		this.getObject(9);		
	}

	@Override
	protected Object createObject(Integer object) throws JaguARException {		
		return object;
	}

	@Override
	protected Object generatesKey(Integer object) throws JaguARException {
		return object;
	}

	
	@Disposal(disposalEnum = DisposalEnum.LAST_RECENTLY_USED, maxObjects = 5)
	public void config(NumbersCachingImp nci){		
	}
	



}
