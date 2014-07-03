package lx.zv.jaguar.business.sensors.interfaces;

import lx.zv.jaguar.business.menu.enums.Side;

public interface OrientationListener{
	
	  public void onOrientationChanged(double pitch, double roll);
	  
	  public Side getOrientation();
	  
	 
	  /*
	   Executed when the top of the device screen in up.
	   */
	  public void onTopUp();
	  
	  /*
	   Executed when the bottom of the device screen in up
	   */
	  public void onBottomUp();
	  	  
	  /*
	   Executed when the left of the device screen in up
	   */
	  public void onLeftUp();
	  
	  /*
	   Executed when the right of the device screen in up
	   */
	  public void onRightUp();

}
