package display;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	private TileLabel[][] tiles;

	public Display(String title, int width, int height){
		this.title = title;
		this.rows = 10;
		this.width = rows * TILE_SIZE;
		this.height = rows * TILE_SIZE + 29;
		setDisplay();
		createDisplay();    	
	}

	public void setDisplay() {
		tiles = new TileLabel[rows][rows];
		try {
			loadTiles("src/tiles.dat");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < rows; j++){
				panel.add(tiles[i][j]);
			}
		}
	}

	public void setTile(Tiles tile, int x, int y){
		if(isTileWithinRange(x, y)){
			TileLabel tl = new TileLabel(tile);
			tl.setSize(TILE_SIZE, TILE_SIZE);
			tl.loadImage(TILE_SIZE);
			tl.setLocation(x * TILE_SIZE, y * TILE_SIZE);
			tl.setVisible(true);
			tiles[x][y] = tl;
		}
	}

	public boolean isTileWithinRange(int x, int y){
		return x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length;
	}

	public void loadTiles(String path) throws IOException{
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if(reader != null){

			String line;
			int index = 0;

			while((line = reader.readLine()) != null){
				String[] tileids = line.split(" ");
				if(tileids.length != tiles.length){
					System.err.println("Invalid width while reading the layout");
					return;
				}

				for(int i = 0; i < tileids.length; i++){
					int id = Integer.valueOf(tileids[i]);
					Tiles tile = Tiles.getTile(id);
					System.out.println(tile);
					int x = index % rows;
					int y = index / rows;
					setTile(tile, x, y);
					index++;
				}
			}

			reader.close();
		}
		else{
			System.err.println("Failed to intstantiate a buffered reader for file '" + path + "'!");
		}
	}
}
