package tile;

import entity.EnumKey;
import entity.Player;
import room.GameRoom;

public class BarricadeTile extends Tiles {

	private final EnumKey key;

	public BarricadeTile(String texture, int id, EnumKey key) {
		super(texture, id);
		this.key = key;
	}

	@Override
	public boolean isSolid(){
		return true;
	}

	@Override
	public void beforePlayerWalkTo(GameRoom room, Player player, int x, int y){
		if(key == player.getCurrentKey()){
			room.playSound("unlockChest");
			room.replaceTile(TILE_WITHERED_GRASS, x, y);
		}
		if(key != player.getCurrentKey()){
			if(player.getCurrentKey() != null){
				room.playSound("wrong");
			}
		}
	}
}
