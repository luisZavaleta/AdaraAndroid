package lx.zv.model.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import lx.zv.model.persistence.vo.Object3DOld;
import lx.zv.model.persistence.vo.sincronization.LocalizationVO;
import lx.zv.model.persistence.vo.sincronization.MarkerVO;
import lx.zv.persistence.config.DBAdapter;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LocalizationDAO {

	
	private final String[] table = {"id", "altitude", "latitud", "longitud"};
	
	private String TABLE_NAME =  "localization";

	SQLiteDatabase db = null;
	
	public LocalizationDAO(){	
		db = DBAdapter.getInstance().getDatabase();
	}
	
	public long insert(LocalizationVO lvo) throws SQLException{
		Cursor cur = db.query(TABLE_NAME, new String[]{"id"}, "id = ?", new String[]{Integer.valueOf(lvo.getId()).toString()}, null, null, null);

		if(!cur.moveToFirst()){
			ContentValues contentValues = createContentValue(lvo);
			
		
			return db.insertOrThrow(TABLE_NAME, null, contentValues);
		
			
		}
		return -1;
	}
	
	
	public List<LocalizationVO> getAll(){
		
		List<LocalizationVO> localizations = new ArrayList<LocalizationVO>();
		
		Cursor cur = db.query(TABLE_NAME, table, null, null, null, null, null);
		
		while(cur.moveToNext()){			
			
			localizations.add(cursorToObject(cur));			
		}
		
		
		return localizations;
	}

	public LocalizationVO getById(long id){
		Cursor cur = db.query(TABLE_NAME, new String[]{"id"}, "id = ?", new String[]{Long.valueOf(id).toString()}, null, null, null);
		if(cur.moveToFirst()){
			return cursorToObject(cur);
		}	
		
		
		return null;
	}
	
private LocalizationVO cursorToObject(Cursor cur){

		
	
	LocalizationVO lvo = new LocalizationVO();
	
	lvo.setId(cur.getInt(0));
	
try{
	lvo.setAltitude(cur.getLong(1));
	lvo.setLatitude(cur.getLong(2));
	lvo.setLatitude(cur.getLong(3));
}catch(Exception ex){
	ex.printStackTrace();
	
}
		
		return lvo;
	}
	
	private ContentValues createContentValue(LocalizationVO lvo){
	//"create table object3d (idObject3D integer, obj blob, mtl blob, image blob, imageExtension string)";
		ContentValues values = new ContentValues();
		
	
		values.put("id", lvo.getId());
		values.put("altitude", lvo.getAltitude());
		values.put("latitud", lvo.getLatitude());
		values.put("longitud", lvo.getLongitude());

		
		
		return values;
		
	}
	
	public Cursor getAllTemp(){		
		return db.rawQuery("select * from "+TABLE_NAME, null);
	}
	
	
}
