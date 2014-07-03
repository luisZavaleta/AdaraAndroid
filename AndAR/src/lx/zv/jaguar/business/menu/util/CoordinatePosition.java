package lx.zv.jaguar.business.menu.util;

import java.util.ArrayList;
import java.util.Collections;

import lx.zv.exceptions.JaguARException;
import lx.zv.jaguar.business.menu.enums.Label;
import lx.zv.jaguar.business.menu.enums.Position;
import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.model.menu.bo.JagArMenuListVO;
import lx.zv.model.persistence.bo.texture.MenuMetrics;
import lx.zv.model.persistence.bo.texture.TextCoordinates;
import android.graphics.Paint;
import android.util.DisplayMetrics;

public class CoordinatePosition {
private DisplayMetrics dm;


private boolean resetValues = true;	

//private int topX, bottomX = 0, leftX = 0, rightX= 0, topY = 0, bottomY, leftY, rightY;
private int totalWidth, totalHeight;

private float textHeight;
private float horizontalPercentage;
private float verticalPercentage;


public CoordinatePosition(DisplayMetrics dm, float textHeight,float verticalPercentage, float horizontalPercentage ){

		this.dm = dm;	
		
		
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		
		totalWidth = screenWidth;
		totalHeight = screenHeight;
		this.textHeight = textHeight;
		
		this.horizontalPercentage = horizontalPercentage;
		this.verticalPercentage = verticalPercentage;

		
	}


	
	public float[] getRelativeCoordinates(Side side,float x, float y){
		
		float[] relativeCoordinates= new float[2];
		
		float relX=0, relY=0;
		
		switch(side){
		case TOP:
			relX =  totalWidth -  y - textHeight;
			relY =  x;
			break;
		case BOTTOM:	
			relX = y;
			relY = totalHeight - x;
			break;
		case LEFT:
			relX = x;
			relY = y;
			break;
		case RIGHT:
			relX =totalWidth - x;
			relY = totalHeight - y  - textHeight;
		
		}
		
		
		relativeCoordinates[0] = relX;
		relativeCoordinates[1] = relY;
		
		return relativeCoordinates;
		
	}

	public float getMenuWidth(Position pos){		
		if(Position.VERTICAL.equals(pos)){
			return totalHeight;
		}else{
			return totalWidth;	
		}		
	}
	
	/*
	 * @return menu height in pixels
	 * */
	public float getMenuHeight(Position pos){		
		if(Position.VERTICAL.equals(pos)){
			return totalWidth;
		}else{
			return totalHeight;
		}		
	}

	/*
	 * @return menu height in pixels
	 * */
	public float getMenuWidth(Side side){
		if(Side.BOTTOM.equals(side) || Side.TOP.equals(side)){
			return 	getMenuWidth(Position.VERTICAL)  * horizontalPercentage;
		}else{
			return 	getMenuWidth(Position.HORIZONTAL)  * horizontalPercentage;	
		}
	}
	
	
	
	/*
	 * @return menu height in pixels
	 * */
	public float getMenuHeight(Side side){
		if(Side.BOTTOM.equals(side) || Side.TOP.equals(side)){
			return 	getMenuHeight(Position.VERTICAL)  * verticalPercentage;
		}else{
			return 	getMenuHeight(Position.HORIZONTAL)  * verticalPercentage;	
		}
	}
	
	
	
	public float getTotalWidth(Side side){
		if(Side.BOTTOM.equals(side) || Side.TOP.equals(side)){
			return 	getMenuWidth(Position.VERTICAL);
		}else{
			return 	getMenuWidth(Position.HORIZONTAL);	
		}
	}
	
	
	public float getTotalHeight(Side side){
		if(Side.BOTTOM.equals(side) || Side.TOP.equals(side)){
			return 	getMenuWidth(Position.VERTICAL);
		}else{
			return 	getMenuWidth(Position.HORIZONTAL);	
		}
	}
	

	
	public MenuMetrics getMenuData(JagArMenuListVO jamla) throws JaguARException{
		
		
		
		MenuMetricsPool cpp = new MenuMetricsPool();
		
		if(!cpp.exists(jamla)){

		MenuMetrics mm = this.getBeginPositions(jamla.initX, jamla.initY, jamla.txts.size(), jamla.menuWidth, jamla.menuHeight);
	
		ArrayList<float[]> positions =	mm.position;
		
		ArrayList<TextCoordinates>  tc = new ArrayList<TextCoordinates>();
		mm.textCoordinates = tc;
	
		if(positions.size() < jamla.txts.size()){	
		//this should never happen
		throw new JaguARException("Crazy error XD size of positions and strings are not equal");		
		}	
	
	
		for(int a = 0; a<jamla.txts.size(); a++){
		
			String txt = jamla.txts.get(a);
			float[] pos = positions.get(a);
		
			ArrayList<TextCoordinates>  tcs = this.getMenuData(jamla.paint, txt, pos[0], pos[1], mm.widthSize, mm.heightSize, textHeight, 
					jamla.jarPadding.right, jamla.jarPadding.left, jamla.jarPadding.top, jamla.jarPadding.bottom, jamla.spaceBetweenLines);
					tc.addAll(tcs);
		
	}
		cpp.setMenuMetric(jamla, mm);
		return mm;
		
		}else{
			
			return cpp.getMenuMetrics(jamla);
		}
		
		
		
	}
	
	
	
	
	
	public ArrayList<TextCoordinates> getMenuData(Paint paint,String txt, float initX, float initY, float menuWidth, float menuHeight, float textHeight, 
			int paddingRight, int paddingLeft, int paddingTop, int paddingBottom, int spaceBetweenLines) throws JaguARException{
		
		
		float availableWidth = menuWidth - paddingLeft - paddingRight;
		//int avalilableHeight = lableHeight - paddingTop - paddingBottom;
		
		ArrayList<TextCoordinates> textCoord = new ArrayList<TextCoordinates>();
		
		
		
		// float textWidth = paint.measureText(txt);
		 
		 float x = 0;
		 float y = 0;
		 
		 
		 ArrayList<String> textLines = new ArrayList<String>();
		 
		 this.getLineTextAndResidualText(paint, txt, availableWidth, textLines);
		 
		 float totalHeight = (textLines.size() * textHeight) + ((textLines.size()-1) * spaceBetweenLines);
		 
		 if(totalHeight > (menuHeight-paddingTop- paddingBottom)){
			 throw new JaguARException("The text does not fit in the menu space reserved for it");
		 }
		 
		float residualSpace = menuHeight - totalHeight;
		 
		
		x = initX + paddingRight;
		y = initY + (residualSpace/2);
		
		Collections.reverse(textLines);
		
		for(String line: textLines){
			TextCoordinates tc = new TextCoordinates();
			tc.text = line;
			tc.x = x;
			tc.y = y;
			
			textCoord.add(tc);
			
			y += textHeight + spaceBetweenLines;

		}
	
		 
		return textCoord;
		
		
		
	}
	
	
	
	public MenuMetrics  getBeginPositions(float beginX, float beginY, int divNumber, float menuWidth, float menuHeight){
		
		
		MenuMetrics mm = new MenuMetrics();
		ArrayList<float[]>  beginPositions = new ArrayList<float[]>();
		
		mm.position = beginPositions;

		
		
		
		float[] pos;
		
		
		
		switch(divNumber){
		case 1:
			mm.heightSize = menuHeight;
			mm.widthSize = menuWidth;
			pos = new float[2];
			pos[0] = beginX;
			pos[1] = beginY;
			beginPositions.add(pos);
			break;
		case 2:
			mm.heightSize = menuHeight/2;
			mm.widthSize = menuWidth;
			pos = new float[2];
			pos[0] = beginX;
			pos[1] = beginY + (menuHeight/2);
			beginPositions.add(pos);
			pos = new float[2];
			pos[0] = beginX;
			pos[1] = beginY;
			beginPositions.add(pos);
			break;
		case 3:
		case 4:
			mm.heightSize = menuHeight/2;
			mm.widthSize = menuWidth/2;
			pos = new float[2];
			pos[0] = beginX;
			pos[1] = beginY + (menuHeight/2);
			beginPositions.add(pos);
			pos = new float[2];
			pos[0] = beginX;
			pos[1] = beginY;
			beginPositions.add(pos);
			
			pos = new float[2];
			pos[0] = beginX +(menuWidth/2);
			pos[1] = beginY + (menuHeight/2);
			beginPositions.add(pos);
			pos = new float[2];
			pos[0] = beginX +(menuWidth/2);;
			pos[1] = beginY;
			beginPositions.add(pos);
			break;
		case 5:
		case 6:
			mm.heightSize = menuHeight/2;
			mm.widthSize = menuWidth/3;
			pos = new float[2];
			pos[0] = beginX;
			pos[1] = beginY + (menuHeight/2);
			beginPositions.add(pos);
			pos = new float[2];
			pos[0] = beginX;
			pos[1] = beginY;
			beginPositions.add(pos);
			
			
			pos = new float[2];
			pos[0] = beginX +(menuWidth/3);
			pos[1] = beginY + (menuHeight/2);
			beginPositions.add(pos);
			pos = new float[2];
			pos[0] =  beginX +(menuWidth/3);
			pos[1] = beginY;
			beginPositions.add(pos);
			
			
			pos = new float[2];
			pos[0] = beginX +((menuWidth/3)*2);
			pos[1] = beginY + (menuHeight/2);
			beginPositions.add(pos);
			pos = new float[2];
			pos[0] = beginX +((menuWidth/3)*2);
			pos[1] = beginY;
			beginPositions.add(pos);
			

		}
		
		return mm;
		
	}
	
	
	
	

	private void getLineTextAndResidualText(Paint paint, String completeText, float availableWidth, ArrayList<String> textLines ){
		
// BUG: cuando una palabra ocupa mas espacio que el reservado para el menú
		 int maxCharNumber = paint.breakText(completeText, true, availableWidth, null);
		 float textWidth = paint.measureText(completeText);
		
		String oneLineText = "";
		String residualText = "";
		
		String[] texts = {oneLineText, residualText};
		
		boolean add = true;
		if(textWidth < availableWidth ){	
			oneLineText = completeText;			 
		 }else{

		char c = completeText.charAt(maxCharNumber);
		
		String subText = completeText.substring(0, maxCharNumber);
		

		
		if(c == ' '){
			oneLineText = subText;
			residualText = completeText.substring(maxCharNumber);
		}else{
			
			int lastIndexOfSpace = subText.lastIndexOf(' ');
			
			if(lastIndexOfSpace == -1){
				
				//Bug corregir,  aquí XD
				String tempSub = subText;
				
				subText = subText.substring(0, tempSub.length()/2) + " " + subText.substring(tempSub.length()/2, tempSub.length());
				
				add = false;
				
				oneLineText = "";
				residualText = subText;
			
			}else{
				oneLineText = subText.substring(0, lastIndexOfSpace);
				residualText = completeText.substring(lastIndexOfSpace);
			}
			
			

		}

		 }
		if(add){
			textLines.add(oneLineText);
		}
		
		if(!residualText.equals("")){
			this.getLineTextAndResidualText(paint, residualText, availableWidth, textLines);
		}		
	}
	
	
	public Label getLabel(int x, int y, Side side, MenuMetrics mm){
		
		ArrayList<TextCoordinates> textCoordinates = mm.textCoordinates;
		
		float[] relCoord =	this.getRelativeCoordinates(side, x, y);
		
		
		float  margin = ((this.getTotalWidth(side) - this.getMenuWidth(side))/2);
		
	if(relCoord[0] < margin || relCoord[0] > margin + this.getMenuWidth(side) || relCoord[1] < 0 || relCoord[1] > 0+this.getMenuHeight(side)){		
		return null;		
	}else{
		
		if(textCoordinates.size() > 1){
		int xLab = (int)Math.ceil(relCoord[0]/mm.widthSize);
		int yLab  = (int)Math.ceil(relCoord[1]/mm.heightSize);
		
		if(xLab ==1 && yLab == 2 ){
			return Label.FIRST;			
		}else if(xLab ==1 && yLab == 1 ){
			return Label.SECOND;
		}else if(xLab ==2 && yLab == 2 ){
			return Label.THIRD;
		}else if(xLab ==2 && yLab == 1 ){
			return Label.FOURTH;
		}else if(xLab ==3 && yLab == 2 ){
			return Label.FIFTH;
		}else if(xLab ==3 && yLab == 1 ){
			return Label.SIXTH;
		}
		
		
		
		}else{
			return Label.FIRST;
			
		}
		
		
		
	}

		
	return null;	
	}
	
	
}
	
	
