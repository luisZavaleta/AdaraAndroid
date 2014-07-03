package lx.zv.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andar.ARObject;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Debug;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import lx.zv.exceptions.DivisionNumberException;
import lx.zv.exceptions.JaguARException;
import lx.zv.jaguar.business.menu.enums.Position;
import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.jaguar.business.menu.interfaces.IdentificableByMenu;
import lx.zv.jaguar.business.menu.util.CoordinatePosition;
import lx.zv.jaguar.business.opengl.menu.DrawMenuBackground;
import lx.zv.jaguar.business.opengl.menu.MenuText;
import lx.zv.jaguar.business.sensors.ImageOrientation;
import lx.zv.model.menu.bo.SideAndPercentages;
import lx.zv.model.persistence.dao.MenuDAO;
import lx.zv.model.persistence.vo.MenuTextVO;
import lx.zv.textures.InitBackgroundTexture;


public class MenuFacade implements Observer{
	
	GL10 gl;
	MenuText mt;
	ImageOrientation cio;
	Context ctx;
	Resources res;
	Vector<ARObject> aro;

	SensorManager sm;
	
	InitBackgroundTexture init;
	DrawMenuBackground dt;
	
	ArrayList<ArrayList<String>> textos = new ArrayList<ArrayList<String>>();
	ArrayList<String> text = new ArrayList<String>();
	
	
	private boolean isMenuClicked = false;
	

	MenuDAO mtdao;
	

	

	//ArrayList<String> textMainMenu = new ArrayList<String>();
	
	
	
	public MenuFacade(GL10 gl, Context ctx, Resources res, Vector<ARObject> aro, WindowManager wm,SensorManager sm, Observable obs){
		
		
		
		 
		obs.addObserver(this);
		this.gl = gl;
		this.ctx = ctx;
		this.res = res;
		this.aro = aro;
	
		this.sm = sm;
		
	//	mt = new MenuText(gl, wm);
		cio = new ImageOrientation(sm);
		cio.registerOrientationListener();
		
		init = InitBackgroundTexture.getInstance(gl, ctx,1);	
		
		
		dt = new DrawMenuBackground();	
		
		mtdao = new MenuDAO();
		
		//Para propositos de esting, estos datos van a venir despues de la base de datos.
	
		
		
		
		
		
	}

	public void drawMenu() throws JaguARException{
	

		gl.glPushMatrix();
			//cio = new ChangeImageOrientation(sm,gl, ctx, aro);	
			//cio.registerOrientationListener();
		
		//Side s = cio.getOrientation();
		
	try {


		
		
		this.printMenu();
		
		


	} catch (DivisionNumberException e) {	
		//Mandar mensaje de error al usuario
	Log.e(this.getClass().toString(), "Error en el num de la textura", e);
	}
	
		gl.glPopMatrix();
		
		 
	
		
		
	}
	
	
	public void printMenu() throws DivisionNumberException, JaguARException{	

		for (ARObject obj : aro) {
			
			
			
			/*
			if(obj.getPatternName().equals("patt.hiro")){
				textMainMenu.clear();
				textMainMenu.add("UNO Luis Zavaleta Vazquez");
				textMainMenu.add("DOS  Luis Zavaleta Vazquez");
				textMainMenu.add("TRES Luis Zavaleta Vazquez");
				textMainMenu.add("CUATRO Luis Zavaleta Vazquez");
				textMainMenu.add("CINCO Luis Zavaleta Vazquez");
				textMainMenu.add("SEIS Luis Zavaleta Vazquez");
						
					}else if(obj.getPatternName().equals("barcode.patt")){
						
				textMainMenu.clear();
				textMainMenu.add("mexico");
				textMainMenu.add("lindo");
				textMainMenu.add("y queeeeeeeeerido");
					}
			
			*/
			
			
			if(obj.isVisible()){				
				
				if(!(obj instanceof IdentificableByMenu)){
					throw new JaguARException(" ARObjects for JaguAnAr must implements IdentificableByMenu interface");
				}
				
				
				IdentificableByMenu ibm = (IdentificableByMenu)obj;
				
				
				//v--> SE MODIFICO ESTA PARTE, VERIFICAR EL FUINCIONAMIENTO
			//v-->	List<MenuTextVO> mtText = mtdao.getMenuesByIdMenu(ibm.getMenuId());
				
				ArrayList<String> lsx = new ArrayList<String>();
				
				//v--> for(MenuTextVO mtvo: mtText){
					//v--> lsx.add(mtvo.getTexto());
				//v--> }
				
				
				this.printMenuProcess(lsx);
			
			}
		}

	}
	
	
	
	public void printMenuProcess(ArrayList<String> textMenu) throws DivisionNumberException{
		Side s = cio.getOrientation();
		
		SideAndPercentages sap = new SideAndPercentages();
		
		sap.side = s;
		sap.widthPercentage = .7f;
		sap.heightPercentage = .4f;
		
		if(!isMenuClicked){	
			this.text = textMenu;
		}else{
			isMenuClicked = false;
		}
		init.selectTexture(text.size(), s);
		
		//(GL10 gl, Side s, float verticalPercentage, float horizontalPercentage) 
			
		dt.drawSquareBackground(gl, sap);
		//mt.printTextoLite(text,sap);
	}
	
	protected void finalize(){
		cio.unregisterOrientationListener();
	}

	@Override
	public void update(Observable observable, Object data) {
		
		if(!(data instanceof int[])){
			throw new RuntimeException();
		}
		

		ArrayList<String> textSubMenu = new ArrayList<String>();
		
	
		
		textSubMenu.add("mexico");
		textSubMenu.add("lindo");
		textSubMenu.add("y queeeeeeeeerido");
		
		text = textSubMenu; 

		
		
		int[] coords = (int[])data;
		isMenuClicked = true;
		
		
		Log.d("TESTING COORDS: ", coords.toString());
	
		
	}

	
}
