package entity;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import room.GameRoom;
import sprite.SpriteMap;

public abstract class Entity {
	
	protected final GameRoom theRoom;
	
	protected int posX;
	protected int posY;
	
	protected EntityState entityState;
	
	protected SpriteMap spriteMap = new SpriteMap();
	
	private float animation = 0.0F;
	protected float animationSpeed = 1.0F;
	
	private JLabel entityLabel;
	
	public Entity(GameRoom room){
		this.theRoom = room;
		this.entityState = EntityState.IDLE;
	}
	
	public void update(float deltaTime){
		animation = (animation + deltaTime * animationSpeed) % 1.0F;
	}
	
	public void render(int size){
		String icon = getIcon();
		entityLabel.setIcon(new ImageIcon(new ImageIcon(icon).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		entityLabel.setSize(size, size);
		entityLabel.setLocation(getRenderPositionX(size), getRenderPositionY(size));
		entityLabel.repaint();
	}
	
	protected int getRenderPositionX(int tileSize){
		return (posX * tileSize);
	}
	
	protected int getRenderPositionY(int tileSize){
		return (posY * tileSize);
	}
	
	public JLabel getEntityLabel(){
		return entityLabel;
	}
	
	public void init(){
		entityLabel = new JLabel();
		entityLabel.setOpaque(false);
		addSprites();
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
	
	public void setEntityState(String stateID){
		this.entityState = EntityState.getEntityState(stateID);
		if(entityState == null){
			entityState = EntityState.IDLE;
		}
	}
	
	public final String getIcon(){
		return spriteMap.getSprite(entityState).getSprite(animation);
	}
	
	public void loadEntityData(HashMap<String,String> dataMap){}
}
