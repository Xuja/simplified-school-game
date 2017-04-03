package game;

import display.Display;
import input.InputManager;
import room.MenuRoom;
import room.Room;

public class Game {

	private InputManager inputManager;
	
	private Display display;
    public String title;

    private Room theRoom;
    
    public Game(String title) {
    	this.title = title;
    }
    
    public void init(){
    	inputManager = new InputManager();
    	
    	display = new Display(title);
    	display.setVisible(true);
    	display.addKeyListener(inputManager);
    	
    	theRoom = new MenuRoom(this);
    	theRoom.init();
    	
    	//theRoom.pushTilesToDisplay(display);
    	//display.repaint();
    	display.setPanel(theRoom.getPanel());
    }
    
    /*public void drawPlayer(Player player){
    	display.paintPlayer(player);
    }*/
    
    /*public void replaceTile(Tiles tile, int x, int y){
    	display.replaceTile(tile, x, y);
    }*/
    
    public void setRoom(Room room){
    	theRoom = room;
    	theRoom.init();
    	display.setPanel(room.getPanel());
    }
    
    public boolean canRun(){
    	return !display.isWindowClosing();
    }
    
    public void run(float deltaTime){
    	theRoom.update(deltaTime);
    	//theRoom.getPlayer().update(deltaTime);
    	//this.drawPlayer(theRoom.getPlayer());
    }
    
    public InputManager getInputManager(){
    	return inputManager;
    }
}
