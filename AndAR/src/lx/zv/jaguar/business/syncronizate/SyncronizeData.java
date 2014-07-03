package lx.zv.jaguar.business.syncronizate;

import java.util.List;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import lx.zv.model.persistence.dao.DataFromServer;
import lx.zv.model.persistence.dao.LocalizationDAO;
import lx.zv.model.persistence.dao.MarkerDAO;
import lx.zv.model.persistence.dao.MenuDAO;
import lx.zv.model.persistence.dao.Object3DDAO;
import lx.zv.model.persistence.dao.RelationDAO;
import lx.zv.model.persistence.vo.Relations;
import lx.zv.model.persistence.vo.sincronization.ChildMenuVO;
import lx.zv.model.persistence.vo.sincronization.DataVO;
import lx.zv.model.persistence.vo.sincronization.LocalizationVO;
import lx.zv.model.persistence.vo.sincronization.MarkerVO;
import lx.zv.model.persistence.vo.sincronization.MenuVO;
import lx.zv.model.persistence.vo.sincronization.Object3DVO;
import lx.zv.model.persistence.vo.sincronization.RelationVO;
import lx.zv.model.persistence.vo.sincronization.RestResponseVO;
import lx.zv.persistence.config.DBAdapter;
import lx.zv.persistence.config.JagarDatabaseHelper;
import lx.zv.util.AdaraUtil;



public class SyncronizeData {

	SQLiteDatabase db = null;
	
	Object3DDAO odao= new Object3DDAO(); 
	RelationDAO rdao = new RelationDAO();
	MenuDAO medao = new MenuDAO();
	MarkerDAO madao = new MarkerDAO();
	LocalizationDAO ldao = new LocalizationDAO();
	
	public boolean syncronizeWithServer(int role) {

		boolean success = false;

		DataFromServer dfs = new DataFromServer();
		
		
	
		if(dfs != null){
			
		Log.w("Sync", "DFS is not null");	
		
		
		
	
		RestResponseVO restResponse = dfs.getDataByIdRole(role);
		
		if(restResponse != null){
			Log.w("Sync", "restResponse is not null");	
			
		
		
		
		DataVO dvo = restResponse.getData();
		
		
		
		
		List<RelationVO> relations = dvo.getRelation();

		db = DBAdapter.getInstance().getDatabase();

		db.beginTransaction();

		new JagarDatabaseHelper().upgrade(db);
		
		try {
			for (RelationVO relation : relations) {
				
				
				
				Long a = rdao.insert(relation);
				Long b = odao.insert(relation.getObject3D());
				Long c = medao.insert(relation.getMenu());
				insertMenuChilds(relation.getMenu().getChilds());
				Long d = madao.insert(relation.getMarker());
				Long e = ldao.insert(relation.getLocalization());

				Log.i("Sync", a.toString());
				Log.i("Sync", b.toString());
				Log.i("Sync", c.toString());
				Log.i("Sync", d.toString());
				Log.i("Sync", e.toString());

				
			}
			db.setTransactionSuccessful();
			 success = true;
			 
			
			 
		} catch (SQLException sqle) {
			
			Log.e("DatabaseError", sqle.toString());
		} finally {
			db.endTransaction();
			db.close();
		}
		
		
		}
		
		
		}
		
		Log.w("Sync", "return success--->"+success);	
		return success;
	}
	
	
	private void insertMenuChilds(List<ChildMenuVO> childs){
		
		for(ChildMenuVO child: childs){
			medao.insert(child.getMenu());
			if(child.getChilds() != null && child.getChilds().size()>0){
				insertMenuChilds(child.getChilds());
			}
		}
		
		
	}
	
	
}
