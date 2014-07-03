package lx.zv.jaguar.business.opengl.menu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import lx.zv.jaguar.business.menu.enums.Side;
import lx.zv.model.menu.bo.SideAndPercentages;

import android.content.res.Configuration;

import edu.dhbw.andar.util.GraphicsUtil;

/*

 
 @author luisZavaleta
 *
 */
public class DrawMenuBackground {

	 private FloatBuffer vertexBufferS;
	 private FloatBuffer textureBuffer;	 

	 //Square vertices
	 private float verticesS[] = { 
		-1.0f, -1.0f, 0.0f, //Bottom Left
		 1.0f, -1.0f, 0.0f, //Bottom Right
		-1.0f,  1.0f, 0.0f, //Top Left
		 1.0f,  1.0f, 0.0f 	//Top Right
	};
		
	private float texture[] = { 				
		0.0f, 1.0f, 
	    0.0f, 0.0f,
	    1.0f, 1.0f, 
	    1.0f,  0.0f
	};

	/*
	Initialize the square buffer 
	*/
	private void initBackgroundSquare() {
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(this.verticesS.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		this.vertexBufferS = byteBuf.asFloatBuffer();
		this.vertexBufferS.put(this.verticesS);
		this.vertexBufferS.position(0);
			
		byteBuf = ByteBuffer.allocateDirect(this.texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		this.textureBuffer = byteBuf.asFloatBuffer();
		this.textureBuffer.put(this.texture);
		this.textureBuffer.position(0);
	}

	
	private static void fitTextureToScreen(GL10 gl,SideAndPercentages sap){			
		switch(sap.side){
			case TOP: 
				gl.glTranslatef(1 - sap.heightPercentage, 0f, 0f);
				gl.glScalef(sap.heightPercentage, sap.widthPercentage, 1f);				
				break;
			case BOTTOM:
				gl.glTranslatef(sap.heightPercentage-1, 0f, 0f);
				gl.glScalef(sap.heightPercentage, sap.widthPercentage, 1f);
				break;
			case LEFT:
				gl.glTranslatef(0f, sap.heightPercentage-1, 0f);
				gl.glScalef(sap.widthPercentage, sap.heightPercentage, 1f);
				break;			
			case RIGHT:
				gl.glTranslatef(0f,1 -sap.heightPercentage, 0f);
				gl.glScalef(sap.widthPercentage, sap.heightPercentage, 1f);
				break;		
		}	
	}
		
		
		/**
		 * The object own drawing function.
		 * Called from the renderer to redraw this instance
		 * with possible changes in values.
		 * 
		 * @param gl - The GL context
		 */
		//public void drawS(GL10 gl, float textureWidth, float textureHeight, float screenWidth, float screenHeight, int orientation) {
		
	public void drawSquareBackground(GL10 gl, SideAndPercentages sap) {
			
		gl.glMatrixMode(GL10.GL_PROJECTION);
	    gl.glLoadIdentity();
	    gl.glOrthof(0f, 0f, 0, 0, 0, 0);
	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glDisable(GL10.GL_LIGHTING);
	    gl.glColor4f(1f, 1.0f, 0, 1.0f);
	    	
	    DrawMenuBackground.fitTextureToScreen(gl,sap);
		this.initBackgroundSquare();
	
		//Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.vertexBufferS);
			
		//Texture mapping
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.textureBuffer);
			
		//Enable vertex buffer
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			
		//Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, this.verticesS.length / 3);
			
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}	
}
