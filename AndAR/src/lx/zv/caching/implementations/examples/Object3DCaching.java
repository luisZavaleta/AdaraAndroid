package lx.zv.caching.implementations.examples;

import java.util.List;

import android.content.Context;
import edu.dhbw.andar.exceptions.AndARException;
import lx.zv.caching.PrimeCaching;
import lx.zv.caching.disposal.BaseDisposal;
import lx.zv.caching.disposal.DisposalEnum;
import lx.zv.caching.disposal.DisposalFactory;
import lx.zv.exceptions.JaguARException;
import lx.zv.model.persistence.dao.Object3DDAO;
import lx.zv.model.persistence.vo.Object3DOld;


public class Object3DCaching extends PrimeCaching<Object>{

	@Override
	public void preLoadElements() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object createObject(Object object) throws JaguARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object generatesKey(Object object) throws JaguARException {
		// TODO Auto-generated method stub
		return null;
	}
/*
	Context ctx = null;
	
	public Object3DCaching(BaseDisposal dispoBase) {
		super(DisposalFactory.getDisposal(DisposalEnum.FARTHEST_GEOSPATIALLY_BASED));
		// TODO Auto-generated constructor stub
	}

	public void setContext(Context ctx){
	this.ctx = ctx;	
	}
	
	
	@Override
	public void preLoadElements() throws JaguARException {
	
		if(ctx == null){
			throw new JaguARException("the context must be set in order to call this method");			
		}
		
		Object3DDAO dao = new Object3DDAO(null);
		
		List<Object3D> os3d =dao.getAllObjects();
		
	}
	
	
	
/*	

	private Object3D createObject(byte[] obj, byte[] mtl,byte[] image,String extension) throws JagARException{		
		
		Object3D o3d= new Object3D();
		
		o3d.setObj(obj);
		o3d.setMtl(mtl);
		o3d.setExtension(extension);
		o3d.setImage(image);		
		
		return o3d;		
	}
	*/
	
/*
	@Override
	protected Object3D createObject(Object... objects) throws JaguARException {
		
		
		if(objects.length == 1){
			if(objects[0] instanceof Object3D){
				return (Object3D)objects[0];
			}else{
				throw new JaguARException("the object created by createObject method could not be created");				
			}
		}
		
		
		//completar método despues
		throw new JaguARException("the object created by createObject method could not be created");
	
		
		/*
		 *  private byte[] obj;	 
	    private byte[] mtl;	   
	    private byte[] image;	 
	    private String extension;
		 * */
		
		// TODO Auto-generated method stub
	/*
	}

	@Override
	protected String generatesKey(Object... objects) throws JaguARException {
	
		if(objects.length == 1){
			if(objects[0] instanceof String){
				return (String) objects[0];
			}else{
				throw new JaguARException("the key created by generatesKey method could not be created");				
			}
		}
		
		//completar método despues
		throw new JaguARException("the key created by generatesKey method could not be created");
	
	}
*/

}
