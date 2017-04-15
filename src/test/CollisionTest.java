package test;

import org.junit.Assert;
import org.junit.Test;

import entity.Entity;
import entity.Player;
import game.EnumDirection;
import room.GameRoom;
import tile.Tiles;

public class CollisionTest {

	@Test
	public void test() {
		GameRoom room = new TestRoom();
		Player player = new TestPlayer(room);
		
		EnumDirection moveDirection = EnumDirection.RIGHT;
		player.startMoving(moveDirection);
		player.finalizeMove(moveDirection);
		int expectedX = 0;
		int expectedY = 0;
		Assert.assertEquals("Player did not move correctly allong the x axis!", expectedX, player.getX());
		Assert.assertEquals("Player did not move correctly allong the y axis!", expectedY, player.getY());
	}

	private static class TestRoom extends GameRoom{

		public TestRoom() {
			super(null, "");
			tiles = new Tiles[2][1];
			tiles[0][0] = Tiles.TILE_GRASS;
			tiles[1][0] = Tiles.TILE_WALL;
		}
		
		@Override
		public void addEntityToRoom(Entity entity){
			entity.init();
			entityList.add(entity);
		}
		
		@Override
		public void removeEntityFromRoom(Entity entity){
			entityList.remove(entity);
		}
	}
	
	private static class TestPlayer extends Player{

		public TestPlayer(GameRoom room) {
			super(room);
		}
		
	}
}
