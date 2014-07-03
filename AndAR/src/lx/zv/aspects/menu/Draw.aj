package lx.zv.aspects.menu;

import java.util.Observable;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import android.util.DisplayMetrics;
import android.util.Log;
import edu.dhbw.andar.ARObject;
import edu.dhbw.andar.ARToolkit;
import lx.zv.annotations.menu.DrawMenu;
import lx.zv.business.facade.menu.MenuFacade;
import lx.zv.exceptions.JaguARException;
import lx.zv.jaguar.annotations.Preload3DObjects;
import lx.zv.jaguar.view.helper.JaguARRenderer;



public aspect Draw {
	

	
	/*
	Vector<ARObject> arObjects = JaguARRenderer.;
	Observable obs;	
	DisplayMetrics displayMetrics = null;
	*/
	
	private MenuFacade JaguARRenderer._mf = null;
	
	pointcut drawMenu(DrawMenu dm, GL10 gl, JaguARRenderer jr) : 
		execution (@DrawMenu *  draw(GL10)) && @annotation(dm) && args(gl)  && this(jr);
	


	
	after(DrawMenu dm , GL10 gl, JaguARRenderer jr) : drawMenu(dm,gl,jr) {
	
		Log.e("DrawMenu","Inside aspect drawMenuXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
		
		
		if(dm.value() == true){		
			Log.e("DrawMenu","dm.value() == true");
			
			
			if(jr._mf == null){	
				
				jr._mf = new MenuFacade(jr.arObjects, jr.displayMetrics, jr.obs);
			}
			
			try {
				Log.e("DrawMenu","jr._mf.drawMenu(gl);");
				jr._mf.drawMenu(gl);
			} catch (JaguARException e) {
				e.printStackTrace();
			}			
			
		}
		
	}
	
	
	/*
	pointcut drawMenu(DrawMenu drawMenu, GL10  gl, JaguARRenderer jr) : call (@DrawMenu *  draw(GL10))
	&& @annotation(drawMenu) && args(gl); && this(jr);
	
	
	
	
	//public pointcut asyncTask() : call(protected Void lx.zv.caching.helper.AsyncPreload.doInBackground(Void...));
	
	
	
	after(DrawMenu drawMenu, GL10  gl, JaguARRenderer jr) : drawMenu(drawMenu,gl,jr){
		Log.e("DrawMenu","Inside aspect drawMenu");
		
		
		if(drawMenu.value() == true){			
			if(jr._mf == null){			
				jr._mf = new MenuFacade(jr.arObjects, jr.displayMetrics, jr.obs);
			}
			
			try {
				jr._mf.drawMenu(gl);
			} catch (JaguARException e) {
				e.printStackTrace();
			}			
			
		}
		
		
	}*/

}
