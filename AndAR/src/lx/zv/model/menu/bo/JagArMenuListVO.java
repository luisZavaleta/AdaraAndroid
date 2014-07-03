package lx.zv.model.menu.bo;

import java.util.ArrayList;

import android.graphics.Paint;

public class JagArMenuListVO {

	public Paint paint;
	public ArrayList<String> txts;
	public int initX;
	public int initY;
	public float menuWidth;
	public float menuHeight;
	public float textHeight;
	public JagARPadding jarPadding;
	public int spaceBetweenLines;

	// Hash code and equals do not include Paint because if Paint changes all
	// the precalculate valuetes will be deleted.

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + initX;
		result = prime * result + initY;
		result = prime * result
				+ ((jarPadding == null) ? 0 : jarPadding.hashCode());
		result = prime * result + Float.floatToIntBits(menuHeight);
		result = prime * result + Float.floatToIntBits(menuWidth);
		result = prime * result + spaceBetweenLines;
		result = prime * result + Float.floatToIntBits(textHeight);
		result = prime * result + ((txts == null) ? 0 : txts.hashCode());
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
		JagArMenuListVO other = (JagArMenuListVO) obj;
		if (initX != other.initX)
			return false;
		if (initY != other.initY)
			return false;
		if (jarPadding == null) {
			if (other.jarPadding != null)
				return false;
		} else if (!jarPadding.equals(other.jarPadding))
			return false;
		if (Float.floatToIntBits(menuHeight) != Float
				.floatToIntBits(other.menuHeight))
			return false;
		if (Float.floatToIntBits(menuWidth) != Float
				.floatToIntBits(other.menuWidth))
			return false;
		if (spaceBetweenLines != other.spaceBetweenLines)
			return false;
		if (Float.floatToIntBits(textHeight) != Float
				.floatToIntBits(other.textHeight))
			return false;
		if (txts == null) {
			if (other.txts != null)
				return false;
		} else if (!txts.equals(other.txts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JagArMenuListVO [paint=" + paint + ", txts=" + txts
				+ ", initX=" + initX + ", initY=" + initY + ", menuWidth="
				+ menuWidth + ", menuHeight=" + menuHeight + ", textHeight="
				+ textHeight + ", jarPadding=" + jarPadding
				+ ", spaceBetweenLines=" + spaceBetweenLines + "]";
	}

	/*
	 * Paint paint,ArrayList<String> txts, int initX, int initY, float
	 * menuWidth, float menuHeight, float textHeight, int paddingRight, int
	 * paddingLeft, int paddingTop, int paddingBottom, int spaceBetweenLines
	 */

}
