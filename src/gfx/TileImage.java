package gfx;

import java.awt.image.BufferedImage;

public class TileImage {
	
	private static final int WIDTH = 60, HEIGHT = 60;
	
	public static BufferedImage grassTile, wallTile, barricadeTile;
	
	public static void setImages(){
		Sprites spriteSheet = new Sprites(ImageLoader.loadImage("/spritesheet/spritesheet.png"));
		grassTile = spriteSheet.resize(0, 0);		
		
	}

}
