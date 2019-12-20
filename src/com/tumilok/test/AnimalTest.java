package com.tumilok.test;

import com.tumilok.main.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {
	
	@Test
	void testDirection() {
		WorldMap map = new WorldMap(100, 100, 2, 10, 0.2);
		
		Animal animal = new Animal(map, new Vector2d(5, 5), 100);
		
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.EAST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.SOUTH, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());

		Animal animal2 = new Animal(map, new Vector2d(55, 86), 100);

		Assertions.assertEquals(MapDirection.NORTH, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection();
		Assertions.assertEquals(MapDirection.NORTH, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection();
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection();
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection();
		Assertions.assertEquals(MapDirection.WEST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection();
		Assertions.assertEquals(MapDirection.EAST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection();
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection();
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection();
		Assertions.assertEquals(MapDirection.SOUTH, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());

	}

	@Test
	void testPosition() {
		WorldMap map = new WorldMap(100, 100, 2, 10, 0.2);
		Animal animal = new Animal(map, new Vector2d(45, 93), 100);
		
		Assertions.assertEquals(new Vector2d(45, 93), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(45, 94), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.changeDirection();
		animal.move();
		Assertions.assertEquals(new Vector2d(46,95), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal.getDirection());
		animal.changeDirection();
		animal.move();
		Assertions.assertEquals(new Vector2d(47,95), animal.getPosition());
		Assertions.assertEquals(MapDirection.EAST, animal.getDirection());
		animal.changeDirection();
		animal.move();
		Assertions.assertEquals(new Vector2d(48,94), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal.getDirection());
		animal.changeDirection();
		animal.move();
		Assertions.assertEquals(new Vector2d(48,93), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH, animal.getDirection());
		animal.changeDirection();
		animal.move();
		Assertions.assertEquals(new Vector2d(47,92), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal.getDirection());
		animal.changeDirection();
		animal.move();
		Assertions.assertEquals(new Vector2d(46,92), animal.getPosition());
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.changeDirection();
		animal.move();
		Assertions.assertEquals(new Vector2d(45,93), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal.getDirection());
		animal.changeDirection();
	}

	@Test
	void testBorders() {
		WorldMap map = new WorldMap(100,100, 2, 10, 0.2);
		
		Animal animal = new Animal(map, new Vector2d(0,0), 100);

		Assertions.assertEquals(new Vector2d(0,0), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(99, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.EAST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.EAST, animal.getDirection());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.SOUTH, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 99), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH, animal.getDirection());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(99, 99), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal.getDirection());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal.getDirection());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(99, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 99), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal.getDirection());
		animal.changeDirection();
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(99, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal.getDirection());
	}
}
