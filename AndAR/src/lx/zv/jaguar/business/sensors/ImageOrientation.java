package lx.zv.jaguar.business.sensors;

import java.util.ArrayList;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andar.ARObject;

import lx.zv.exceptions.DivisionNumberException;
import lx.zv.jaguar.business.menu.enums.Position;
import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.jaguar.business.menu.util.CoordinatePosition;
import lx.zv.jaguar.business.opengl.menu.DrawMenuBackground;
import lx.zv.jaguar.business.opengl.menu.MenuText;
import lx.zv.textures.InitBackgroundTexture;
import android.content.Context;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;

public class ImageOrientation extends OrientationManager{
	

	/*GL10 gl;
	Context ctx;

	ImageOrientation cio;
	Vector<ARObject> arObjects;
	
	MenuText mt;
	*/

	

	public ImageOrientation(SensorManager sm) {
		super(sm);
		/*this.gl = gl;
		this.ctx = ctx;
		this.arObjects = arObjects;		
		
		mt = new MenuText(gl);
		*/
	}
	

	

	@Override
	public void onTopUp() {
	}

	@Override
	public void onBottomUp() {	
	}

	@Override
	public void onLeftUp() {	
	}

	@Override
	public void onRightUp() {	
	}
	
}
