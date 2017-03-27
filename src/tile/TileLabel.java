package tile;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TileLabel extends JLabel{

	private Tiles tile;
	
	public TileLabel(Tiles tile){
		this.tile = tile;
	}
	
	public Tiles getTile(){
		return tile;
	}
	
	public void setTile(Tiles tile){
		this.tile = tile;
	}
	
	public void loadImage(int size){
		this.setIcon(new ImageIcon(new ImageIcon(tile.tileTexture).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
	}
}
