package test;

import org.junit.Assert;
import org.junit.Test;

import entity.Entity;
import entity.EnumKey;
import entity.Key;
import entity.Player;
import room.GameRoom;
import tile.Tiles;

public class ReplaceKeyTest {
	
	@Test
	public void test() throws InterruptedException {
		
		EnumKey firstKey = EnumKey.KEY_BLUE;
		EnumKey secondKey = EnumKey.KEY_RED;
		
		GameRoom room = new TestRoom();
		Player player = new TestPlayer(room, secondKey);
		
		Key key = new Key(room, 0, 0, firstKey);
		room.addEntityToRoom(key);
		
		Assert.assertNotNull("No key found!", room.getEntityForPosition(0, 0, null));
		
		player.pickupKey();
		
		Assert.assertEquals("The key the player picked up is not the type we expected!", firstKey, player.getCurrentKey());
		Entity e = room.getEntityForPosition(0, 0, null);
		Assert.assertNotNull("No entity found, expected the replacement key!", e);
		if(!(e instanceof Key)){
			Assert.fail("The entity is not an instanceof 'Key'");
		}
		Key pickupKey = (Key)e;
		Assert.assertEquals("The key the player picked up is not the key that was expected!", secondKey, pickupKey.getKey());
	}
	
	private static class TestRoom extends GameRoom{

		public TestRoom() {
			super(null, "");
			tiles = new Tiles[1][1];
			tiles[0][0] = Tiles.TILE_GRASS;
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

		public TestPlayer(GameRoom room, EnumKey key) {
			super(room);
			this.currentKey = key;
		}
		
	}
}
