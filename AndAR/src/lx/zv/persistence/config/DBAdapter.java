package lx.zv.persistence.config;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	
	private static JagarDatabaseHelper jdbh  = null;
	private static DBAdapter dbadapter= null;
	

	
	
	private DBAdapter(){}

	
	public static DBAdapter getInstance(){
	
		if(dbadapter == null){
			return new DBAdapter();
		}
	
		return dbadapter;
	} 
	
	
	
	
	
	
	public SQLiteDatabase getDatabase(){
		
		if(jdbh == null){
			jdbh = new JagarDatabaseHelper();
			
		}		
		return jdbh.getWritableDatabase();
		
	}
	
	
public SQLiteDatabase getDatabase(Context context){
		
		if(jdbh == null){
			jdbh = new JagarDatabaseHelper(context);
		}		
		return jdbh.getWritableDatabase();
		
	}
	
	
	public void close(){
		jdbh.close();
	}
	
	
}
