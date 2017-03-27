package display;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import entity.Player;
import tile.TileLabel;
import tile.Tiles;

public class Display extends JFrame{

	public static final int TILE_SIZE = 60;

	private JLayeredPane layeredPane;
	private JPanel panel;
	private JPanel playerPanel;
	
	private int width, height;
	public String title;

	private int rows;
	
	private JLabel playerLabel;

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

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setSize(rows * TILE_SIZE, rows * TILE_SIZE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(rows * TILE_SIZE, rows * TILE_SIZE);

		panel.setFocusable(false);

		/*Box box = new Box(BoxLayout.Y_AXIS);
		box.add(Box.createVerticalGlue());
		box.add(panel);
		box.add(Box.createVerticalGlue());*/

		playerPanel = new JPanel();
		playerPanel.setOpaque(false);
		playerPanel.setLayout(null);
		playerPanel.setSize(rows * TILE_SIZE, rows * TILE_SIZE);
		playerPanel.setVisible(true);
		playerPanel.setFocusable(false);
		
		playerLabel = new JLabel();
		playerPanel.add(playerLabel);
		
		layeredPane.add(panel, new Integer(0), 0);
		layeredPane.add(playerPanel, new Integer(1), 0);
		
		layeredPane.repaint();
		this.add(layeredPane);
	}

	public void setTile(Tiles tile, int x, int y){
		TileLabel tl = new TileLabel(tile);
		tl.setSize(TILE_SIZE, TILE_SIZE);
		tl.loadImage(TILE_SIZE);
		tl.setLocation(x * TILE_SIZE, y * TILE_SIZE);
		tl.setVisible(true);
		int index = x + y * 10;
		System.out.println(index);
		panel.add(tl, index);
	}
	
	public void repaint(){
		panel.repaint();
	}
	
	public void paintPlayer(Player player){
		playerLabel.setText("player");
		playerLabel.setSize(TILE_SIZE, TILE_SIZE);
		playerLabel.setLocation(player.getX() * TILE_SIZE, player.getY() * TILE_SIZE);
		layeredPane.repaint();
	}

	public void replaceTile(Tiles tile, int x, int y) {
		int index = x + (y * 10);
		System.out.println(index);
		panel.remove(index);
		setTile(tile, x, y);
	}
}
