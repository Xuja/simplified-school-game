package entity;

import room.Room;
import sprite.Sprite;
import sprite.SpriteMap;

public abstract class Entity {
	
	protected final Room theRoom;
	
	protected int posX;
	protected int posY;
	
	protected EntityState entityState;
	
	protected SpriteMap spriteMap = new SpriteMap();
	
	private float animation = 0.0F;
	
	public Entity(Room room){
		this(room, 0, 0);
	}
	
	public Entity(Room room, int x, int y){		
		this.theRoom = room;
		this.posX = x;
		this.posY = y;
		this.entityState = EntityState.IDLE;
		this.addSprites();
	}
	
	public void update(float deltaTime){
		animation = (animation + deltaTime) % 1.0F;
	}
	
	protected abstract void addSprites();

	public int getX(){
		return posX;
	}
	
	public int getY(){
		return posY;
	}
	
	public void setPosition(int x, int y){
		this.posX = x;
		this.posY = y;
	}
	
	public final String getIcon(){
		return spriteMap.getSprite(entityState).getSprite(animation);
	}
}
