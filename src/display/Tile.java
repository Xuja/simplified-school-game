package display;

import javax.swing.JLabel;


public class Tile extends JLabel{
	
	private Display display;
	private int TILE_SIZE = 60;
	
	public Tile(Display display, int TILE_SIZE){
		this.display = display;
		this.TILE_SIZE = TILE_SIZE;
	}
	
	public Display getDisplay(){
		return display;
	}

	public int getTILE_SIZE() {
		return TILE_SIZE;
	}

	public void setTILE_SIZE(int tILE_SIZE) {
		TILE_SIZE = tILE_SIZE;
	}
	
	
	
}
