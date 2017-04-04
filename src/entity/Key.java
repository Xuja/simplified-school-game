package entity;

import room.GameRoom;
import sprite.SpriteFactory;

public class Key extends Entity{

	public Key(GameRoom room, int x, int y) {
		super(room, x, y);
	}
	
	public int getKeyID(){
		return 0;
	}

	@Override
	protected void addSprites() {
		SpriteFactory sf = new SpriteFactory("res/textures/key");
		spriteMap.addSprite(EntityState.KEY_BLUE, sf.addSprite("blue_key").buildSprite());
	}
}
