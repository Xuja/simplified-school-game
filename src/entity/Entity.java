package entity;

import room.Room;

public abstract class Entity {
	
	protected final Room theRoom;
	
	protected int posX;
	protected int posY;
	
	protected String icon;
	
	protected EntityState entityState;
	
	public Entity(Room room, String icon){
		this(room, 0, 0, icon);
	}
	
	public Entity(Room room, int x, int y, String icon){		
		this.theRoom = room;
		this.posX = x;
		this.posY = y;
		this.icon = icon;
		this.entityState = EntityState.IDLE;
	}

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
	
	public String getIcon(){
		return icon;
	}
	
	public EntityState getEntityState(){
		return entityState;
	}
}
