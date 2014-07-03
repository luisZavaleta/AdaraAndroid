package lx.zv.jaguar.view.helper;

import static android.opengl.GLES10.GL_FLOAT;
import static android.opengl.GLES10.glTexCoordPointer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;


import java.util.Observable;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;


import lx.zv.annotations.menu.DrawMenu;
import lx.zv.business.facade.menu.MenuFacade;
import lx.zv.exceptions.JaguARException;
import lx.zv.jaguar.annotations.PreloadBaseTextTexture;
import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.jaguar.business.menu.enums.Textura;
import lx.zv.jaguar.business.opengl.menu.DrawMenuBackground;
import lx.zv.jaguar.business.opengl.menu.MenuText;
import lx.zv.jaguar.business.sensors.GeospatialManager;
import lx.zv.jaguar.business.sensors.ImageOrientation;
import lx.zv.jaguar.business.sensors.OrientationManager;
import lx.zv.jaguar.config.Config;
import lx.zv.model.menu.bo.Texture;
import lx.zv.textures.InitBackgroundTexture;

//import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.opengl.GLUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import edu.dhbw.andar.ARObject;
import edu.dhbw.andar.interfaces.OpenGLRenderer;
import edu.dhbw.andar.util.GraphicsUtil;
import edu.dhbw.andopenglcam.R;
import lx.zv.textures.labelMArker.LabelMaker;



/**
 * 
 * @author @luisZavaleta (based in @tobi CustomRenderer)
 * 
 */
public class JaguARRenderer implements OpenGLRenderer {
	
	public Vector<ARObject> arObjects;
	public Observable obs;	
	//MenuFacade mf = null;	
	public DisplayMetrics displayMetrics = null;
	
	
	
	 



	/*
	 Contructor para crear un CustomRenderer y dibujar una textura con texto.
	 
	 @param ctx Android context
	 @param customObjects Objectos agregados para ser desplegados (contiene, patron y objeto 3D)
	 * */
	public JaguARRenderer(DisplayMetrics displayMetrics, Vector<ARObject> arObjects, Observable obs) {

		this.arObjects = arObjects;		
		this.obs = obs;		
		this.displayMetrics = displayMetrics;
		
	//	mf = new MenuFacade(arObjects, displayMetrics, obs);
		
	
	}

	/**
	 * Light definitions
	 */

	private float[] ambientlight1 = { .3f, .3f, .3f, 1f };
	private float[] diffuselight1 = { .7f, .7f, .7f, 1f };
	private float[] specularlight1 = { 0.6f, 0.6f, 0.6f, 1f };
	private float[] lightposition1 = { 20.0f, -40.0f, 100.0f, 1f };

	private FloatBuffer lightPositionBuffer1 = GraphicsUtil
			.makeFloatBuffer(lightposition1);
	private FloatBuffer specularLightBuffer1 = GraphicsUtil
			.makeFloatBuffer(specularlight1);
	private FloatBuffer diffuseLightBuffer1 = GraphicsUtil
			.makeFloatBuffer(diffuselight1);
	private FloatBuffer ambientLightBuffer1 = GraphicsUtil
			.makeFloatBuffer(ambientlight1);
	
	
	

	@DrawMenu(true)
	public final void draw(GL10 gl) {
		
		Log.e("DrawMenu","Inside JaguarRendered");

	/*	try {
//			mf.drawMenu(gl);
		} catch (JaguARException e) {
			e.printStackTrace();
		}
*/

	}

	
	public final void setupEnv(GL10 gl) {
		Log.w("AspectJ", "entrando a setupEnv");
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, ambientLightBuffer1);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, diffuseLightBuffer1);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR, specularLightBuffer1);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, lightPositionBuffer1);
		gl.glEnable(GL10.GL_LIGHT1);

		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);

		//Por que diablos inicia gl????
		//initGL(gl);
	}

	@PreloadBaseTextTexture(true)
	public final void initGL(GL10 gl) {
		gl.glDisable(GL10.GL_COLOR_MATERIAL);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glDisable(GL10.GL_COLOR_MATERIAL);
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_NORMALIZE);
	}
}
