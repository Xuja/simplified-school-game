package game;

import java.io.IOException;

import display.Display;
import room.Room;

public class Game {

	private Display display;
    private int width, height;
    public String title;

    private Room theRoom;
    
    public Game(String title, int width, int height) {
    	this.title = title;
    	this.width = width;
    	this.height = height;
    
        init();  
    }
    
    private void init(){
    	display = new Display(title, width, height);
    	display.setVisible(true);
    	theRoom = new Room(10);
    	try {
			theRoom.loadTiles("src/tiles.dat");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
    	
    	theRoom.pushTilesToDisplay(display);
    	display.repaint();
    }
}
