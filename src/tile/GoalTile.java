package tile;

import entity.Player;
import room.Room;

public class GoalTile extends Tiles {

	public GoalTile(String tileTexture, int id) {
		super(tileTexture, id);
	}

	@Override
	public void onPlayerWalkedTo(Room room, Player player, int x, int y){
		System.out.println("Finished Game!");
		System.exit(0);
	}
}
