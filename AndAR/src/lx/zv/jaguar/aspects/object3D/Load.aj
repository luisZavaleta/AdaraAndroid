package lx.zv.jaguar.aspects.object3D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import lx.zv.jaguar.annotations.Preload3DObjects;
import lx.zv.jaguar.annotations.PreloadBaseTextTexture;
import lx.zv.jaguar.business.opengl.util.BoxObject;
import lx.zv.jaguar.config.Config;
import lx.zv.model.objects3d.JaguARConstants;
import lx.zv.model.objects3d.ModelLoader;
import lx.zv.textures.InitBaseTextTexture;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.exceptions.AndARException;
import edu.lmzv.andarmenu.ARMenueObject;
import android.content.res.AssetManager;
import android.util.Log;

import lx.zv.jaguar.view.JaguARActivity;
import lx.zv.model.persistence.dao.RelationDAO;
import lx.zv.model.persistence.dao.MarkerDAO;
import lx.zv.model.persistence.dao.Object3DDAO;
import lx.zv.model.persistence.vo.sincronization.RelationVO;
import lx.zv.jaguar.config.Config;

public aspect Load {
	
	
	pointcut preLoad3DObjects(Preload3DObjects preLoad3DObject, ARToolkit artoolkit) : call (@Preload3DObjects *  configure(ARToolkit))
		&& @annotation(preLoad3DObject) && args(artoolkit);
		
	pointcut preLoadBaseTextTexture(PreloadBaseTextTexture preloadBaseTextTexture, GL10 gl) : execution (@PreloadBaseTextTexture *  initGL(GL10))
		&& @annotation(preloadBaseTextTexture) && args(gl);
	
	pointcut configureActivity(ARToolkit artoolkit) : call (* JaguARActivity.configure(ARToolkit)) && args(artoolkit);

	after(Preload3DObjects preLoad3DObject, ARToolkit artoolkit) : preLoad3DObjects(preLoad3DObject, artoolkit){
		
		
		Log.w("AspectJ","Implementar lods para el precargado de objetos. bbb");
		
		
		RelationDAO rd = new RelationDAO();
		
		AssetManager am = Config.context.getResources().getAssets();

		List<String> o3ds = new ArrayList<String>();
		List<String> marks = new ArrayList<String>();

		try {
			String[] models = am.list("models");
			String[] root = am.list("");

			for (String m : models) {
				if (m.endsWith(".obj")) {
					o3ds.add(m);
				}
			}
			
			
			for (String a : root) {
				if (a.contains("patt")) {
					marks.add(a);
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
			Log.e("Load.aj", "Moder or Marker assets not found", e1);
		}
		
		List<RelationVO> lrd = rd.getAll();
		
		for (RelationVO r : lrd) {
			
			String markerName = "";
			String object3dName ="";
			int menuId = -1;

			if (r != null) {
				if (r.getMarker() != null) {
					markerName = r.getMarker().getName();
				}

				if (r.getObject3D() != null) {
					object3dName = r.getObject3D().getBaseFileName();
				}

				if (r.getMenu() != null) {
					menuId = (int) r.getMenu().getId();
					
				}

			}
			
			
			if(o3ds.contains(object3dName)){
				if(marks.contains(markerName)){
					
					marks.remove("markerName");
					
					ModelLoader ml = new ModelLoader(JaguARConstants.TYPE_INTERNAL, object3dName, markerName, menuId, artoolkit, 
							Config.context.getResources().getAssets());
							ml.execute();
				}
				
				
			}else {
				if(marks.contains(markerName)){
					marks.remove("markerName");
				
						try {
							this.artRegisterARObject(artoolkit, markerName, menuId);
						} catch (AndARException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
				
				
			}
			
			
			

		}
		
		
	
		
		/*
			TEMPORAL, para testing, no olvidar descomentar
			
			
			ModelLoader md = new ModelLoader(JaguARConstants.TYPE_INTERNAL, "android.obj", "patt.hiro", 
					artoolkit, Config.context.getResources().getAssets());
			
		md.execute();
		*/
		
	}
	
	
	after(PreloadBaseTextTexture preloadBaseTextTexture,GL10 gl) : preLoadBaseTextTexture(preloadBaseTextTexture,gl){
		
		Log.w("AspectJ","INITGL after");
		
		InitBaseTextTexture initBaseTextTexture = InitBaseTextTexture.getInstance();
		initBaseTextTexture.initTextures(gl);		
			
		Log.w("AspectJ","INITGL end after");
	}
	
	
	after(ARToolkit artoolkit) :configureActivity(artoolkit){

		Log.i("AspectLoad3D", "Entry");
/*
	DAO rdao = new RelationDAO();

		List<RelationVO> lrel = rdao.getAll();

		for (RelationVO rvo : lrel) {
			try {
				this.artRegisterARObject(artoolkit, rvo.getMarker().getName());
			} catch (AndARException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
*/
	}



private void artRegisterARObject(ARToolkit artoolkit, String patternName, int menuId) throws AndARException {
	
	BoxObject someObject = new BoxObject("default"+System.currentTimeMillis(), patternName, 80.0, new double[]{0,0});	
	someObject.setMenuId(menuId);
	
	artoolkit.registerARObject(someObject);
	
}

	
	
	
	
	

}


