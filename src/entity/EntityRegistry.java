package entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import room.GameRoom;

public class EntityRegistry {

	private static final HashMap<String,Class<? extends Entity>> ENTITY_MAP = new HashMap<String,Class<? extends Entity>>();

	public static void registerEntity(String entityID, Class<? extends Entity> entityClass){
		if(ENTITY_MAP.containsKey(entityID)){
			System.err.println("Already got a entity with id " + entityID + "!");
		}
		else{
			ENTITY_MAP.put(entityID, entityClass);
		}
	}

	public static Entity createNewEntity(String entityID, GameRoom room){
		if(!ENTITY_MAP.containsKey(entityID)){
			System.err.println("Failed to create an entity, there is no entity registered with id " + entityID + "!");
			return null;
		}

		try {
			Class<? extends Entity> entityClass = ENTITY_MAP.get(entityID);
			Constructor<?> cons = entityClass.getConstructor(GameRoom.class);
			Entity entity = (Entity) cons.newInstance(room);
			return entity;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
