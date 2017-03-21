package entity;

import java.awt.Rectangle;

public abstract class Entity {
	protected final int width = 50;
	protected final int height = 50;

	protected Rectangle bounds;
	
	public Entity( int width, int height){		
		bounds = new Rectangle(0, 0, width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}



}
