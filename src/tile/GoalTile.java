package tile;

import entity.Player;
import room.GameRoom;

public class GoalTile extends Tiles {

	public GoalTile(String tileTexture, int id) {
		super(tileTexture, id);
	}

	@Override
	public void onPlayerWalkedTo(GameRoom room, Player player, int x, int y){
		room.playSound("drop");
		room.finishLevel();
	}
}
