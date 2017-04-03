package entity;

import room.Room;

public class Key extends Entity{

	public Key(Room room, int x, int y) {
		super(room, x, y);
	}
	
	public int getKeyID(){
		return 0;
	}

	@Override
	protected void addSprites() {
		
	}
}
