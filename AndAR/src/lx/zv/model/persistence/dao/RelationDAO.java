package lx.zv.model.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import lx.zv.model.persistence.vo.Object3DOld;
import lx.zv.model.persistence.vo.RelationIdsVO;
import lx.zv.model.persistence.vo.RelationsOld;
import lx.zv.model.persistence.vo.sincronization.LocalizationVO;
import lx.zv.model.persistence.vo.sincronization.MarkerVO;
import lx.zv.model.persistence.vo.sincronization.MenuVO;
import lx.zv.model.persistence.vo.sincronization.Object3DVO;
import lx.zv.model.persistence.vo.sincronization.RelationVO;
import lx.zv.persistence.config.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RelationDAO {
	

	private final String[] table = {"id", "idObject3D", "idMenu", "idMarker", "idLocalization"};
	
	private String TABLE_NAME =  "relations";

	SQLiteDatabase db = null;
	
	public RelationDAO(){	
		db = DBAdapter.getInstance().getDatabase();
	}
	
	public long insert(RelationVO rvo) throws SQLException{
		Cursor cur = db.query(TABLE_NAME, new String[]{"id"}, "id = ?", new String[]{Integer.valueOf(rvo.getId()).toString()}, null, null, null);

		if(!cur.moveToFirst()){
			ContentValues contentValues = createContentValue(rvo);		
			return db.insertOrThrow(TABLE_NAME, null, contentValues);					
		}
		return -1;
	}
	
	
	
	public List<RelationVO> getAll(){
		
		List<RelationVO> localizations = new ArrayList<RelationVO>();
		
		Cursor cur = db.query(TABLE_NAME, table, null, null, null, null, null);
		
		while(cur.moveToNext()){
			
			RelationVO rvo = new RelationVO();
			
			
			int idRel = cur.getInt(0);
			int idObj3D = cur.getInt(1);
			int idMenu = cur.getInt(2);
			int idMarker = cur.getInt(3);
			//int idLocalization = cur.getInt(4);
	
			
			
			Log.i("RelationDAO","idRel-->"+idRel);
			Log.i("RelationDAO","idObj3D-->"+idObj3D);
			Log.i("RelationDAO","idMenu-->"+idMenu);
			Log.i("RelationDAO","idMarker-->"+idMarker);
			//Log.i("RelationDAO","idLoc-->"+idLocalization);
			
			
			MarkerVO m = new MarkerDAO().getById(idMarker);
			MenuVO me = new MenuDAO().getById(idMenu);
			Object3DVO o = new Object3DDAO().getById(idObj3D);
			
			
			
			
			
			rvo.setId(idRel);
			
		
			//rvo.setLocalization(new LocalizationDAO().getById(idLocalization));
			rvo.setMarker(m);
			rvo.setMenu(me);
			rvo.setObject3D(o);
	
			localizations.add(rvo);			
		}
		
		
		return localizations;
	}
	
	
public List<RelationIdsVO> getAllIds(){
		
		List<RelationIdsVO> localizations = new ArrayList<RelationIdsVO>();
		
		Cursor cur = db.query(TABLE_NAME, table, null, null, null, null, null);
		
		while(cur.moveToNext()){
			
			RelationIdsVO rvo = new RelationIdsVO();
			
			rvo.setId(cur.getInt(0));
			rvo.setLocalization(cur.getInt(1));
			rvo.setMarker(cur.getInt(2));
			rvo.setMenu(cur.getInt(3));
			rvo.setObject3D(cur.getInt(4));
			
			
			localizations.add(rvo);			
		}
		
		
		return localizations;
	}
	
	
	
	private ContentValues createContentValue(RelationVO rvo){
	//"create table object3d (idObject3D integer, obj blob, mtl blob, image blob, imageExtension string)";
		ContentValues values = new ContentValues();
		
	
		values.put("id", rvo.getId());
		values.put("idObject3D", rvo.getObject3D().getId());
		values.put("idMenu", rvo.getMenu().getId());
		values.put("idMarker", rvo.getMarker().getId());
		values.put("idLocalization", rvo.getLocalization().getId());

		
		return values;
		
	}
	
	
	public Cursor getAllTemp(){		
		return db.rawQuery("select * from "+TABLE_NAME, null);
	}
	

}
