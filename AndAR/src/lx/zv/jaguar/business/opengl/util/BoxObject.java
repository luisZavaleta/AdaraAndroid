package lx.zv.jaguar.business.opengl.util;

import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

import lx.zv.jaguar.business.menu.interfaces.IdentificableByMenu;
import edu.dhbw.andar.ARObject;
import edu.dhbw.andar.util.GraphicsUtil;


/**
 * An example of an AR object being drawn on a marker.
 * @author tobi
 *
 */


/*
 Implementa ARObject, Agrega materiales al objeto y el objeto 3D que se despliega cuando .
 * */
public class BoxObject extends ARObject implements IdentificableByMenu{

	private Integer menuId;

	// Bitmap bm = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);






	public BoxObject(String name, String patternName,
			double markerWidth, double[] markerCenter) {
		
		super(name, patternName, markerWidth, markerCenter);
		float   mat_ambientf[]     = {0f, 1.0f, 0f, 1.0f};
		float   mat_flashf[]       = {0f, 1.0f, 0f, 1.0f};
		float   mat_diffusef[]       = {0f, 1.0f, 0f, 1.0f};
		float   mat_flash_shinyf[] = {50.0f};

		this.mat_ambient = GraphicsUtil.makeFloatBuffer(mat_ambientf);
		this.mat_flash = GraphicsUtil.makeFloatBuffer(mat_flashf);
		this.mat_flash_shiny = GraphicsUtil.makeFloatBuffer(mat_flash_shinyf);
		this.mat_diffuse = GraphicsUtil.makeFloatBuffer(mat_diffusef);

	}
	public BoxObject(String name, String patternName,
			double markerWidth, double[] markerCenter, float[] customColor) {
		
		super(name, patternName, markerWidth, markerCenter);
		float   mat_flash_shinyf[] = {50.0f};

		this.mat_ambient = GraphicsUtil.makeFloatBuffer(customColor);
		this.mat_flash = GraphicsUtil.makeFloatBuffer(customColor);
		this.mat_flash_shiny = GraphicsUtil.makeFloatBuffer(mat_flash_shinyf);
		this.mat_diffuse = GraphicsUtil.makeFloatBuffer(customColor);

	}


	private SimpleBox box = SimpleBox.getInstance();
	private FloatBuffer mat_flash;
	private FloatBuffer mat_ambient;
	private FloatBuffer mat_flash_shiny;
	private FloatBuffer mat_diffuse;
	//private DrawImages dm = new DrawImages();

	/**
	 * Everything drawn here will be drawn directly onto the marker,
	 * as the corresponding translation matrix will already be applied.
	 */
	@Override
	public final void draw(GL10 gl) {


		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR,this.mat_flash);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, this.mat_flash_shiny);	
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, this.mat_diffuse);	
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, this.mat_ambient);

		//draw cube



		gl.glColor4f(1.0f, 0f, 0, 1.0f);

		super.draw(gl);


		this.box.draw(gl);
	}
	@Override
	public void init(GL10 gl) {
		// TODO Auto-generated method stub

	}
	@Override
	public Integer getMenuId() {
		return this.menuId;
	}
	@Override
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;

	}
}
