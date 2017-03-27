package entity;

import game.EnumDirection;

public class PlayerMovement {

	/**
	 * The time it takes for the player to move from 1 tile to another tile.
	 */
	public static final float MOVE_TIME = 1.0F;
	
	/**
	 * How far along the player is moving to another tile. value between 0.0 - 1.0
	 */
	private float progress = 0.0F;
	private EnumDirection moveDirection = null;
	
	private final Player thePlayer;
	
	public PlayerMovement(Player player){
		this.thePlayer = player;
	}
	
	/**
	 * @return true if the player is in progress of moving to another tile
	 */
	public boolean isPlayerMoving(){
		return moveDirection != null;
	}
	
	/**
	 * Starts moving the player in a direction
	 * @param dir
	 */
	public void startMoving(EnumDirection dir){
		moveDirection = dir;
	}
	
	public void updateMovement(float deltaTime){
		if(isPlayerMoving()){
			progress += deltaTime;
			if(progress >= MOVE_TIME){
				progress = 0.0F;
				thePlayer.finalizeMove(moveDirection);
				moveDirection = null;
			}
		}
	}
	
}
