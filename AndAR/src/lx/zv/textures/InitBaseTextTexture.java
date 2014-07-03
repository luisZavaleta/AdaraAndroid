package lx.zv.textures;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import lx.zv.jaguar.config.Config;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import edu.dhbw.andopenglcam.R;

public class InitBaseTextTexture {
	
	private static int[] textures = null;	
	private static InitBaseTextTexture initBaseTexture = null;
	
	
	private InitBaseTextTexture(){		
	}
	
	
	public static InitBaseTextTexture getInstance(){		
		if(initBaseTexture == null){
			initBaseTexture = new InitBaseTextTexture();
		}		
		return initBaseTexture;		
	}
	
	
	
	
	public int[] initTextures(GL10 gl){		
		
		if(textures != null){			
			return textures; 
		}
		
		textures = new int[8];
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
					this.pushTexture(i, texIds[i], gl);
				}
			
			
			
			//mTextureID = textures[0];
		 
		
	        //t.setId(mTextureID);
	        return textures;
		
	}

	
	
	private void pushTexture(int position, int resourceId,GL10 gl){
		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[position]);
		 
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,  GL10.GL_NEAREST);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);
        
        InputStream is = Config.context.getResources().openRawResource(resourceId);
        
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
	
	
}
