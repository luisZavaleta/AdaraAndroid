package lx.zv.jaguar.view;




import lx.zv.jaguar.annotations.Preload3DObjects;
import lx.zv.jaguar.annotations.view.MenuView;
import lx.zv.jaguar.business.sensors.util.JagArTouchObservable;
import lx.zv.jaguar.business.syncronizate.SyncronizeData;
import lx.zv.jaguar.config.Config;
import lx.zv.jaguar.view.helper.JaguARRenderer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;

import lx.zv.model.persistence.util.Print;






/*
 * JaguarActivity, provides a way to render Augmented reality applications in android, 
 * it uses OpenGL , it uses JaguARRenderer as a helper for rendering the objects, menu, etc.
 *
  @author Luis Zavaleta 
 */




public class JaguARActivity extends AndARActivity {
	
	/*private final int MENU_SCREENSHOT = 0;
	private final int SYNCRONIZE_DATA = 1;
	*/

	static TextView tv;
	
	
	private ARToolkit artoolkit;
	JagArTouchObservable jat;

	//private ProgressDialog waitDialog;

	@Override	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	
		Config.context = this.getBaseContext();	
		Config.thiz = this;
		
	if (Config.DEBUG)
		Log.i("AspectJ", "Entrando :) a aspect J ");
	
	
	

	 /*
	Print  p = new Print();
	 
	p.printTable("relations");
	p.printTable("menu");
	p.printTable("object3d");
	p.printTable("marker");*/
	
	/*END Testing*/
	
		artoolkit = super.getArtoolkit();		
		jat = new JagArTouchObservable();
		this.configure(artoolkit);	
		startPreview();
		
		
	}



	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("AndAR EXCEPTION", ex.getMessage());
		finish();
		
	}

	/*Ejecuta el precargado de objetos en: lx.zv.jaguar.aspects.object3D.Load */
	@Preload3DObjects(2)
	private void configure(ARToolkit artoolkit){
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		Config.displayMetrics = displayMetrics;

		//Rendered do all the magic
		JaguARRenderer renderer = new JaguARRenderer(displayMetrics, artoolkit.getRegisteredObjects(), this.jat);		
		super.setNonARRenderer(renderer);		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action =  event.getAction();

		if(action == MotionEvent.ACTION_DOWN){
			int x = (int)event.getX();
			int y = (int)event.getY();         

			jat.changed(x, y);
		}

	
		
		return true;
	}
	

	@Override
	@MenuView(sincronizate = true,photo= true)
	public boolean onCreateOptionsMenu(Menu menu) {		
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return true;
		
	}
	
	@Deprecated
	private void testing(){		
		SyncronizeData sd = new SyncronizeData();
		sd.syncronizeWithServer(1);	
	}	
}
