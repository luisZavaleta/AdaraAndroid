package lx.zv.textures;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import lx.zv.exceptions.DivisionNumberException;
import lx.zv.jaguar.business.menu.enums.Position;
import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.jaguar.business.menu.enums.Textura;
import lx.zv.jaguar.business.opengl.menu.DrawMenuBackground;
import lx.zv.model.menu.bo.Texture;

import edu.dhbw.andopenglcam.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

/*
 Inicialización de textura con texto
 
 @author @luisZavaleta
 * */

public class InitBackgroundTexture {
	
	private int mTextureID;
	private int division;
	GL10 gl;
	Context ctx;
	
	private static int[] textures = new int[8];
	//private DrawImages dt;
	static InitBackgroundTexture instance;
	static int[] texturesIds = null;
	
	
	
	
	

	/*
	 * Cosntructor for texture creation
	 * @param gl openGL 1.0
	 * @param ctx android context
	 * @param division Number of divisions in the menue.Bitmap
	 * */
	private InitBackgroundTexture(GL10 gl, Context ctx,int division){

		this.division = division;
		this.gl = gl;
		this.ctx = ctx;
		
		texturesIds = this.initTextures();
	} 
	
	
	public static InitBackgroundTexture getInstance(GL10 gl, Context ctx,int division){
		
		if(instance == null){
			instance = new InitBackgroundTexture(gl,ctx,division);
			//texturesIds = instance.initTextures();
			Log.d("InitBackgroundTexture", "InitBackgroundTexture");
			texturesIds = instance.initTextures();
		}
		
		return instance;
	}
	
	public InitBackgroundTexture(GL10 gl, Context ctx){
		this.division = 1;			
	} 
	
	private void pushTexture(int position, int resourceId){
		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[position]);
		 
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,  GL10.GL_NEAREST);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);
        
        InputStream is = ctx.getResources().openRawResource(resourceId);
        
        Bitmap bitmap;
        
       
        
        try {
            bitmap = BitmapFactory.decodeStream(is);	
            
            
            
        } finally {
            try {
                is.close();
            } catch(IOException e) {
                // Ignore.
            }
        }

      //  Bitmap bitmapx =  Bitmap.createScaledBitmap(bitmap,1024,1024,true);
        // Push the bitmap onto the GPU:
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        
        bitmap.recycle();
		
	}
	
	private int[] initTextures(){
		
		
		
			
		gl.glGenTextures(8, textures, 0);
			
			int[] texIds = new int[8];
			
			
			//verificar si R es null
			texIds[0]=R.raw.onediv;
			texIds[1]=R.raw.twodivhor;
			texIds[2]=R.raw.fourdivhor;
			texIds[3]=R.raw.sixdivhor;
			texIds[4]=R.raw.onediv;
			texIds[5]=R.raw.twodiv;
			texIds[6]=R.raw.fourdiv;
			texIds[7]=R.raw.sixdiv;
				
			
				for(int i=0; i < 8; i++){
					this.pushTexture(i, texIds[i]);
				}
			
			
			
			//mTextureID = textures[0];
		 
		
	        //t.setId(mTextureID);
	        return textures;
		
	}
	
	/*
	 *Create the background texture with the parameters given.
	 *@param textura textura 
	 * 
	 * */
	public void createTexture(Textura textura){	
		this.createTexture(textura.getPosition());
	}
	
	/*
	 	Create the background texture with the parameters given.
	 
	  	@param pos Position, if it is null Position.HORIZONTAL is used.
	 	@param divisionNumber Number of divisions (menus).
	 */
	
	
	public void selectTexture(int divisionNumber, Side s) throws DivisionNumberException{
		
		
		if(Side.BOTTOM.equals(s) || Side.TOP.equals(s)){
			this.selectTexture(divisionNumber, Position.VERTICAL);
		}else{
			this.selectTexture(divisionNumber, Position.HORIZONTAL);
		}
		
		
	}
	
	
	
	public void selectTexture(int divisionNumber, Position pos) throws DivisionNumberException{
		
		if(divisionNumber < 1 ||  divisionNumber > 6){			
			throw new DivisionNumberException();
		}
		
		Textura tx = Textura.HORIZONTAL1;
		
		if(Position.VERTICAL.equals(pos)){
		
		switch(divisionNumber){
		case 1:
			tx = Textura.VERTICAL1;
			break;
		case 2:
			tx = Textura.VERTICAL2;
			break;
		case 3:
		case 4:
			tx = Textura.VERTICAL4;
			break;
		case 5:
		case 6:
			tx = Textura.VERTICAL6;
			break;
		
		}
		}else{			
			switch(divisionNumber){
			case 1:
				tx = Textura.HORIZONTAL1;
				break;
			case 2:
				tx = Textura.HORIZONTAL2;
				break;
			case 3:
			case 4:
				tx = Textura.HORIZONTAL4;
				break;
			case 5:
			case 6:
				tx = Textura.HORIZONTAL6;
				break;			
			}
		}
		
		

		
	this.createTexture(tx);	
	
	
		
	}
	

	
	
	private void createTexture(int textureId){
		
		
		gl.glDisable(GL10.GL_DITHER);
	 	gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);			
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glActiveTexture(GL10.GL_TEXTURE0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[textureId]);
        
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		
		
	}
	
	
}
