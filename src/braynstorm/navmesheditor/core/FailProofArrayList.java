package braynstorm.navmesheditor.core;

import java.util.ArrayList;

public class FailProofArrayList<T> extends ArrayList<T> {
	
	private static final long serialVersionUID = 1L;
	private T defaultItem;
	
	public void setDefault(T item){
		defaultItem = item;
	}
	
	public T getWithDefault(int index){
		T result = get(index);
		return result == null ? defaultItem : result;
	}
	
}
