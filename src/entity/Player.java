package entity;

import game.EnumDirection;
import input.IActionListener;
import input.InputAction;
import room.Room;
import tile.Tiles;

public class Player extends Entity implements IActionListener{

	private PlayerMovement playerMovement;

	private int currentKey = -1;

	public Player(Room room, int x, int y, String icon) {
		super(room, x, y, icon);
		this.playerMovement = new PlayerMovement(this, 0.5F);
		this.entityState = EntityState.IDLE_DOWN;
	}

	public int getCurrentKey(){
		return currentKey;
	}

	@Override
	public void onAction(InputAction action, int actionFlag) {

		if(actionFlag == 0){
			switch(action){
			default: return;

			case MOVE_UP:
				startMoving(EnumDirection.UP);
				break;

			case MOVE_DOWN:
				startMoving(EnumDirection.DOWN);
				break;

			case MOVE_LEFT:
				startMoving(EnumDirection.LEFT);
				break;

			case MOVE_RIGHT:
				startMoving(EnumDirection.RIGHT);
				break;
			}
		}
	}

	public void update(float deltaTime){
		playerMovement.updateMovement(deltaTime);
	}

	/**
	 * Start moving the player in a direction, won't do anything if the player is already moving.
	 * @param dir the direction the player needs to move to.
	 */
	public void startMoving(EnumDirection dir){

		if(dir == null || playerMovement.isPlayerMoving())
			return;

		int nx = posX + dir.x;
		int ny = posY + dir.y;

		Tiles tile = theRoom.getTile(nx, ny);
		tile.beforePlayerWalkTo(theRoom, this, nx, ny);

		tile = theRoom.getTile(nx, ny);
		if(tile.canPlayerWalkTo(this)){
			playerMovement.startMoving(dir);
			entityState = EntityState.WALK_DIRECTION_MAP.get(dir);
		}
		
		theRoom.onPlayerMoved();
	}

	/**
	 * Fired by player movement when the player is ready to progress to the next tile.
	 * @param dir the direction the player needs to move to.
	 */
	public void finalizeMove(EnumDirection dir){
		if(dir == null)
			return;

		int nx = posX + dir.x;
		int ny = posY + dir.y;

		Tiles tile = theRoom.getTile(nx, ny);
		tile.onPlayerWalkedTo(theRoom, this, nx, ny);

		setPosition(nx, ny);
		
		entityState = EntityState.IDLE_DIRECTION_MAP.get(dir);
	}
	
	public int getPlayerRenderPositionX(int tileSize){
		return (int)((float)(posX + playerMovement.getX()) * tileSize);
	}
	
	public int getPlayerRenderPositionY(int tileSize){
		return (int)((float)(posY + playerMovement.getY()) * tileSize);
	}
	
	@Override
	public boolean isActive() {
		return true;
	}
}
