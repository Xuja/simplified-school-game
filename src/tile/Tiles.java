package tile;

import entity.Player;

public class Tiles{
	
	private static final Tiles[] TILES = new Tiles [256];
	
	public static final Tiles TILE_GRASS = new GrassTile("src/img/grass1.png", 0);
	public static final Tiles TILE_WALL = new WallTile("src/img/wall.png", 1);
	public static final Tiles TILE_BARRICADE = new BarricadeTile("src/img/blueGate.png", 2, 0);
	
	//protected BufferedImage texture;
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
	
	public void beforePlayerWalk(Player player){}

	public static Tiles getTile(int id) {
		return TILES[id];
	}
}
