package lx.zv.aspects.caching;

import android.util.Log;
import lx.zv.caching.BCaching;
import lx.zv.caching.BaseCaching;
import lx.zv.caching.PrimeCaching;
import lx.zv.caching.annotations.Disposal;
import lx.zv.caching.disposal.DisposalFactory;
import lx.zv.caching.disposal.GeospatialDisposal;
import lx.zv.caching.helper.AsyncPreload;

public aspect ConfigureDisposal {
	
	//call( @MyAnnotation * *.new(..) )
	

	private PrimeCaching<?> primeCaching = null;
	
	/*
	public pointcut selectDisposal(Disposal disposal, BCaching baseCaching) : call (@Disposal lx.zv.caching.implementations..*CachingImp.new()) 
		&& @annotation(disposal) && this(baseCaching);
		*/
	
	public pointcut selectDisposal(Disposal disposal, BaseCaching<?> baseCaching) : call (@Disposal * lx.zv.caching.implementations..*CachingImp.config(BaseCaching+)) 
		&& @annotation(disposal) && args(baseCaching);
	
	public pointcut asyncTask() : call(protected Void lx.zv.caching.helper.AsyncPreload.doInBackground(Void...));

	
	/*
	after(Disposal disposal, BaseCaching<?> baseCaching) : selectDisposal(disposal,baseCaching){
		
	}
	*/
	
	
	after(Disposal disposal, BaseCaching<?> baseCaching) : selectDisposal(disposal,baseCaching){
		Log.e("AspectJ", "aspectJ XD");	
	
		baseCaching.baseDisposal = DisposalFactory.getDisposal(disposal.disposalEnum());
		baseCaching.baseDisposal.maxObjects = disposal.maxObjects();
		
		if(baseCaching.baseDisposal instanceof GeospatialDisposal){		
		((GeospatialDisposal) baseCaching.baseDisposal).maximunMeters = disposal.maxMeters();				
		}
		
		if(baseCaching instanceof PrimeCaching){
			
		PrimeCaching<?> primeCaching = (PrimeCaching<?>)baseCaching;
		this.primeCaching = primeCaching;	
			
		if(disposal.preloadAsync()){
			new AsyncPreload().execute();
		}else{
			try {
				primeCaching.preLoadElements();
			} catch (Exception e) {
				Log.e("AspectJ", "no se pudieron precargar los elementos");
				e.printStackTrace();
			}			
		}
			
		}
		}
		
	
	
	
	before() : asyncTask(){		
		try {
			primeCaching.preLoadElements();
		} catch (Exception e) {
		Log.e("AspectJ", "no se pudieron precargar los elementoa");
			e.printStackTrace();
		}
	}
/*
	pointcut selectDisposal(Disposal disposal, DisposalEnum disposalEnum) : call (@Disposal *  *caching())
	&& @annotation(disposal) && arg(s);
	
	*/
	
	
}
