package lx.zv.model.persistence.dao;


import java.util.ArrayList;
import java.util.List;

import lx.zv.jaguar.config.Config;
import lx.zv.model.persistence.vo.Object3DOld;
import lx.zv.model.persistence.vo.sincronization.Object3DVO;
import lx.zv.persistence.config.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Object3DDAO {

	
	private final String TABLE_NAME = "object3d";
	// baseUrl string, baseFileName string
	
	private final String[] table = {"id", "obj", "mtl","img", "imageExtension", "baseUrl","baseFileName"};
	

	SQLiteDatabase db = null;
	
	public Object3DDAO(){	
		db = DBAdapter.getInstance().getDatabase();	
	}
	
	
	
	
	public long insert(Object3DVO o3d)  throws SQLException{
		
		Cursor cur = db.query(TABLE_NAME, new String[]{"id"}, "id = ?", new String[]{Integer.valueOf(o3d.getId()).toString()}, null, null, null);
		
		if(!cur.moveToFirst()){
			ContentValues contentValues = createContentValue(o3d);
			
			
	
			return db.insertOrThrow(TABLE_NAME, null, contentValues);
		
		}
		
		return -1;
		
	}
	
	
public Object3DVO getById(long id){
	Cursor cur = db.query(TABLE_NAME, table, "id = ?", new String[]{Long.valueOf(id).toString()}, null, null, null);
	
	if(cur != null){
	cur.moveToNext();
	
	return cursorToObject(cur);
	}else{
		
		return null;
	}
}
	
	

	
	

	public List<Object3DVO> getAll(){
		
		List<Object3DVO>  objects3d = new ArrayList<Object3DVO>();

		Cursor cur = db.query(TABLE_NAME, table, null, null, null, null, null);
		
		while(cur.moveToNext()){
			
			Object3DVO o3d = cursorToObject(cur);
		
			objects3d.add(o3d);				
		}
		
		return objects3d;
	}
	
	

	
	public ArrayList<Object3DOld> getNearestObjects(int quiantity){
		
		ArrayList<Object3DOld>  o3dList = new ArrayList<Object3DOld> ();
		
		//ArrayList<Object3DOld>  allList = this.getAllObjects();
		
		return null;
			
	}
	
	
	public Cursor getAllTemp(){		
		return db.rawQuery("select * from "+TABLE_NAME, null);
	}
	
	
	
	
	private Object3DVO cursorToObject(Cursor cur){
		
		Object3DVO o3d = new Object3DVO();	
		
		o3d.setId(cur.getInt(0));
		o3d.setObj(cur.getBlob(1));
		o3d.setMtl(cur.getBlob(2));
		o3d.setImg(cur.getBlob(3));
		o3d.setImageExtension(cur.getString(4));
		o3d.setBaseURL(cur.getString(5));
		o3d.setBaseFileName(cur.getString(6));
		
		
		return o3d;
	}
	
	
	
	
	
	private ContentValues createContentValue(Object3DVO o3d){
	//"create table object3d (idObject3D integer, obj blob, mtl blob, image blob, imageExtension string)";
		ContentValues values = new ContentValues();
		
		values.put("id", o3d.getId());
		values.put("obj", o3d.getObj());
		values.put("mtl", o3d.getMtl());
		values.put("img", o3d.getImg());
		values.put("imageExtension", o3d.getImageExtension());
		
		//baseFileName
		values.put("baseUrl", o3d.getBaseURL());
		values.put("baseFileName", o3d.getBaseFileName());
	

		
		return values;
		
	}
	

}