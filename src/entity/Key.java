package entity;

import room.Room;

public class Key extends Entity{

	public Key(Room room, int x, int y, String icon) {
		super(room, x, y, icon);
	}
	
	public int getKeyID(){
		return 0;
	}
	
	

}
