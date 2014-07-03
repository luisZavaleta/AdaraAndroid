package lx.zv.model.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import lx.zv.model.persistence.vo.sincronization.MarkerVO;
import lx.zv.model.persistence.vo.sincronization.MenuVO;
import lx.zv.persistence.config.DBAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MarkerDAO {

	private final String[] table = {"id", "name", "pattern"};
	
	private String TABLE_NAME =  "marker";

	SQLiteDatabase db = null;
	
	public MarkerDAO(){	
		db = DBAdapter.getInstance().getDatabase();
	}
	
	public long insert(MarkerVO mvo) throws SQLException{
		Cursor cur = db.query(TABLE_NAME, new String[]{"id"}, "id = ?", new String[]{Integer.valueOf(mvo.getId()).toString()}, null, null, null);

		if(!cur.moveToFirst()){
			ContentValues contentValues = createContentValue(mvo);
		
			return db.insertOrThrow(TABLE_NAME, null, contentValues);
	
		
		}
		return -1;
	}
	
	
	public List<MarkerVO> getAll(){
		List<MarkerVO>  markers = new ArrayList<MarkerVO>();
		 
		Cursor cur = db.query(TABLE_NAME, table, null, null, null, null, null);
		while(cur.moveToNext()){
			markers.add(cursorToObject(cur));
		} 
		 
		
		return markers;
	}
	
	
	public MarkerVO getById(long id){
		
		Cursor cur = db.query(TABLE_NAME, table, "id = ?", new String[]{Long.valueOf(id).toString()}, null, null, null);
		if(cur.moveToFirst()){
			return cursorToObject(cur);
		}else{	
			return null;
		}
	}
	
private MarkerVO cursorToObject(Cursor cur){
		
		MarkerVO mvo = new MarkerVO();
		
		mvo.setId(cur.getInt(0));
		mvo.setName(cur.getString(1));
		mvo.setPattern(cur.getBlob(2));
		
		
		return mvo;
	}
	
	private ContentValues createContentValue(MarkerVO mvo){
	//"create table object3d (idObject3D integer, obj blob, mtl blob, image blob, imageExtension string)";
		ContentValues values = new ContentValues();
		
		
		values.put("id", mvo.getId());
		values.put("name", mvo.getName());
		values.put("pattern", mvo.getPattern());

		
		
		return values;
		
	}
	
	public Cursor getAllTemp(){		
		return db.rawQuery("select * from "+TABLE_NAME, null);
	}
		
}
