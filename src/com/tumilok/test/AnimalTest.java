package com.tumilok.test;

import com.tumilok.main.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {
	
	@Test
	void testDirection() {
		WorldMap map = new WorldMap();
		
		Animal animal = new Animal(map, new Vector2d(5, 5));
		
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection(MoveDirection.ZERO);
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection(MoveDirection.ONE);
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection(MoveDirection.TWO);
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection(MoveDirection.THREE);
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection(MoveDirection.FOUR);
		Assertions.assertEquals(MapDirection.EAST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection(MoveDirection.FIVE);
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection(MoveDirection.SIX);
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());
		animal.changeDirection(MoveDirection.SEVEN);
		Assertions.assertEquals(MapDirection.SOUTH, animal.getDirection());
		Assertions.assertEquals(new Vector2d(5, 5), animal.getPosition());

		Animal animal2 = new Animal(map, new Vector2d(55, 86));

		Assertions.assertEquals(MapDirection.NORTH, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection(MoveDirection.ZERO);
		Assertions.assertEquals(MapDirection.NORTH, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection(MoveDirection.ONE);
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection(MoveDirection.TWO);
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection(MoveDirection.THREE);
		Assertions.assertEquals(MapDirection.WEST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection(MoveDirection.FOUR);
		Assertions.assertEquals(MapDirection.EAST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection(MoveDirection.FIVE);
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection(MoveDirection.SIX);
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());
		animal2.changeDirection(MoveDirection.SEVEN);
		Assertions.assertEquals(MapDirection.SOUTH, animal2.getDirection());
		Assertions.assertEquals(new Vector2d(55, 86), animal2.getPosition());

	}

	@Test
	void testPosition() {
		WorldMap map = new WorldMap();
		Animal animal = new Animal(map, new Vector2d(45, 93));
		
		Assertions.assertEquals(new Vector2d(45, 93), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(45, 94), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.changeDirection(MoveDirection.ONE);
		animal.move();
		Assertions.assertEquals(new Vector2d(46,95), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal.getDirection());
		animal.changeDirection((MoveDirection.ONE));
		animal.move();
		Assertions.assertEquals(new Vector2d(47,95), animal.getPosition());
		Assertions.assertEquals(MapDirection.EAST, animal.getDirection());
		animal.changeDirection((MoveDirection.ONE));
		animal.move();
		Assertions.assertEquals(new Vector2d(48,94), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal.getDirection());
		animal.changeDirection((MoveDirection.ONE));
		animal.move();
		Assertions.assertEquals(new Vector2d(48,93), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH, animal.getDirection());
		animal.changeDirection((MoveDirection.ONE));
		animal.move();
		Assertions.assertEquals(new Vector2d(47,92), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal.getDirection());
		animal.changeDirection((MoveDirection.ONE));
		animal.move();
		Assertions.assertEquals(new Vector2d(46,92), animal.getPosition());
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.changeDirection((MoveDirection.ONE));
		animal.move();
		Assertions.assertEquals(new Vector2d(45,93), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal.getDirection());
		animal.changeDirection((MoveDirection.ONE));
	}

	@Test
	void testBorders() {
		WorldMap map = new WorldMap();
		
		Animal animal = new Animal(map, new Vector2d(0,0));

		Assertions.assertEquals(new Vector2d(0,0), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.changeDirection(MoveDirection.SIX);
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(99, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.changeDirection(MoveDirection.FOUR);
		Assertions.assertEquals(MapDirection.EAST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.EAST, animal.getDirection());
		animal.changeDirection(MoveDirection.TWO);
		Assertions.assertEquals(MapDirection.SOUTH, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 99), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH, animal.getDirection());
		animal.changeDirection(MoveDirection.FOUR);
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH, animal.getDirection());
		animal.changeDirection(MoveDirection.FIVE);
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(99, 99), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH_WEST, animal.getDirection());
		animal.changeDirection(MoveDirection.FOUR);
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH_EAST, animal.getDirection());
		animal.changeDirection(MoveDirection.FIVE);
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(99, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.WEST, animal.getDirection());
		animal.changeDirection(MoveDirection.FIVE);
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(0, 99), animal.getPosition());
		Assertions.assertEquals(MapDirection.SOUTH_EAST, animal.getDirection());
		animal.changeDirection(MoveDirection.FOUR);
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal.getDirection());
		animal.move();
		Assertions.assertEquals(new Vector2d(99, 0), animal.getPosition());
		Assertions.assertEquals(MapDirection.NORTH_WEST, animal.getDirection());
	}
}
