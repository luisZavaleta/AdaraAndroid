package lx.zv.textures;

import java.util.HashMap;

import lx.zv.model.persistence.bo.texture.TextureVO;



public class TexTexturePool {
	
	//esto guarda la textura del fondo transparente, modificar no soolo importa si es vertical u horizontal
	HashMap<TextureVO, Integer> textures = new HashMap<TextureVO, Integer>();
	
	
	
	public boolean isTextureAvailable(TextureVO tvo){		
		return textures.containsKey(tvo);
	}
	
	
	
	public int getTextureId(TextureVO tvo){		
		return textures.get(tvo);
	}
	
	
	public void setTexture(TextureVO tvo, int textureId){		
		textures.put(tvo, textureId);		
	}
	
	public void deleteTexture(TextureVO tvo){
		textures.remove(tvo);
	}
	
	
	

}
