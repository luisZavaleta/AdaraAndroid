package lx.zv.model.persistence.util;

import lx.zv.persistence.config.DBAdapter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Print {
	
	
	SQLiteDatabase db = null;
	
	
	public Print(){	
		db = DBAdapter.getInstance().getDatabase();
	}
	
	
	
	
	/*Sorry for this, is just for testing purpose, I have to do this, 
	 * because I didn't find a stable database manager for android (whitout root mode) */
	
	public void printTable(String tableName){
		
		Cursor cur = db.rawQuery("SELECT * FROM "+tableName, null);	
		
		if(cur == null){
			return;
		}
		
		
		
		int count = cur.getColumnCount();
		
		Log.i("DBData", "=================================================================");
		Log.i("DBData", "Base de datos: "+tableName);
		
		
		
		while(cur.moveToNext()){	
			
			String d = "";
			
			for(int i =0; i<count; i++){
			
				//Ok, you can kill me for this, but you don't know where do I live
				String st ="";
				
			try{
				st = cur.getString(i); 
				
			}catch(Exception ex){
				
				
			}
				
				d = d + cur.getColumnName(i) + "<--" + st +";   ";
			}
			
			Log.i("DBData",d);
			
			
		}
		Log.i("DBData", "=================================================================");
		
		
		
		
		
		
	}
	
	
	
	
	

}
