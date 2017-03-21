package display;

import javax.swing.JLabel;


public class Tile extends JLabel{
	
	private Display display;
	
	public Tile(Display display){
		this.display = display;
	}
	
	public Display getDisplay(){
		return display;
	}
	
}
