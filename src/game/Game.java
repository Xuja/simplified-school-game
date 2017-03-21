package game;

import javax.swing.JPanel;

import display.Display;
import display.Tile;

public class Game {

	private Display display;
    private int width, height;
    public String title;

    public Game(String title, int width, int height) {
    	this.title = title;
    	this.width = width;
    	this.height = height;
    
        init();  
    }
    
    private void init(){
    	display = new Display(title, width, height);
    	display.setVisible(true);
    }
	  
}
