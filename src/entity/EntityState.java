package entity;

import java.util.HashMap;

import game.EnumDirection;

public class EntityState {

	private static HashMap<String,EntityState> stateMap = new HashMap<String,EntityState>();
	
	public static final EntityState IDLE = new EntityState("idle");
	public static final EntityState IDLE_UP = new EntityState("idle_up");
	public static final EntityState IDLE_DOWN = new EntityState("idle_down");
	public static final EntityState IDLE_LEFT = new EntityState("idle_left");
	public static final EntityState IDLE_RIGHT = new EntityState("idle_right");
	public static final EntityState WALK_UP = new EntityState("walk_up");
	public static final EntityState WALK_DOWN = new EntityState("walk_down");
	public static final EntityState WALK_LEFT = new EntityState("walk_left");
	public static final EntityState WALK_RIGHT = new EntityState("walk_right");

	public static final HashMap<EnumDirection, EntityState> WALK_DIRECTION_MAP = new HashMap<EnumDirection, EntityState>();
	public static final HashMap<EnumDirection, EntityState> IDLE_DIRECTION_MAP = new HashMap<EnumDirection, EntityState>();
	
	private final String stateID;
	
	private EntityState(String id){
		this.stateID = id;
		stateMap.put(id, this);
	}
	
	public String getStateID(){
		return stateID;
	}
	
	public static EntityState getEntityState(String id){
		return stateMap.get(id);
	}
	
	//static initializer block dont have to add into UML
	static{
		WALK_DIRECTION_MAP.put(EnumDirection.DOWN, WALK_DOWN);
		WALK_DIRECTION_MAP.put(EnumDirection.UP, WALK_UP);
		WALK_DIRECTION_MAP.put(EnumDirection.LEFT, WALK_LEFT);
		WALK_DIRECTION_MAP.put(EnumDirection.RIGHT, WALK_RIGHT);
		IDLE_DIRECTION_MAP.put(EnumDirection.DOWN, IDLE_DOWN);
		IDLE_DIRECTION_MAP.put(EnumDirection.UP, IDLE_UP);
		IDLE_DIRECTION_MAP.put(EnumDirection.LEFT, IDLE_LEFT);
		IDLE_DIRECTION_MAP.put(EnumDirection.RIGHT, IDLE_RIGHT);
	}
}
