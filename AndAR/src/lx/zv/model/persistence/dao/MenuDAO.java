package lx.zv.model.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import lx.zv.jaguar.business.opengl.menu.MenuText;
import lx.zv.model.persistence.vo.MenuTextVO;
import lx.zv.model.persistence.vo.Object3DOld;
import lx.zv.model.persistence.vo.RelationsOld;
import lx.zv.model.persistence.vo.sincronization.ChildMenuVO;
import lx.zv.model.persistence.vo.sincronization.MenuVO;
import lx.zv.model.persistence.vo.sincronization.Object3DVO;
import lx.zv.persistence.config.DBAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MenuDAO {
	
	SQLiteDatabase db = null;
	
	/*"create table menutext (idMenuText integer, idParent integer, texto text, orden integer, " +
																"latitud real, longitud real, altitud real)";
	 * 
	 * */
private final String TABLE_NAME = "menu";
	
	private final String[] table = {"id", "parentMenu", "texto", "orden"};
	

	
	
	public MenuDAO(){	
		db = DBAdapter.getInstance().getDatabase();
	}
	
	public long insert(MenuVO mvo) throws SQLException{
		Cursor cur = db.query(TABLE_NAME, new String[]{"id"}, "id = ?", new String[]{Long.valueOf(mvo.getId()).toString()}, null, null, null);

		if(!cur.moveToFirst()){
			ContentValues contentValues = createContentValue(mvo);
		
	long r = db.insertOrThrow(TABLE_NAME, null, contentValues);
	cur.close();
			return r;

		}
		cur.close();
		return -1;
	}

	
	public List<MenuVO> getAll(){
		
		 List<MenuVO>  menus = new  ArrayList<MenuVO>();
		 Cursor cur = db.query(TABLE_NAME, table, null, null, null, null, null);
		 
		 while(cur.moveToNext()){			 
			 menus.add(cursorToObject(cur));			 
		 }
		 cur.close();
		return menus;
	}

	
	public MenuVO getById(long id){
		Cursor cur = db.query(TABLE_NAME, table, "id = ?", new String[]{Long.valueOf(id).toString()}, null, null, null);
		if(cur.moveToFirst()){
			MenuVO cto= cursorToObject(cur);
			cur.close();
			return cto;
		}	
		cur.close();
		return null;
	}
	
	
	public List<MenuVO> getByParentId(long id){
		
		Cursor cur = db.query(TABLE_NAME, table, "parentMenu = ?", new String[]{Long.valueOf(id).toString()}, null, null, null);
		
		List<MenuVO> lm = new ArrayList<MenuVO>();
		
		while(cur.moveToNext()){
			lm.add(cursorToObject(cur));
			
		}
			cur.close();
		return lm;
	}
	
	

	public List<MenuVO> getByParentIdOrderByOrden(long id){
		
		Cursor cur = db.query(TABLE_NAME, table, "parentMenu = ?", new String[]{Long.valueOf(id).toString()}, null, null, "orden");
		
		List<MenuVO> lm = new ArrayList<MenuVO>();
		
		while(cur.moveToNext()){
			lm.add(cursorToObject(cur));
			
		}
			cur.close();
		return lm;
	}
	
	
	

	private MenuVO cursorToObject(Cursor cur){
		
		MenuVO mvo = new MenuVO();
		
		mvo.setId(cur.getLong(0));
		mvo.setParentMenu(cur.getLong(1));
		mvo.setText(cur.getString(2));
		mvo.setOrder(cur.getInt(3));
		
		
		return mvo;
	}
	

	
	private ContentValues createContentValue(MenuVO mvo){
	//"create table object3d (idObject3D integer, obj blob, mtl blob, image blob, imageExtension string)";
		ContentValues values = new ContentValues();
		
		
		values.put("id", mvo.getId());
		values.put("parentMenu", mvo.getParentMenu());
		values.put("texto", mvo.getText());
		values.put("orden", mvo.getOrder());
		
		
		return values;
		
	}
	
	
	public Cursor getAllTemp(){		
		return db.rawQuery("select * from "+TABLE_NAME, null);
	}
	
	
}
