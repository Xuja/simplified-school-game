package gfx;

import java.awt.image.BufferedImage;

public class Sprites {
	private BufferedImage spriteSheet;
	private static final int WIDTH = 60, HEIGHT = 60;

	public Sprites(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public BufferedImage resize(int x, int y) {
		return spriteSheet.getSubimage(x, y, WIDTH, HEIGHT);
	}

}
