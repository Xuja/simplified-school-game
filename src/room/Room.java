package room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import display.Display;
import entity.Player;
import game.Game;
import tile.Tiles;

public class Room {

	private Game theGame;
	
	private Player thePlayer;
	
	private Tiles[][] tiles;
	private final int roomSize;
	
	public Room(Game game, int size){
		this.theGame = game;
		this.roomSize = size;
		tiles = new Tiles[roomSize][roomSize];
	}
	
	public Tiles getTile(int x, int y){
		return tiles[x][y];
	}
	
	public void setTile(Tiles tile, int x, int y, boolean shouldRedraw){
		if(isTileWithinRange(x, y)){
			tiles[x][y] = tile;
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
					int x = index % roomSize;
					int y = index / roomSize;
					setTile(tile, x, y, false);
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
		thePlayer = new Player(this, 1, 1, "src/img/player.png");
	}
	
	public Player getPlayer(){
		return thePlayer;
	}
	
	public void pushTilesToDisplay(Display display){
		for(int j = 0; j < roomSize; j++){
			for(int i = 0; i < roomSize; i++){
				display.setTile(tiles[i][j], i, j);
			}
		}
	}
	
	public void replaceTile(Tiles tile, int x, int y){
		tiles[x][y] = tile;
		theGame.replaceTile(tile, x, y);
	}
	
	public void onPlayerMoved(){
		theGame.drawPlayer(thePlayer);
	}
}
