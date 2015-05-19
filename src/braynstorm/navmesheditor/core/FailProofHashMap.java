package braynstorm.navmesheditor.core;

import java.util.HashMap;

public class FailProofHashMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	public V defaultValue;
	
	public void setDefaultValue(V val){
		defaultValue = val;
	}
	
	public V getWithDefault(K key){
		V val = this.get(key);
		return val == null ? defaultValue : val;
	}
	
}
