package entity;

public class Player extends Entity {

	private int currentKey = -1;
	
	public Player(int x, int y) {
		super(x, y);
	}
	
	
	
	public int getCurrentKey(){
		return currentKey;
	}
}
