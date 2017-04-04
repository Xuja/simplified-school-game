package roombuilder;

import java.util.HashMap;
import java.util.Set;

public class EntityBuilder{
	
	public final String id;
	public final int x;
	public final int y;
	public final String state;
	private HashMap<String,String> dataMap = new HashMap<String,String>();
	
	public EntityBuilder(String id, int x, int y, String state){
		this.id = id;
		this.x = x;
		this.y = y;
		this.state = state;
	}
	
	public EntityBuilder addAditionalData(String key, String value){
		dataMap.put(key, value);
		return this;
	}
	
	public boolean hasAdditionalData(){
		return !dataMap.isEmpty();
	}
	
	public Set<String> getDataKeys(){
		return dataMap.keySet();
	}
	
	public String getDataValue(String key){
		return dataMap.get(key);
	}
}