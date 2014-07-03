package lx.zv.model.persistence.bo.texture;

import lx.zv.jaguar.business.menu.enums.Position;
import lx.zv.jaguar.business.menu.enums.Side;

public class TextureVO {
	
	public Side side;
	public String text;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((side == null) ? 0 : side.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextureVO other = (TextureVO) obj;
		if (side != other.side)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	
	

}
