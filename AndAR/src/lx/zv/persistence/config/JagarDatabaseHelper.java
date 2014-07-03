package lx.zv.persistence.config;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import lx.zv.jaguar.config.Config;

public class JagarDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "jaguar";

	private static final int DATABASE_VERSION = 9;
	
	//cambiar sentencias SQL a mayusculas
	//private static final String DATABASE_CREATE_USUARIOS = 		"create table users (idUsuario integer, idDispositivo integer, name text)";
	

//{"id":1,"version":0,"idUsuario":1,"id3DObject":1,"markerName":"1","idMenu":1}
	private static final String DATABASE_CREATE_RELATIONS = 	
			"CREATE TABLE relations (" +
					"id integer, " +
					"idLocalization integer, " +
					"idMarker integer, " +
					"idMenu integer, " +
					"idObject3D integer" +
			")";
	
	private static final String DATABASE_CREATE_LOCALIZATION = 	
			"CREATE TABLE localization (" +
					"id integer, " +
					"altitude real, " +
					"latitud real, " +
					"longitud real" +
			")";
	
	
	private static final String DATABASE_CREATE_MARKER = 		
			"CREATE TABLE marker (" +
					"id integer, " +
					"name String, " +
					"pattern blob" +
			")";
	
	
	private static final String DATABASE_CREATE_MENU= 	
			"CREATE TABLE menu (" +
					"id integer, " +
					"parentMenu integer, " +
					"texto text, " +
					"orden integer " +
			")";
	
	
	
	private static final String DATABASE_CREATE_OBJECT3D = 		
			"CREATE TABLE object3d (" +
					"id integer, " +
					"obj blob, " +
					"mtl blob, " +
					"img blob, " +
					"imageExtension text, " +
					"baseUrl text, " +
					"baseFileName text)";
	
	
	
	
	public JagarDatabaseHelper() {		
		super(Config.context, DATABASE_NAME, null, DATABASE_VERSION );
	}
	
	public JagarDatabaseHelper(Context context) {		
		super(context, DATABASE_NAME, null, DATABASE_VERSION );
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
	
		db.execSQL(DATABASE_CREATE_LOCALIZATION);
		db.execSQL(DATABASE_CREATE_MENU);
		db.execSQL(DATABASE_CREATE_RELATIONS);
		db.execSQL(DATABASE_CREATE_MARKER);
		db.execSQL(DATABASE_CREATE_OBJECT3D);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(this.getClass().getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		
		upgrade(db);
	}
	
	
	public void upgrade(SQLiteDatabase db){

		
		db.execSQL("DROP TABLE IF EXISTS relations");
		db.execSQL("DROP TABLE IF EXISTS menu");
		db.execSQL("DROP TABLE IF EXISTS localization");
		db.execSQL("DROP TABLE IF EXISTS marker");
		db.execSQL("DROP TABLE IF EXISTS object3d");
		onCreate(db);
	}

}
