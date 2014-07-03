package lx.zv.aspects.view;

import edu.dhbw.andopenglcam.R;
import lx.zv.jaguar.annotations.view.MenuView;
import lx.zv.jaguar.business.syncronizate.AsyncSyncronize;
import lx.zv.jaguar.config.Config;
import lx.zv.jaguar.view.helper.Messages;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public aspect ViewMenuAspect {
	
	private final int MENU_SCREENSHOT = 0;
	private final int SYNCRONIZE_DATA = 1;

	pointcut menuOnCreateOption(MenuView menuView, Menu menu) : 
		execution(@MenuView * onCreateOptionsMenu(Menu)) && @annotation(menuView) && args(menu); 
	
	
	pointcut onOptionsItemSelected(MenuItem item) : 
		execution(public boolean onOptionsItemSelected(MenuItem)) && args(item);
	

	
	
	before(MenuView menuView, Menu menu) : menuOnCreateOption(menuView,menu){
		menu.add(0,0,0,"Sincronizar");
		//menu.add(0,1,0,"Menu ABC");
		
		/*Log.e("Aspect","menuOnCreateOption(menuView,menu)");
		menu.add(0, SYNCRONIZE_DATA, 0, Messages.getString("jaguar.sincronizeData"));	*/
	}
	
	
	before(MenuItem item) : onOptionsItemSelected(item){
		
		
		switch(item.getItemId()) {

			case 0:				
				Log.i("Sync", "Begining Syncronization");
				new AsyncSyncronize().execute(1);
				break;	
				
			/*case 1:			
				Log.w("Menu", "Menu ABC");
				break;*/
			}
		
	/*	Log.e("Aspect","onOptionsItemSelected(item)");
		
		switch(item.getItemId()) {
		case 0:
			new AsyncSyncronize().execute(1);
			break;
MarkerInfo
AspectJ
		}
		*/
		
	}
	

/*
 * 
 * 	menu.add(0, MENU_SCREENSHOT, 0, Config.context.getResources().getText(R.string.takescreenshot)).setIcon(R.drawable.screenshoticon);	
		
 * Config
 * */
	
}
