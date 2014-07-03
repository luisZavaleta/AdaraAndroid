package lx.zv.business.facade.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andar.ARObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.hardware.SensorManager;
import android.os.Debug;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import lx.zv.exceptions.DivisionNumberException;
import lx.zv.exceptions.JaguARException;
import lx.zv.jaguar.annotations.MenuConfiguration;
import lx.zv.jaguar.business.menu.enums.Label;
import lx.zv.jaguar.business.menu.enums.Position;
import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.jaguar.business.menu.interfaces.IdentificableByMenu;
import lx.zv.jaguar.business.menu.util.CoordinatePosition;
import lx.zv.jaguar.business.opengl.menu.DrawMenuBackground;
import lx.zv.jaguar.business.opengl.menu.MenuText;
import lx.zv.jaguar.business.sensors.ImageOrientation;
import lx.zv.jaguar.config.Config;
import lx.zv.model.menu.MenuCurrentText;
import lx.zv.model.menu.bo.SideAndPercentages;
import lx.zv.model.persistence.dao.MenuDAO;
import lx.zv.model.persistence.vo.MenuTextVO;
import lx.zv.model.persistence.vo.sincronization.MenuVO;
import lx.zv.textures.InitBackgroundTexture;
import lx.zv.util.AdaraUtil;


public class MenuFacade implements Observer{
	
	//GL10 gl;
	MenuText menuText;
	ImageOrientation imageOrientation;
	Vector<ARObject> arObjects;
	boolean visible = false;
	
	long lastAccess = Long.MIN_VALUE;

	//SensorManager sm;
	
	InitBackgroundTexture init;
	DrawMenuBackground dt;
	
	ArrayList<ArrayList<String>> textos = new ArrayList<ArrayList<String>>();
//	ArrayList<String> text = new ArrayList<String>();
	//int actualObjId = -1;
	int[] clickCoords= new int[2]; 
	
	private boolean isMenuClicked = false;
	

	MenuDAO mdao  = new MenuDAO();
	

	

	ArrayList<String> textMainMenu = new ArrayList<String>();
	
	
	 
	public MenuFacade(Vector<ARObject> arObjects, DisplayMetrics displayMetrics, Observable obs){ 		
		obs.addObserver(this);
		//this.gl = gl;	
		this.arObjects = arObjects;
	
		//this.sm = sm; patt
		
		menuText = new MenuText(displayMetrics);
		imageOrientation = new ImageOrientation((SensorManager)Config.context.getSystemService(Activity.SENSOR_SERVICE));
		imageOrientation.registerOrientationListener();
		
		//init = InitBackgroundTexture.getInstance(gl, Config.context,1);	
		
		dt = new DrawMenuBackground();	

		//Para propositos de testing, estos datos van a venir despues de la base de datos.	
		
	}

	@MenuConfiguration
	(
			textSize = 20,	
			ARGBColor = {0xff,0x00,0x00,0x00},
			antiAlias = true,
			fontFamily = "normal",
			fontStyle = Typeface.NORMAL,
			widthPercentage = .9f,
			heightPercentage = .5f			
	)
	public void drawMenu(GL10 gl) throws JaguARException{
		
		Log.e("DrawMenu","drawMenu(GL10 gl) throws JaguARException");
		init = InitBackgroundTexture.getInstance(gl, Config.context,1);	
		gl.glPushMatrix();
			try {
				Log.e("DrawMenu","this.printMenu(gl);");
				this.printMenu(gl);
			} catch (DivisionNumberException e) {	
			//Mandar mensaje de error al usuario
				Log.e(this.getClass().toString(), "Error en el num de la textura", e);
			}

		gl.glPopMatrix();
		
		

	}

	
	
	
	
	public void printMenu(GL10 gl) throws DivisionNumberException, JaguARException{	

		Log.e("DrawMenu","printMenu(GL10 gl) throws DivisionNumberException");
		
		for (ARObject obj : arObjects) {

			if(obj.isVisible()){
				visible = true;
				
				if(!(obj instanceof IdentificableByMenu)){
					
					if(Config.DEBUG)
						Log.e("PrintMenu", "Can't get the Id for Menu, won't display anythin ");
					
					
					Log.e("DrawMenu","Log.e(PrintMenu, Can't get the Id for Menu, won't display anythin );");
					return;
					//throw new JaguARException(" ARObjects for JaguAnAr must implements IdentificableByMenu interface");
				}
				
				if(timeOut() || MenuCurrentText.actualObjId != obj.getId()){
					
					
					MenuCurrentText.actualObjId = obj.getId();	
					
					Log.e("DrawMenu","MenuCurrentText.actualObjId = obj.getId();--->"+obj.getId());
					
					IdentificableByMenu ibm = (IdentificableByMenu)obj;
					MenuCurrentText.menuId = ibm.getMenuId();
					 setCurrentText();
					/*List<MenuVO> mvl = mdao.getByParentId(MenuCurrentText.menuId);	
					if(mvl.size() > 0){					
					MenuCurrentText.setMenueVOList(mvl);
					}*/
				}
				

				
				
				
				this.printMenuProcess(gl);
				
				
				
				
				visible = false;
			
			}
		}

	}
	
	
	
	
	
	
	
	public void printMenuProcess(GL10 gl) throws DivisionNumberException{
		
		//ArrayList<String> textMenu = getCurrentMenu();
		
		Side s = imageOrientation.getOrientation();		
		SideAndPercentages sap = new SideAndPercentages();
		
		sap.side = s;
		sap.widthPercentage = .7f;
		sap.heightPercentage = .4f;
		
		
		//ArrayList<String> lsx = new ArrayList<String>();
		
		//for(MenuVO mv: MenuCurrentText.getMenueVOList()){
			//lsx.add(mv.getText());Hola
		//}
		
		
		
		if(!isMenuClicked){	
			//this.text = textMenu;
		}else{
			onClickMenu(sap,MenuCurrentText.getMenueVOList().size());
			isMenuClicked = false;
		}
		
		
		
		//Esto estaba causando el error
		if(MenuCurrentText.getMenueVOList() != null){
		
		init.selectTexture(MenuCurrentText.getMenueVOList().size(), s);
		
		//(GL10 gl, Side s, float verticalPercentage, float horizontalPercentage) 
			
		dt.drawSquareBackground(gl, sap);
		menuText.printTextoLite(gl,sap);
		
		}
	}
	
	protected void finalize(){
		imageOrientation.unregisterOrientationListener();
	}
	@Override
	public void update(Observable observable, Object data) {
		
		if(!(data instanceof int[])){
			throw new RuntimeException();
		}
		
		isMenuClicked = true;
		clickCoords = (int[])data;
		
	}
	
	/*

	@Override
	public void update(Observable observable, Object data) {
		
	
		
		
		if(!(data instanceof int[])){
			throw new RuntimeException();
		}
		

		//List<MenuVO> mvl = new ArrayList<MenuVO>();
		
		
		//ArrayList<String> textSubMenu = new ArrayList<String>();
		
	
		//MenuVO mvo = new MenuVO();
		//mvo.setText("mexico");
		
		//MenuVO mvo1 = new MenuVO();
		//mvo1.setText("lindo");
		
		//MenuVO mvo2 = new MenuVO();
		//mvo2.setText("y querido si muero");
		
		//mvl.add(mvo);
		//mvl.add(mvo1);
		//mvl.add(mvo);
		//mvl.add(mvo1);
		//mvl.add(mvo);
		//mvl.add(mvo1);
		//mvl.add(mvo2);
		
		
		//MenuCurrentText.setMenueVOList(mvl);
		
		//text = textSubMenu; 

		
		
		//int[] coords = 
		
		isMenuClicked = true;
		clickCoords = (int[])data;
		
		
	//	Log.d("TESTING COORDS", coords[0]+"<------>"+coords[1]);
		//Side s = imageOrientation.getOrientation();
		//SideAndPercentages sap = new SideAndPercentages();
		
		//sap.side = s;
		//sap.widthPercentage = .7f;
		//sap.heightPercentage = .4f;
	}
		
	
	*/	
	ArrayList<String> lsx = new ArrayList<String>();	


	
	private void onClickMenu(SideAndPercentages sap, int size){
		
		
		Label label = AdaraUtil.getSelectedMenu(clickCoords[0], clickCoords[1], sap, 0);
		
		if(label != null){
		switch(label){
		case FIRST:
			MenuCurrentText.menuId = getIdFromParent(0);
			break;
		case SECOND:
			MenuCurrentText.menuId = getIdFromParent(1);
			break;
		case THIRD:
			MenuCurrentText.menuId = getIdFromParent(2);
			break;
		case FOURTH:
			MenuCurrentText.menuId = getIdFromParent(3);
			break;
		case FIFTH:
			MenuCurrentText.menuId = getIdFromParent(4);
			break;
		case SIXTH:
			MenuCurrentText.menuId = getIdFromParent(5);
			break;
		}
	}
	}
	

	
	private long getIdFromParent(int id){
		Log.i("MenuFacadex", "position---->"+id);
		try{
			long idr =  MenuCurrentText.getMenueVOList().get(id).getId();
			
			Log.i("MenuFacadex", "idr---->"+idr);
			 setCurrentText();
			return	idr;	
			
			
		}catch(Exception ex){
			Log.i("MenuFacadex", "exception---->"+id);
			return id;
		}
		
		
	}
	
	private void  setCurrentText(){
		
		List<MenuVO> mvl = mdao.getByParentId(MenuCurrentText.menuId);
		
		
		Log.i("PrintMenu",""+MenuCurrentText.menuId);
		//Log.i("PrintMenu",mvl.size() + mvl.get(0).getText());
		
		
		
		if(mvl!= null && mvl.size() > 0){					
		MenuCurrentText.setMenueVOList(mvl);
		}
	}
	
	
	private boolean timeOut(){
		
		boolean ret = false;
		
		
		long lxa = lastAccess + 750;
	
		if(lxa  < System.currentTimeMillis() ){
			ret = true;
			MenuCurrentText.menuId = 1;
			Log.i("LA", "true");
		}else{
			Log.i("LA", "false");
			
		}
	
		
		lastAccess = System.currentTimeMillis();
		
		Log.i("LA", "lastAccess---->"+lastAccess);
		Log.i("LA", "ret---->"+ret);
		
		return ret;
		
	}
	
	
}
