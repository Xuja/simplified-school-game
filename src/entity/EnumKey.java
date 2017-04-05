package entity;

import java.util.HashMap;

public enum EnumKey {

	KEY_BLUE("blue"),
	KEY_GREEN("green"),
	KEY_YELLOW("yellow"),
	KEY_RED("red");
	
	private static final HashMap<String,EnumKey> KEY_MAP = new HashMap<String,EnumKey>();
	
	private final String keyID;
	
	private EnumKey(String id){
		this.keyID = id;
	}
	
	public String getKeyID(){
		return keyID;
	}
	
	public static EnumKey getKey(String keyID){
		return KEY_MAP.get(keyID);
	}
	
	static{
		KEY_MAP.put(KEY_BLUE.keyID, KEY_BLUE);
		KEY_MAP.put(KEY_GREEN.keyID, KEY_GREEN);
		KEY_MAP.put(KEY_YELLOW.keyID, KEY_YELLOW);
		KEY_MAP.put(KEY_RED.keyID, KEY_RED);
	}
}
