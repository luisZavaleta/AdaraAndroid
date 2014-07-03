package lx.zv.jaguar.business.menu.util;

import java.util.ArrayList;
import java.util.HashMap;

import lx.zv.model.menu.bo.JagArMenuListVO;
import lx.zv.model.menu.bo.JagArMenuVO;
import lx.zv.model.persistence.bo.texture.MenuMetrics;
import lx.zv.model.persistence.bo.texture.TextCoordinates;



//modificar agregar caching
public class MenuMetricsPool {
	
	private static HashMap<JagArMenuListVO,MenuMetrics> hm = new HashMap<JagArMenuListVO, MenuMetrics>();
	
	
	public void setMenuMetric(JagArMenuListVO jagarmvo,MenuMetrics mm){		
		MenuMetricsPool.hm.put(jagarmvo, mm);
	}
	
	
	public MenuMetrics getMenuMetrics(JagArMenuListVO jagArMenuListVo){
		
		return hm.get(jagArMenuListVo);
	}
	
	public void clearMenuMetricsPool(){		
		hm.clear();
	}
	
	
	public boolean exists(JagArMenuListVO jagArMenuListVo){
		
		return hm.containsKey(jagArMenuListVo);
		
	}
	

}
