package test;

import org.junit.Assert;
import org.junit.Test;

import entity.Entity;
import entity.EntityRegistry;
import entity.Player;

public class EntityCreationTest {

	@Test
	public void test() {
		EntityRegistry.registerEntity("player", Player.class);
		Entity entity = EntityRegistry.createNewEntity("player", null);
		Assert.assertNull("No entity was created!", entity);
	}

}
