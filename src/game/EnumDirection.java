package game;

public enum EnumDirection {

	NONE(0, 0), UP(0, -1),DOWN(0, 1),LEFT(-1, 0),RIGHT(1, 0);
	
	public final int x;
	public final int y;
	
	private EnumDirection(int x, int y){
		this.x = x;
		this.y = y;
	}
}
