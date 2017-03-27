package entity;

public class Player extends Entity {

	private int currentKey = -1;
	
	public Player(int width, int height) {
		super(width, height);
		bounds.width = width;
		bounds.height = height;		
	}
	
	public int getCurrentKey(){
		return currentKey;
	}
}
