package tile;

import entity.Player;
import room.GameRoom;

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
	
	public void beforePlayerWalkTo(GameRoom room, Player player, int x, int y){
		//if(keyID == player.getCurrentKey()){
			room.replaceTile(TILE_WITHERED_GRASS, x, y);
		//}
	}
}
