package entity;

import room.GameRoom;

public class Key extends Entity{

	public Key(GameRoom room, int x, int y) {
		super(room, x, y);
	}
	
	public int getKeyID(){
		return 0;
	}

	@Override
	protected void addSprites() {
		
	}
}
