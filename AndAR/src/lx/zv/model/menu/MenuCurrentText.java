package lx.zv.model.menu;

import java.util.ArrayList;
import java.util.List;

import lx.zv.model.persistence.vo.sincronization.MenuVO;

public class MenuCurrentText {
	
	private static List<MenuVO> mvl; 
	public static long actualObjId = Long.MIN_VALUE;
	public static long menuId = 1;
	
	
	public static void  setMenueVOList( List<MenuVO> mvl){
		
		MenuCurrentText.mvl = mvl;
	}

	
public static List<MenuVO> getMenueVOList(){
		
	List<MenuVO> lmv = MenuCurrentText.mvl;
	
	/*
	List<MenuVO> lmv = new ArrayList<MenuVO>();
	
	MenuVO mvo0= new MenuVO();
	MenuVO mvo1 = new MenuVO();
	mvo0.setOrder(1);
	mvo0.setText("Hola");
	
	
	mvo1.setOrder(0);
	mvo1.setText("Mundo");
	
	lmv.add(mvo0);
	lmv.add(mvo1);
	*/
	
	
		return  lmv;
	}
	
}
