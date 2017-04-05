package roombuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BuilderButton extends JButton implements ActionListener, MouseListener{

	public static final String[] TILES = {
			"res/textures/tile/grass1.png",
			"res/textures/tile/goal.png",
			"res/textures/tile/grass2.png",
			"res/textures/tile/wall.png",
			"res/textures/tile/barricade_blue.png",
			"res/textures/tile/barricade_green.png",
			"res/textures/tile/barricade_red.png",
			"res/textures/tile/barricade_yellow.png"
	};
	
	public final int x;
	public final int y;
	
	public int tileIndex = 0;
	
	public BuilderButton(int x, int y){
		super();
		this.x = x;
		this.y = y;
		this.addActionListener(this);
		this.addMouseListener(this);
		this.setLocation(x * BuilderPanel.TILE_SIZE, y * BuilderPanel.TILE_SIZE);
		this.setSize(BuilderPanel.TILE_SIZE, BuilderPanel.TILE_SIZE);
		this.setIcon(new ImageIcon(TILES[tileIndex]));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		increment(1);
	}
	
	private void increment(int amount){
		tileIndex = (tileIndex + amount) % TILES.length;
		if(tileIndex < 0){
			tileIndex = TILES.length - 1;
		}
		this.setIcon(new ImageIcon(TILES[tileIndex]));
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getButton());
		if (e.getButton() == 3) {
			increment(-1);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
