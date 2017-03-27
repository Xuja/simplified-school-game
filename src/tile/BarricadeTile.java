package tile;

import entity.Player;
import gfx.TileImage;

public class BarricadeTile extends Tiles {

	private final int keyID;
	
	public BarricadeTile(String texture, int id, int key) {
		super(texture, id);
		this.keyID = key;
	}

	@Override
	public boolean isSolid(){
		return true;
	}
	
	@Override
	public void beforePlayerWalk(Player player){
		if(player.getCurrentKey() == keyID){
			//TODO replace tile with empty tile
		}
	}
}
