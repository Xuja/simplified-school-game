package tile;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import display.Display;


public class Tiles extends JLabel{
	
	private Display display;
	private static final int TILE_WIDTH = 60, TILE_HEIGHT = 60;
	private static final int TILE_SIZE = 60;
	
	public static Tiles[] tiles = new Tiles [256];
	public static Tiles grassTile = new GrassTile(0);
	public static Tiles wallTile = new WallTile(1);
	public static Tiles barricadeTile = new BarricadeTile(2);
	
	protected BufferedImage texture;
	protected final int id;
	
	
	public Tiles(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}
	
	public Display getDisplay(){
		return display;
	}

	public int getTILE_SIZE() {
		return TILE_SIZE;
	}
	
	
}
