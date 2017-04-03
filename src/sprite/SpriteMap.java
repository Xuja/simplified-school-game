package sprite;

import java.util.HashMap;

import entity.EntityState;

public class SpriteMap {

	private final HashMap<EntityState,Sprite> spriteMap = new HashMap<EntityState,Sprite>();
	
	public SpriteMap(){}
	
	public void addSprite(EntityState state, Sprite sprite){
		spriteMap.put(state, sprite);
	}
	
	public Sprite getSprite(EntityState state){
		return spriteMap.get(state);
	}
}
