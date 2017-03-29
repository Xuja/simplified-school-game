package display;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import tile.Tiles;

@SuppressWarnings("serial")
public class TileLabel extends JLabel{

	private Tiles tile;
	
	public TileLabel(Tiles tile){
		this.tile = tile;
	}
	public void setTile(Tiles tile){
		this.tile = tile;
	}
	
	public void loadImage(int size){
		this.setIcon(new ImageIcon(new ImageIcon(tile.getTileTexture()).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
	}
}
