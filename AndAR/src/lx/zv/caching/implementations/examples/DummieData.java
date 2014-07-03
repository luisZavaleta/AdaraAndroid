package lx.zv.caching.implementations.examples;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

import lx.zv.model.persistence.bo.JaguARObject;
import lx.zv.model.persistence.bo.Marker;
import lx.zv.model.persistence.vo.Object3DOld;

public class DummieData {
	
	
	
	public List<JaguARObject> getJaguARObjectForPreloading(){

		
		List<JaguARObject> jaguarObjects = new ArrayList<JaguARObject>();
		
		JaguARObject jo;
		
		
		Object3DOld object3D;
		Marker marker;
		List<String> texto;
		Location location;
		
		jo = new JaguARObject();
		jo.id = 1;
		
		object3D = new Object3DOld();
		
	
		
		
		
		
		
				
		
		return jaguarObjects;
		
	}

}
