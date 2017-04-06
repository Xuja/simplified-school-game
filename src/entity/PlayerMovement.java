package entity;

import game.EnumDirection;

public class PlayerMovement {

	//the time it takes for the player to move from 1 tile to another tile.
	private final float moveTime;

	//How far along the player is moving to another tile. value between 0.0 - 1.0
	private float progress = 0.0F;
	private EnumDirection moveDirection = EnumDirection.NONE;
	
	private final Player thePlayer;
	
	public PlayerMovement(Player player, float moveTime){
		this.thePlayer = player;
		this.moveTime = moveTime;
	}
	
	
	//return true if the player is in progress of moving to another tile
	public boolean isPlayerMoving(){
		return moveDirection != EnumDirection.NONE;
	}
	

	//Starts moving the player in a direction
	public void startMoving(EnumDirection dir){
		moveDirection = dir;
	}
	
	public void updateMovement(float deltaTime){
		if(isPlayerMoving()){
			progress += deltaTime;
			if(progress >= moveTime){
				progress = 0.0F;
				thePlayer.finalizeMove(moveDirection);
				moveDirection = EnumDirection.NONE;
			}
		}
	}
	
	public float getX(){
		return progress * moveDirection.x * (1.0F / moveTime);
	}
	
	public float getY(){
		return progress * moveDirection.y * (1.0F / moveTime);
	}
	
}
