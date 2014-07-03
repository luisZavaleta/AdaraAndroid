package lx.zv.jaguar.business.opengl.menu;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import lx.zv.exceptions.JaguARException;
import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.jaguar.business.menu.util.CoordinatePosition;
import lx.zv.jaguar.config.Config;
import lx.zv.model.menu.bo.JagARPadding;
import lx.zv.model.menu.bo.JagArMenuListVO;
import lx.zv.model.menu.bo.SideAndPercentages;
import lx.zv.model.persistence.bo.texture.MenuMetrics;
import lx.zv.model.persistence.bo.texture.TextCoordinates;
import lx.zv.model.persistence.bo.texture.TextureVO;
import lx.zv.model.persistence.vo.sincronization.MenuVO;
import lx.zv.textures.TexTexturePool;
import lx.zv.textures.labelMArker.LabelMaker;
import lx.zv.util.AdaraUtil;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class MenuText {
	
	private LabelMaker mLabels;
	private Paint mLabelPaint;
	private int mLabelA;

	
	DisplayMetrics dm; 
	
	CoordinatePosition coop; 
	
	TexTexturePool ttp;
	
	float textHeight;
	
	//public	MenuText(GL10 gl, WindowManager wm){
	public	MenuText(DisplayMetrics displayMetrics){
		
		
		//si modificamos el paint eliminamos todas las texturas, valores precaulculados, etc (por que basicamente ya no nos servirían :))
		mLabelPaint = new Paint();
		mLabelPaint.setTextSize(20);
		mLabelPaint.setAntiAlias(true);
        mLabelPaint.setARGB(0xff, 0x00, 0x00, 0x00);

        
      
        mLabels = new LabelMaker(true, 1024, 1024);

 
        //-dm = new DisplayMetrics();		
		//-wm.getDefaultDisplay().getMetrics(dm);
        
        dm = displayMetrics;
  
	
		textHeight = -mLabelPaint.getFontMetrics().ascent + mLabelPaint.getFontMetrics().descent;
       
        ttp = new   TexTexturePool();     
  
		
	}
	
	
	
	
//
	
	//Imprime texto
	
	
	
	
	public void printTextoLite(GL10 gl, SideAndPercentages sap){
		

		printTextoLite(gl, AdaraUtil.getCurrentMenu(), sap);
		
	}
	
	
	public void printTextoLite(GL10 gl, ArrayList<String> texto, SideAndPercentages sap){
		if(coop == null){
	        mLabels.initialize(gl);
	        coop = new CoordinatePosition(dm,textHeight, sap.heightPercentage, sap.widthPercentage);
		}
		

		float menuWidth = coop.getMenuWidth(sap.side); 
		float menuHeight = coop.getMenuHeight(sap.side);
		
		TextureVO tvo = new TextureVO();
		float textHeight = -mLabelPaint.ascent() + mLabelPaint.descent();
		
		ArrayList<TextCoordinates> altc;
		
		try {
			
			JagArMenuListVO jamla = new JagArMenuListVO();
			
			jamla.paint = mLabelPaint;
			jamla.txts = texto;
			jamla.initX = (int)((coop.getTotalWidth(sap.side) - coop.getMenuWidth(sap.side))/2);//(int)(coop.getMenuWidth(side)*((1-horizontalPercentage)/2));
			jamla.initY = 0;
			jamla.menuWidth = menuWidth;
			jamla.menuHeight = menuHeight;
			jamla.textHeight = textHeight;
			JagARPadding jap = new JagARPadding();
			jap.right = 4;
			jap.left = 4;
			jap.top = 4;
			jap.bottom = 4;
			jamla.jarPadding = jap;
			jamla.spaceBetweenLines = 5;
			
			MenuMetrics mm = coop.getMenuData(jamla); 
			
			altc = mm.textCoordinates; 
			
			for(TextCoordinates tc : altc){
				float[] wa =coop.getRelativeCoordinates(sap.side, tc.x, tc.y);
				float xa = wa[0];			
				float ya = wa[1];
				tvo.text = tc.text; 
				tvo.side =sap.side;
				
				if(!ttp.isTextureAvailable(tvo)){
	            
	            	mLabels.beginAdding(gl);
	            	mLabelA = mLabels.add(gl,tc.text , mLabelPaint,sap.side,ttp,false);
	            	mLabels.endAdding(gl,tc.text,sap.side);
	         
				
				}else{        	 
				
	               mLabelA = ttp.getTextureId(tvo);
	        	   
	           }
				
				
				mLabels.beginDrawing(gl, 1024, 1024, tc.text, sap.side);
				mLabels.draw(gl, xa,ya, mLabelA);
				mLabels.endDrawing(gl);	    	
				gl.glEnable(GL10.GL_LIGHTING);
				
					}
		     
			} catch (JaguARException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public float getTextHeight(){
		return textHeight;
	}


}
