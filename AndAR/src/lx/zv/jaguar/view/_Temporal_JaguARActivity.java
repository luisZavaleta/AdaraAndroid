package lx.zv.jaguar.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lx.zv.exceptions.JaguARException;
import lx.zv.jaguar.annotations.Preload3DObjects;
import lx.zv.jaguar.annotations.testingAnotations;
import lx.zv.jaguar.business.opengl.util.BoxObject;
import lx.zv.jaguar.business.sensors.util.JagArTouchObservable;
import lx.zv.jaguar.view.helper.JaguARRenderer;
import lx.zv.jaguar.view.helper.Messages;
import lx.zv.model.persistence.dao.MarkerDAO;
import lx.zv.model.persistence.dao.MenuDAO;
import lx.zv.model.persistence.dao.MenuTextFromServerDAO;
import lx.zv.model.persistence.dao.Object3DDAO;
import lx.zv.model.persistence.dao.Object3DFromServerDAO;
import lx.zv.model.persistence.dao.RelationDAO;
import lx.zv.model.persistence.dao.RelationsFromServerDAO;
import lx.zv.model.persistence.vo.Object3DOld;
import lx.zv.model.persistence.vo.RelationsOld;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.exceptions.AndARException;
import edu.dhbw.andobjviewer.graphics.Model3D;
import edu.dhbw.andobjviewer.models.Model;
import edu.dhbw.andopenglcam.R;
import lx.zv.model.objects3d.ModelLoader;


/*
 * JaguarActivity, provides a way to render Augmented reality applications in android, 
 * it uses OpenGL , it uses JaguARRenderer as a helper for rendering the objects, menu, etc.
 *
  @author Luis Zavaleta 
 */




public class _Temporal_JaguARActivity extends AndARActivity {

	private final int MENU_SCREENSHOT = 0;
	private final int SYNCRONIZE_DATA = 1;

	private Context context;
	private AssetManager assetsManager;
	private ARToolkit artoolkit;
	private ProgressDialog waitDialog;
	JagArTouchObservable jat;

	RelationDAO relationsDao;
	MarkerDAO markersDao;
	private Object3DDAO object3DDao;





	/*	
	//private Model model;
	//private Model3D model3D;
	//private static ArrayList<Model3D> models3d = new ArrayList<Model3D>();
	private ProgressDialog syncronizingDialog;
	RelationsDAO rd;
	//MarkersDAO md;
	//private int idUser = 1; //Hardcoded for the moment*/
	/*BoxObject someObject;
	ARToolkit artoolkit;
	//StatisticsDAO useDao;
	 */
	
	
	
	
	@Override
	@Preload3DObjects(2)
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);	

		this.context = this.getBaseContext();
		this.assetsManager = getResources().getAssets();		
		artoolkit = super.getArtoolkit();	

		
		
		//Rendered do all the magic
		//JaguARRenderer renderer = new JaguARRenderer(this, artoolkit.getRegisteredObjects(), this.jat);
		//super.setNonARRenderer(renderer);		

		this.waitDialog = ProgressDialog.show(this, Messages.getString("jaguar.progressDialog.title"), Messages.getString("jaguar.progressDialog.loading"), true); //$NON-NLS-1$ //$NON-NLS-2$

		try{
			throw new AndARException("");
			/*
			 * 
			 * Do the loading of the three nearest objects.
			 * 
			 * */
		} catch (AndARException ex){
			//handle the exception, that means: show the user what happened

			//Send alert dialog with the error
			System.out.println("The objects couldn't be registered"); 
		}	


		//waitDialog.show();

		//new PreLoadObjects().execute();
		//android.obj

		//ModelLoader ml = null;//= new ModelLoader(ModelLoader.TYPE_INTERNAL, this.getResources().getAssets());





		//Object3DDAO o3 = new Object3DDAO(this.getBaseContext());

		//Object3D ox =	o3.getObjectById(1);



		//Hola hola = new Hola(this);

		//Documents doc = hola.getPlayedMatches();



		/*

		try {

			//Agregamos un objeto, este es el que se usa para las pruebas con textures.
			//someObject = new BoxObject
				//("test", "patt.hiro", 80.0, new double[]{0,0},this.getWidthS(),this.getHeigthS()); //$NON-NLS-1$ //$NON-NLS-2$
			//someObject.setMenuId(1);


			//artoolkit.registerARObject(someObject);

			//customObjects.add(someObject);


			someObject = new BoxObject
				("test", "android.patt", 80.0, new double[]{0,0},this.getWidthS(),this.getHeigthS());
			artoolkit.registerARObject(someObject);



		} catch (AndARException ex){
			//handle the exception, that means: show the user what happened
			System.out.println("The objects couldn't be registered"); //$NON-NLS-1$
		}	*/

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		super.surfaceCreated(holder);
		//load the model
		//this is done here, to assure the surface was already created, so that the preview can be started
		//after loading the model
		//Verify if all the objects are loaded correctly
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

	/**
	 * Inform the user about exceptions that occurred in background threads.
	 * This exception is rather severe and can not be recovered from.
	 * Inform the user and shut down the application.
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("AndAR EXCEPTION", ex.getMessage());
		finish();
	}	


	/* (non-Javadoc)
	 * Agrega menúes
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		menu.add(0, MENU_SCREENSHOT, 0, getResources().getText(R.string.takescreenshot)).setIcon(R.drawable.screenshoticon);	
		menu.add(0, SYNCRONIZE_DATA, 0, Messages.getString("jaguar.sincronizeData"));	 //$NON-NLS-1$
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()) {
		case MENU_SCREENSHOT:
			new TakeAsyncScreenshot().execute();
			break;

		case SYNCRONIZE_DATA:			
			this.waitDialog = ProgressDialog.show(this, "", getResources().getText(R.string.synchronizing_database), true);
			this.waitDialog.show();
			new  SyncronizeData().execute();			
			break;	
		}
		return true;
	}





	private class PreLoadObjects extends AsyncTask<Void, Void, Void>{


		@Override
		protected Void doInBackground(Void... params) {

			if(relationsDao == null){
				relationsDao = new RelationDAO();
			}
			if(object3DDao == null){
				//object3DDao = new Object3DDAO(context);	
			}





		//	List<RelationsOld> rel = relationsDao.getAllRelations();


			//	waitDialog.show();

/*
			try {
				this.loadModels(rel);
			} catch (JaguARException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

/*


			// TODO Auto-generated method stub
			return null;
		}





		private void loadModels(List<RelationsOld> rels) throws JaguARException{

			for(RelationsOld rel: rels){ 



				//String markerName = "patt.hiro";
				Object3DOld o = object3DDao.getObjectById(rel.getId3DObject());

				List<String> pattList = null;

				try {
					pattList = Arrays.asList(assetsManager.list("")); //$NON-NLS-1$
				} catch (IOException e) {
					e.printStackTrace();
					throw new JaguARException("model could not be loades"); //$NON-NLS-1$
				}


				if(pattList == null || !pattList.contains(rel.getMarkerName())){
					throw new JaguARException("pattern doesn exists");				 //$NON-NLS-1$
				}



				ModelLoader  ml  = null;// = new  ModelLoader();			 
				// ml.setParameters(TYPE_EXTERNAL, o.getBaseUrl()+".obj",rel.getMarkerName(),rel.getIdMenu()); 
				//ml.execute();

				break;
			}





		}


		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			/*
	//waitDialog.dismiss();

	//register model
	try {
		for(Model3D m3 : JaguARActivity.models3d){
			artoolkit.registerARObject(m3);	
	}
	} catch (AndARException e) {
			e.printStackTrace();
	}

models3d.clear();
			 */	


			//	if(model3d != null)
			//	artoolkit.registerARObject(model3d);


			startPreview();
			return null;
		}



	}	









	class TakeAsyncScreenshot extends AsyncTask<Void, Void, Void> {

		private String errorMsg = null;

		@Override
		protected Void doInBackground(Void... params) {
			Bitmap bm = takeScreenshot();
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(Messages.getString("jaguar.sdcard.path")+new Date().getTime()+".png"); //$NON-NLS-1$ //$NON-NLS-2$
				bm.compress(CompressFormat.PNG, 100, fos);
				fos.flush();
				fos.close();					
			} catch (FileNotFoundException e) {
				errorMsg = e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				errorMsg = e.getMessage();
				e.printStackTrace();
			}	
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if(errorMsg == null)
				Toast.makeText(_Temporal_JaguARActivity.this, getResources().getText(R.string.screenshotsaved), Toast.LENGTH_SHORT ).show();
			else
				Toast.makeText(_Temporal_JaguARActivity.this, getResources().getText(R.string.screenshotfailed)+errorMsg, Toast.LENGTH_SHORT ).show();
		};

	}



	private class SyncronizeData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {


			MenuTextFromServerDAO mtfsdao = new MenuTextFromServerDAO();
			MenuDAO mtdao = new MenuDAO();

			RelationDAO rdao = new RelationDAO();
			RelationsFromServerDAO rfs = new RelationsFromServerDAO();


		//	Object3DDAO o3ddao = new Object3DDAO(context);
			Object3DFromServerDAO o3dfs = new Object3DFromServerDAO();



			//mtdao.recreateTableFromServer(mtfsdao.getAllMenueText());			
			//rdao.recreateTableFromServer(rfs.getAll());
		//	o3ddao.recreateTableFromServer(o3dfs.getAll());









			waitDialog.dismiss();

			return null;
		}	

	}

}
