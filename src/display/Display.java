package display;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tile.TileLabel;
import tile.Tiles;

public class Display extends JFrame{

	public static final int TILE_SIZE = 60;

	private JPanel panel;

	private int width, height;
	public String title;

	private int rows;

	public Display(String title, int width, int height){
		this.title = title;
		this.rows = 10;
		this.width = rows * TILE_SIZE;
		this.height = rows * TILE_SIZE + 29;
		createDisplay();    	
	}

	public void createDisplay(){
		this.setTitle(title);
		this.setResizable(false);

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int monitorWidth = gd.getDisplayMode().getWidth();
		int monitorHeight = gd.getDisplayMode().getHeight();
		this.setBounds((monitorWidth - width) / 2, (monitorHeight - height) / 2, width, height);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(TILE_SIZE * rows, TILE_SIZE * rows));
		panel.setMinimumSize(new Dimension(TILE_SIZE * rows, TILE_SIZE * rows));
		panel.setMaximumSize(new Dimension(TILE_SIZE * rows, TILE_SIZE * rows));

		panel.setFocusable(false);

		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(Box.createVerticalGlue());
		box.add(panel);
		box.add(Box.createVerticalGlue());

		this.add(box);
	}

	public void setTile(Tiles tile, int x, int y){
		TileLabel tl = new TileLabel(tile);
		tl.setSize(TILE_SIZE, TILE_SIZE);
		tl.loadImage(TILE_SIZE);
		tl.setLocation(x * TILE_SIZE, y * TILE_SIZE);
		tl.setVisible(true);
		panel.add(tl);
	}
	
	public void repaint(){
		panel.repaint();
	}
}
