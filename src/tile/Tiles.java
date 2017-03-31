package tile;

import entity.Player;
import room.Room;

public class Tiles{
	
	private static final Tiles[] TILES = new Tiles [256];
	
	public static final Tiles TILE_GRASS = new GrassTile("res/textures/tile/grass1.png", 0);
	public static final Tiles TILE_WALL = new WallTile("res/textures/tile/wall.png", 1);
	public static final Tiles TILE_BARRICADE = new BarricadeTile("res/textures/tile/blueGate.png", 2, 0);
	public static final Tiles TILE_WITHERED_GRASS = new WitheredGrassTile("res/textures/tile/grass2.png", 3);
	public static final Tiles TILE_GOAL = new GoalTile("res/textures/tile/goal.png", 4 );
	
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
	
	public void beforePlayerWalkTo(Room room, Player player, int x, int y){}

	public void onPlayerWalkedTo(Room room, Player player, int x, int y){}
	
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
