package tile;

import entity.EnumKey;
import entity.Player;
import room.GameRoom;

public class Tiles{
	
	private static final Tiles[] TILES = new Tiles [8];
	
	public static final Tiles TILE_GRASS = new GrassTile("res/textures/tile/grass1.png", 0);
	public static final Tiles TILE_GOAL = new GoalTile("res/textures/tile/goal.png", 1 );
	public static final Tiles TILE_WITHERED_GRASS = new WitheredGrassTile("res/textures/tile/grass2.png", 2);
	public static final Tiles TILE_WALL = new WallTile("res/textures/tile/wall.png", 3);
	public static final Tiles TILE_BARRICADE_BLUE = new BarricadeTile("res/textures/tile/barricade_blue.png", 4, EnumKey.KEY_BLUE);	
	public static final Tiles TILE_BARRICADE_GREEN = new BarricadeTile("res/textures/tile/barricade_green.png", 5, EnumKey.KEY_GREEN);	
	public static final Tiles TILE_BARRICADE_RED = new BarricadeTile("res/textures/tile/barricade_red.png", 6, EnumKey.KEY_RED);	
	public static final Tiles TILE_BARRICADE_YELLOW = new BarricadeTile("res/textures/tile/barricade_yellow.png", 7, EnumKey.KEY_YELLOW);	
	
	protected final int id;
	protected final String tileTexture;
	
	public Tiles(String tileTexture, int id){
		this.tileTexture = tileTexture;
		this.id = id;
		TILES[id] = this;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public void beforePlayerWalkTo(GameRoom room, Player player, int x, int y){}

	public void onPlayerWalkedTo(GameRoom room, Player player, int x, int y){}
	
	public static Tiles getTile(int id) {
		return TILES[id];
	}
	
	public boolean canPlayerWalkTo(Player player){
		return !isSolid();
	}
	
	public String getTileTexture(){
		return tileTexture;
	}
}
