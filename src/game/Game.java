package game;

import display.Display;
import entity.EntityRegistry;
import entity.Key;
import entity.Player;
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
    	
    	EntityRegistry.registerEntity("player", Player.class);
    	EntityRegistry.registerEntity("key", Key.class);
    	
    	theRoom = new MenuRoom(this);
    	theRoom.init();
    	
    	display.setPanel(theRoom.getPanel());
    }

    public void setRoom(Room room){
    	if(theRoom != null){
    		theRoom.closeRoom();
    	}
    	theRoom = room;
    	theRoom.init();
    	display.setPanel(room.getPanel());
    }
    
    public boolean canRun(){
    	return !display.isWindowClosing();
    }
    
    public void run(float deltaTime){
    	theRoom.update(deltaTime);
    }
    
    public InputManager getInputManager(){
    	return inputManager;
    }
}
