package lx.zv.jaguar.business.syncronizate;

import lx.zv.jaguar.config.Config;
import lx.zv.model.persistence.dao.LocalizationDAO;
import lx.zv.model.persistence.dao.MarkerDAO;
import lx.zv.model.persistence.dao.MenuDAO;
import lx.zv.model.persistence.dao.Object3DDAO;
import lx.zv.model.persistence.dao.RelationDAO;
import lx.zv.persistence.config.DBAdapter;
import lx.zv.util.AdaraUtil;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncSyncronize extends AsyncTask<Integer, Void, Boolean> {
	SyncronizeData sd = new SyncronizeData();
	//SQLiteDatabase db = null;

	@Override
	protected Boolean doInBackground(Integer... args) {
		boolean success = false;
		Log.i("Sync", "WTF!!!!! 1");
		
		
		try {
			Log.i("Sync", "Asincronous Syncronization");

			//db = DBAdapter.getInstance().getDatabase();
			//db.beginTransaction();
			Log.i("Sync", "Beginning transaction...");
			
			try {
				Log.i("Sync", "WTF!!!!! 2");
				
				
				
				
				for (Integer arg : args) {
					//Log.i("Sync", "WTF!!!!! 3");
					success = sd.syncronizeWithServer(arg);
				}
				//db.setTransactionSuccessful();
				Log.i("Sync", "Ending transaction...");

				
				
				
				
				// testing();
				

			} catch (SQLException ex) {
				success = false;
				AdaraUtil.showSimpleMessage("ADARA", "Error");
				Log.e("Sync", ex.toString());
			} finally {
				//db.endTransaction();
				//db.close();
				Log.i("Sync", "Asincronous Syncronization ending transaction");
				//Syncronization
			
			}
			Log.i("Sync", "Asincronous Syncronization END");
		} catch (SQLException ex) {

			Log.e("Sync", "Error occurred while Syncronizating--->" + ex.toString());
		}
		
		Log.i("Sync", "WTF!!!!! Final"); 
		return success;
	}
	
	
	@Override
	protected void onPostExecute(Boolean success) {

		//Log.w("Aspect", "Asincronous Tres xx");
		
		Log.i("Sync", "Sucessss-->"+success);

		if(success){
			AdaraUtil.showSimpleMessage("ADARA", "la base de datos ha sido actualizada");
		}else{
			AdaraUtil.showSimpleMessage("ADARA", "Error de conexi�n");
			
		}
		
		/*
		 * AlertDialog alertDialog = new
		 * AlertDialog.Builder(Config.context).create();
		 * alertDialog.setTitle("Sincronización de base de datos"); if(success){
		 * alertDialog
		 * .setMessage("La base de datos se sincronizó correctamente"); }else{
		 * alertDialog
		 * .setMessage("La base de datos no se sincronizó debido a un error"); }
		 * alertDialog.show();
		 * hola
		 */
	}

}
