package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gfx.TileImage;
import tile.Tiles;

public class Display extends JFrame{
	
    private JPanel panel;
	
	private int width, height;
	public String title;
	
	private final int TILE_SIZE = 60;
	private int rows;
    private Tiles[][] tiles;
	
	public Display(String title, int width, int height){
		this.title = title;
    	this.width = width;
    	this.height = height;
    	this.rows = 10;
    	setDisplay();
    	createDisplay();    	
	}
	
    public void setDisplay() {
        tiles = new Tiles[rows][rows];
        for (int rij = 0; rij < rows; rij++) {
            for (int kolom = 0; kolom < rows; kolom++) {
            	//tiles[rij][kolom] = new Tiles(TILE_SIZE);
                tiles[rij][kolom] = new Tiles(TileImage.grassTile, 0);
            }
        }
    }
    
    public void setRows(int row) {
        for (int i = 0; i < this.rows; i++) {
        	panel.add(tiles[row][i]);
            tiles[row][i].setSize(TILE_SIZE, TILE_SIZE);
            tiles[row][i].setLocation(TILE_SIZE * i, TILE_SIZE * row);
            tiles[row][i].setIcon(new ImageIcon(new ImageIcon("src/img/grass1.png").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT)));
            
            //uncomment om border van de tiles te zien.
           //tiles[row][i].setBorder(BorderFactory.createLineBorder(Color.black));

        }
        
        
        //hier nog aparte methode voorschrijven en in andere klasse
        tiles[1][1].setIcon(new ImageIcon(new ImageIcon("src/img/blueGate.png").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT)));
        tiles[1][2].setIcon(new ImageIcon(new ImageIcon("src/img/yellowDoor.png").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT)));
        tiles[1][3].setIcon(new ImageIcon(new ImageIcon("src/img/greenGate.png").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT)));
        tiles[1][4].setIcon(new ImageIcon(new ImageIcon("src/img/orangeGate.png").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT)));
        tiles[2][8].setIcon(new ImageIcon(new ImageIcon("src/img/wall.png").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT)));
        
        //moet nog een tile erachter als background?
        tiles[2][2].setIcon(new ImageIcon(new ImageIcon("src/img/yellowKey.png").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT)));
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
        
        //If you dont want any borders uncomment
        //this.pack();
        
        for (int i = 0; i < this.rows; i++) {
            setRows(i);
        }
    }
}
