package room;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import display.Display;
import display.TileLabel;
import entity.Key;
import entity.Player;
import game.Game;
import tile.Tiles;

public class GameRoom extends Room {

	public static final int ROWS = 10;

	private Player thePlayer;
	private Key blueKey;

	private Tiles[][] tiles;
	private final int roomSize;

	private JLayeredPane layeredPane;
	private JPanel panel;
	private JPanel playerPanel;

	private JLabel playerLabel;

	private boolean isInitialized = false;

	public GameRoom(Game game, int size){
		super(game);
		this.roomSize = size;
		tiles = new Tiles[roomSize][roomSize];
	}

	@Override
	public void init() {

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setSize(ROWS * Display.TILE_SIZE, ROWS * Display.TILE_SIZE);
		layeredPane.setFocusable(false);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(ROWS * Display.TILE_SIZE, ROWS * Display.TILE_SIZE);

		panel.setFocusable(false);

		playerPanel = new JPanel();
		playerPanel.setOpaque(false);
		playerPanel.setLayout(null);
		playerPanel.setSize(ROWS * Display.TILE_SIZE, ROWS * Display.TILE_SIZE);
		playerPanel.setVisible(true);
		playerPanel.setFocusable(false);

		playerLabel = new JLabel();
		playerPanel.add(playerLabel);

		layeredPane.add(panel, new Integer(0), 0);
		layeredPane.add(playerPanel, new Integer(1), 0);

		layeredPane.repaint();

		loadEntities();
		theGame.getInputManager().addActionListener(thePlayer);
		try {
			loadTiles("src/tiles.dat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		isInitialized = true;
	}

	public Tiles getTile(int x, int y){
		return tiles[x][y];
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
					int x = index % roomSize;
					int y = index / roomSize;
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

	public void loadEntities(){
		thePlayer = new Player(this, 1, 1);
		blueKey = new Key(this, 1, 2);
	}

	public Player getPlayer(){
		return thePlayer;
	}
	
	public Key getKey(){
		return blueKey;
	}

	public void setTile(Tiles tile, int x, int y){
		if(isTileWithinRange(x, y)){
			tiles[x][y] = tile;
			TileLabel tl = new TileLabel(tile);
			tl.setSize(Display.TILE_SIZE, Display.TILE_SIZE);
			tl.loadImage(Display.TILE_SIZE);
			tl.setLocation(x * Display.TILE_SIZE, y * Display.TILE_SIZE);
			tl.setVisible(true);
			int index = x + y * 10;
			panel.add(tl, index);
		}
	}

	public void paintPlayer(Player player){
		String icon = player.getIcon();
		playerLabel.setIcon(new ImageIcon(new ImageIcon(icon).getImage().getScaledInstance(Display.TILE_SIZE, Display.TILE_SIZE, Image.SCALE_DEFAULT)));
		playerLabel.setSize(Display.TILE_SIZE, Display.TILE_SIZE);
		playerLabel.setLocation(player.getPlayerRenderPositionX(Display.TILE_SIZE), player.getPlayerRenderPositionY(Display.TILE_SIZE));
		playerPanel.repaint();
	}

	public void replaceTile(Tiles tile, int x, int y) {
		tiles[x][y] = tile;
		int index = x + (y * 10);
		TileLabel tileLabel = (TileLabel) panel.getComponent(index);
		tileLabel.setTile(tile);
		tileLabel.loadImage(Display.TILE_SIZE);
	}

	public void onPlayerMoved(){
		paintPlayer(thePlayer);
	}

	@Override
	public void update(float deltaTime) {
		if(isInitialized){
			thePlayer.update(deltaTime);
			paintPlayer(thePlayer);
		}
	}

	@Override
	public JLayeredPane getPanel() {
		return layeredPane;
	}
	
	public void finishLevel(){
		theGame.setRoom(new MenuRoom(theGame));
	}
}
