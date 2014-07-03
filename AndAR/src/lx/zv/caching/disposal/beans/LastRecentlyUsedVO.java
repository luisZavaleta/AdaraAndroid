package lx.zv.caching.disposal.beans;

public class LastRecentlyUsedVO implements Comparable<LastRecentlyUsedVO>, DisposalVO {
	
	
	public Long date;
	public Object key;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		LastRecentlyUsedVO other = (LastRecentlyUsedVO) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}


	@Override
	public int compareTo(LastRecentlyUsedVO another) {		
		return this.date.compareTo(another.date);
	}


	@Override
	public Object getKey() {
		return key;
	}


	@Override
	public void setKey(Object key) {
		this.key = key;
		
	}

}
