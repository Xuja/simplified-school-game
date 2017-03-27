package game;

import java.io.IOException;

import display.Display;
import entity.Player;
import input.InputManager;
import room.Room;
import tile.Tiles;

public class Game {

	private InputManager inputManager;
	
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
    	inputManager = new InputManager();
    	
    	display = new Display(title, width, height);
    	display.setVisible(true);
    	display.addKeyListener(inputManager);
    	
    	theRoom = new Room(this, 10);
    	theRoom.loadEntities();
    	inputManager.addActionListener(theRoom.getPlayer());
    	
    	try {
			theRoom.loadTiles("src/tiles.dat");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
    	
    	theRoom.pushTilesToDisplay(display);
    	display.repaint();
    }
    
    
    public void drawPlayer(Player player){
    	System.out.println("draw player");
    	display.paintPlayer(player);
    }
    
    public void replaceTile(Tiles tile, int x, int y){
    	display.replaceTile(tile, x, y);
    }
}
