package lx.zv.util;

import java.util.ArrayList;

import lx.zv.jaguar.business.menu.enums.Label;
import lx.zv.jaguar.business.menu.enums.Position;
import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.jaguar.business.menu.util.CoordinatePosition;
import lx.zv.jaguar.config.Config;
import lx.zv.model.menu.MenuCurrentText;
import lx.zv.model.menu.bo.SideAndPercentages;
import lx.zv.model.persistence.vo.sincronization.MenuVO;
import android.app.AlertDialog;
import android.util.Log;

public class AdaraUtil {

	CoordinatePosition coop; 
	
	
	
	
	public static void showSimpleMessage(String title, String message){
		
		AlertDialog.Builder alert = new AlertDialog.Builder(Config.thiz);
    	alert.setTitle(title);
    	alert.setMessage(message);
    	alert.show();
		
	}
	
	
	public static Label getSelectedMenu(float x, float y, SideAndPercentages sap, int textSize){
		
		float[] relativeCoordinates = getRelativeCoordinates(sap.side,x,y);
		Log.i("SelectedMenu", "Side-------->"+sap.side);
		Log.i("SelectedMenu", "Original---->X="+x+ ",    Y="+y);
		Log.i("SelectedMenu", "New--------->X="+relativeCoordinates[0]+ ",    Y="+relativeCoordinates[1]);
		
		int[][] mc =AdaraUtil.getMenuCoordenates(sap);
		
		Log.i("MenuCoord", "[0][0]--->"+mc[0][0]);
		Log.i("MenuCoord", "[0][1]--->"+mc[0][1]);
		Log.i("MenuCoord", "[1][0]--->"+mc[1][0]);
		Log.i("MenuCoord", "[1][1]--->"+mc[1][1]);
		
	Label l =	AdaraUtil.getLabel((int)relativeCoordinates[0],(int)relativeCoordinates[1],mc,4);
	
	Log.i("SelectLabel","---->"+l);
	
		
		return l;
	}
	
	
	private static float[] getRelativeCoordinates(Side side,float x, float y){
		
		
		
		float[] relativeCoordinates= new float[2];
		
		float relX=0, relY=0;
		
		switch(side){
		case TOP:
			relX = Config.displayMetrics.heightPixels  -  y;
			relY =  x;
			break;
		case BOTTOM:	
			relX = y;
			relY = Config.displayMetrics.widthPixels - x;
			break;
		case LEFT:
			relX = x;
			relY = y;
			break;
		case RIGHT:
			relX = Config.displayMetrics.widthPixels - x;
			relY = Config.displayMetrics.heightPixels  - y;
		
		}
		
		
		relativeCoordinates[0] = relX;
		relativeCoordinates[1] = relY;
		
		return relativeCoordinates;
		
	}
	
	
	private static int[][] getMenuCoordenates(SideAndPercentages sap){

		Side s = sap.side;
		
		switch(s){
		case TOP:
		case BOTTOM:
			
			int x1 = (int) (Config.displayMetrics.widthPixels -(Config.displayMetrics.widthPixels*sap.heightPercentage));
			int x2 = Config.displayMetrics.widthPixels;
			
			int libre = (int) ((Config.displayMetrics.heightPixels -(Config.displayMetrics.heightPixels*sap.widthPercentage))/2);
			
			int y1 = libre;
			int y2 = Config.displayMetrics.heightPixels - libre;
			
			int[][] ret= new int[2][2];
			ret[0][0] = x1;
			ret[0][1] = x2;
			ret[1][0] = y1;
			ret[1][1] = y2;
			
			
			return ret;
			
		case RIGHT:
		case LEFT:
			int xa1 = (int) (Config.displayMetrics.heightPixels -(Config.displayMetrics.heightPixels*sap.heightPercentage));
			int xa2 = Config.displayMetrics.heightPixels;
			
			int librea = (int) ((Config.displayMetrics.widthPixels -(Config.displayMetrics.widthPixels*sap.widthPercentage))/2);
			
			int ya1 = librea;
			int ya2 = Config.displayMetrics.widthPixels - librea;
			
			int[][] reta= new int[2][2];
			reta[0][0] = xa1;
			reta[0][1] = xa2;
			reta[1][0] = ya1;
			reta[1][1] = ya2;
			
			return reta;
			
			default: return null;
		}
		
	}
	
	
	
	public static Label getLabel(int x, int y, int[][] coord,int div){
		
		int ymid = coord[0][0] +((coord[0][1] - coord[0][0])/2);
		
		int xmid = coord[1][0] +((coord[1][1] - coord[1][0])/2);
		
		int onetd = ((coord[1][1] - coord[1][0])/3);
		
		int onethird  = coord[1][0] + onetd;
		int twothird  = coord[1][0] +(onetd*2);
		
		
		
		switch(div){
		case 1:
			Log.i("SelectLabel","!!!!!!!!!!!!!!!!!1");
		Log.i("SelectLabel","x-------->"+x);
		Log.i("SelectLabel","y-------->"+y);
		Log.i("SelectLabel","[1][0]--->"+coord[1][0]);
		Log.i("SelectLabel","[1][1]--->"+coord[1][1]);
		Log.i("SelectLabel","[0][0]--->"+coord[0][0]);
		Log.i("SelectLabel","[0][1]--->"+coord[0][1]);
		Log.i("SelectLabel","¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡");
		
			
			if(x > coord[1][0] && x < coord[1][1] && y > coord[0][0] && y < coord[0][1]){
				return Label.FIRST;
			}
			break;
		case 2:
			if(x > coord[1][0] && x < coord[1][1] && y > coord[0][0] && y < ymid){
				return Label.FIRST;
			}	
			if(x > coord[1][0] && x < coord[1][1] && y > ymid && y < coord[0][1]){
				return Label.SECOND;
			}
			break;
		case 3:
		case 4:
			if(x > coord[1][0] && x < xmid && y > coord[0][0] && y < ymid){
				return Label.FIRST;
			}
			if(x > coord[1][0] && x < xmid && y > ymid && y < coord[0][1]){
				return Label.SECOND;
			}
			if(x > xmid && x < coord[1][1] && y > coord[0][0] && y < ymid){
				return Label.THIRD;
			}
			if(x > xmid && x < coord[1][1] && y > ymid && y < coord[0][1]){
				return Label.FOURTH;
			}
			
			
			break;
		case 5:
		case 6:
			if(x > coord[1][0] && x < onethird && y > coord[0][0] && y < ymid){
				return Label.FIRST;
			}
			if(x > coord[1][0] && x < onethird && y > ymid && y < coord[0][1]){
				return Label.SECOND;
			}
			if(x > onethird && x < twothird && y > coord[0][0] && y < ymid){
				return Label.THIRD;
			}
			if(x > onethird && x < twothird && y > ymid && y < coord[0][1]){
				return Label.FOURTH;
			}
			if(x > twothird && x < coord[1][1] && y > coord[0][0] && y < ymid){
				return Label.FIFTH;
			}
			if(x > twothird && x < coord[1][1] && y > ymid && y < coord[0][1]){
				return Label.SIXTH;
			}
			
			break; 
			default: Log.i("SelectLabel","no label selected");

			//throw  new IllegalArgumentException("");
		
		
		}
		
		
		
		
		
		
		
		return null;
	}
	
	
	
	public static ArrayList<String> getCurrentMenu(){
		
		ArrayList<String> lsx = new ArrayList<String>();	
		
		for(MenuVO mv: MenuCurrentText.getMenueVOList()){
			lsx.add(mv.getText());
		}
		
		return lsx;
		
	}
	
	
	
}
