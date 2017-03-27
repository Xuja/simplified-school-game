package room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import display.Display;
import entity.Player;
import tile.Tiles;

public class Room {

	private Player thePlayer;
	
	private Tiles[][] tiles;
	private final int roomSize;
	
	public Room(int size){
		this.roomSize = size;
		tiles = new Tiles[roomSize][roomSize];
	}
	
	public Tiles getTile(int x, int y){
		return tiles[x][y];
	}
	
	public void setTile(Tiles tile, int x, int y){
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
					System.out.println(tile);
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
	
	public void pushTilesToDisplay(Display display){
		for(int i = 0; i < roomSize; i++){
			for(int j = 0; j < roomSize; j++){
				display.setTile(tiles[i][j], i, j);
			}
		}
	}
}
