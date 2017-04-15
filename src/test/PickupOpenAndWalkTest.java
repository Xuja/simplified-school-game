package test;

import org.junit.Assert;
import org.junit.Test;

import entity.Entity;
import entity.EnumKey;
import entity.Key;
import entity.Player;
import game.EnumDirection;
import room.GameRoom;
import tile.Tiles;

public class PickupOpenAndWalkTest {

	@Test
	public void test() throws InterruptedException {
		Tiles barricadeTile = Tiles.TILE_BARRICADE_BLUE;
		GameRoom room = new TestRoom(barricadeTile);
		Player player = new TestPlayer(room);
		EnumKey expectedKey = EnumKey.KEY_BLUE;
		Key key = new Key(room, 0, 0, expectedKey);
		room.addEntityToRoom(key);
		player.pickupKey();
		Assert.assertNotNull("Player did not pickup the key!", player.getCurrentKey());
		Assert.assertEquals("The key the player picked up is not the correct type!", expectedKey, player.getCurrentKey());
		Thread.sleep(1000);
		EnumDirection dir = EnumDirection.RIGHT;
		int expectedX = dir.x;
		int expectedY = dir.y;
		player.startMoving(dir);
		Assert.assertNotEquals("Barricade did not open!", barricadeTile, room.getTile(expectedX, expectedY));
		player.finalizeMove(dir);
		Assert.assertEquals("Player did not move correctly allong the x axis!", expectedX, player.getX());
		Assert.assertEquals("Player did not move correctly allong the y axis!", expectedY, player.getY());
	}
	
	private static class TestRoom extends GameRoom{

		public TestRoom(Tiles tile) {
			super(null, "");
			tiles = new Tiles[2][1];
			tiles[0][0] = Tiles.TILE_GRASS;
			tiles[1][0] = tile;
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
		
		@Override
		public void replaceTile(Tiles tile, int x, int y) {
			tiles[x][y] = tile;
		}
	}
	
	private static class TestPlayer extends Player{

		public TestPlayer(GameRoom room) {
			super(room);
		}
		
	}
}
