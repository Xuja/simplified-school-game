package entity;

import java.util.HashMap;

import room.GameRoom;
import sprite.SpriteFactory;

public class Key extends Entity{

	private String key;
	
	public Key(GameRoom room) {
		super(room);
	}
	
	public String getKeyID(){
		return key;
	}

	@Override
	public void update(float deltaTime){
		super.update(deltaTime);
		if(theRoom.getPlayer().posX == posX && theRoom.getPlayer().posY == posY){
			theRoom.removeEntityFromRoom(this);
		}
	}
	
	@Override
	protected void addSprites() {
		SpriteFactory sf = new SpriteFactory("res/textures/key");
		spriteMap.addSprite(EntityState.IDLE, sf.addSprite(key + "Key").buildSprite());
	}
	
	@Override
	public void loadEntityData(HashMap<String,String> dataMap){
		key = dataMap.get("key");
	}
}
