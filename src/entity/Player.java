package entity;

import game.EnumDirection;
import input.IActionListener;
import input.InputAction;
import room.GameRoom;
import sprite.SpriteFactory;
import tile.Tiles;

public class Player extends Entity implements IActionListener{

	private PlayerMovement playerMovement;

	private int currentKey = -1;
	
	public Player(GameRoom room, int x, int y) {
		super(room, x, y);
		this.playerMovement = new PlayerMovement(this, 0.5F);
		this.entityState = EntityState.IDLE_DOWN;
		this.animationSpeed = 2.0F;
	}

	@Override
	protected void addSprites() {
		SpriteFactory sf = new SpriteFactory("res/textures/player");
		spriteMap.addSprite(EntityState.IDLE_DOWN, sf.addSprite("DOWN1").buildSprite());
		spriteMap.addSprite(EntityState.IDLE_LEFT, sf.addSprite("LEFT1").buildSprite());
		spriteMap.addSprite(EntityState.IDLE_RIGHT, sf.addSprite("RIGHT1").buildSprite());
		spriteMap.addSprite(EntityState.IDLE_UP, sf.addSprite("UP1").buildSprite());
		
		int i;
		for(i = 0; i < 9; i++){
			sf.addSprite("DOWN" + (i + 1));
		}
		spriteMap.addSprite(EntityState.WALK_DOWN, sf.buildSprite());
		
		for(i = 0; i < 9; i++){
			sf.addSprite("LEFT" + (i + 1));
		}
		spriteMap.addSprite(EntityState.WALK_LEFT, sf.buildSprite());
		
		for(i = 0; i < 9; i++){
			sf.addSprite("RIGHT" + (i + 1));
		}
		spriteMap.addSprite(EntityState.WALK_RIGHT, sf.buildSprite());
		
		for(i = 0; i < 9; i++){
			sf.addSprite("UP" + (i + 1));
		}
		spriteMap.addSprite(EntityState.WALK_UP, sf.buildSprite());
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
		super.update(deltaTime);
		playerMovement.updateMovement(deltaTime);
	}

	
	 //Start moving the player in a direction, won't do anything if the player is already moving.
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
			setEntityState(EntityState.WALK_DIRECTION_MAP.get(dir));
		}
		
		theRoom.onPlayerMoved();
	}


	//Fired by player movement when the player is ready to progress to the next tile.
	public void finalizeMove(EnumDirection dir){
		if(dir == null)
			return;

		int nx = posX + dir.x;
		int ny = posY + dir.y;

		Tiles tile = theRoom.getTile(nx, ny);
		tile.onPlayerWalkedTo(theRoom, this, nx, ny);

		setPosition(nx, ny);
		
		setEntityState(EntityState.IDLE_DIRECTION_MAP.get(dir));
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
	
	public void setEntityState(EntityState state){
		entityState = state;
		System.out.println(state.getStateID());
	}
}
