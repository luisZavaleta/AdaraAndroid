package lx.zv.model.objects3d;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

import lx.zv.exceptions.JaguARRuntimeException;
import lx.zv.jaguar.config.Config;
import lx.zv.util.AdaraUtil;
//import lx.zv.exceptions.JagARException;
import android.app.AlertDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Debug;
import android.os.Environment;
import android.util.Log;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.exceptions.AndARException;
import edu.dhbw.andobjviewer.graphics.Model3D;
import edu.dhbw.andobjviewer.models.Model;
import edu.dhbw.andobjviewer.parser.ObjParser;
import edu.dhbw.andobjviewer.parser.ParseException;
import edu.dhbw.andobjviewer.parser.Util;
import edu.dhbw.andobjviewer.util.AssetsFileUtil;
import edu.dhbw.andobjviewer.util.BaseFileUtil;
import edu.dhbw.andobjviewer.util.SDCardFileUtil;

 public class ModelLoader extends AsyncTask<Void, Integer, Model> {	
	 


		int type = JaguARConstants.TYPE_INTERNAL;
		String modelFileName;
		String patternName;
		ARToolkit artoolkit;
		AssetManager assetManager;
		int menuId;
		
		Model3D model3D;
	 
	 
	public ModelLoader(int type, String modelFileName,String patternName, int menuId,ARToolkit artoolkit, AssetManager assetManager){
		this.type = type;
		this.modelFileName = modelFileName;
		this.artoolkit = artoolkit;
		this.assetManager = assetManager;
		this.patternName = patternName;
		this.menuId = menuId;
		 
	 }
	 



	@Override
	protected Model doInBackground(Void... params) {
		
		Log.i("ADARA", "iniciando");

		Model model = null;
		

		
		BaseFileUtil fileUtil= null;
		File modelFile=null;
		
	
		switch(type) {
		case JaguARConstants.TYPE_EXTERNAL:
			fileUtil = new SDCardFileUtil();
			modelFile =  new File(URI.create(modelFileName));
			modelFileName = modelFile.getName();
			fileUtil.setBaseFolder(modelFile.getParentFile().getAbsolutePath());
			break;
		case JaguARConstants.TYPE_INTERNAL:
			fileUtil = new AssetsFileUtil(assetManager);
			fileUtil.setBaseFolder("models/");
			break;
		}
		
		//read the model file:						
		if(modelFileName.endsWith(".obj")) {
			ObjParser parser = new ObjParser(fileUtil);
			try {
				
				/*if(Config.DEBUG)
					Debug.startMethodTracing("AndObjViewer");*/
				if(type == JaguARConstants.TYPE_EXTERNAL) {
					//an external file might be trimmed
					BufferedReader modelFileReader = new BufferedReader(new FileReader(modelFile));
					String shebang = modelFileReader.readLine();				
					if(!shebang.equals("#trimmed")) {
						//trim the file:			
						File trimmedFile = new File(modelFile.getAbsolutePath()+".tmp");
						BufferedWriter trimmedFileWriter = new BufferedWriter(new FileWriter(trimmedFile));
						Util.trim(modelFileReader, trimmedFileWriter);
						if(modelFile.delete()) {
							trimmedFile.renameTo(modelFile);
						}					
					}
				}
				if(fileUtil != null) {
					BufferedReader fileReader = fileUtil.getReaderFromName(modelFileName);
					if(fileReader != null) {
						model = parser.parse("Model", fileReader);
						model3D = new Model3D(model, patternName);
						
						model3D.setMenuId(menuId);
				//	 m3d = new Model3D(model);
						this.onPostExecute(model);
					 return model;
					 
						//JaguARActivity.models3d.add(mdl3d);
					}
				}
				/*if(Config.DEBUG)
					Debug.stopMethodTracing();*/
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		Log.i("ADARA", "iniciando");
		return null;
	}
	
	
	
	@Override
	protected void onPostExecute(Model model) {
		//super.onPostExecute(model);

		
		//register model
		try {
			if(model3D!=null){
				artoolkit.registerARObject(model3D);
				}
			Log.i("ADARA", "objecto 3D registrado");
		} catch (AndARException e) {
			Log.i("ADARA", "objecto 3D error");
			e.printStackTrace();
		}
		
	/*	
		AlertDialog alertDialog;
		alertDialog = new AlertDialog.Builder(Config.context).create();
		alertDialog.setTitle("Objectos creados");
		alertDialog.setMessage("Se crearon los objetos");
		alertDialog.show();
		*/
		//waitDialog.dismiss();
		//draw
		//startPreview();
	}


}