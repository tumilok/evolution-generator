package com.tumilok.test;

import com.tumilok.main.MapDirection;
import com.tumilok.main.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapDirectionTest {

	@Test
	void testNext() {
		MapDirection northDirection = MapDirection.NORTH;
		MapDirection northEastDirection = MapDirection.NORTH_EAST;
		MapDirection eastDirection = MapDirection.EAST;
		MapDirection southEastDirection = MapDirection.SOUTH_EAST;
		MapDirection southDirection = MapDirection.SOUTH;
		MapDirection southWestDirection = MapDirection.SOUTH_WEST;
		MapDirection westDirection = MapDirection.WEST;
		MapDirection northWestDirection = MapDirection.NORTH_WEST;
		
		Assertions.assertEquals(MapDirection.NORTH_EAST, northDirection.next());
		Assertions.assertEquals(MapDirection.EAST, northEastDirection.next());
		Assertions.assertEquals(MapDirection.SOUTH_EAST, eastDirection.next());
		Assertions.assertEquals(MapDirection.SOUTH, southEastDirection.next());
		Assertions.assertEquals(MapDirection.SOUTH_WEST, southDirection.next());
		Assertions.assertEquals(MapDirection.WEST, southWestDirection.next());
		Assertions.assertEquals(MapDirection.NORTH_WEST, westDirection.next());
		Assertions.assertEquals(MapDirection.NORTH, northWestDirection.next());
	}
	
	@Test
	void testPrevious() {
		MapDirection northDirection = MapDirection.NORTH;
		MapDirection northEastDirection = MapDirection.NORTH_EAST;
		MapDirection eastDirection = MapDirection.EAST;
		MapDirection southEastDirection = MapDirection.SOUTH_EAST;
		MapDirection southDirection = MapDirection.SOUTH;
		MapDirection southWestDirection = MapDirection.SOUTH_WEST;
		MapDirection westDirection = MapDirection.WEST;
		MapDirection northWestDirection = MapDirection.NORTH_WEST;

		Assertions.assertEquals(MapDirection.NORTH_WEST, northDirection.previous());
		Assertions.assertEquals(MapDirection.WEST, northWestDirection.previous());
		Assertions.assertEquals(MapDirection.SOUTH_WEST, westDirection.previous());
		Assertions.assertEquals(MapDirection.SOUTH, southWestDirection.previous());
		Assertions.assertEquals(MapDirection.SOUTH_EAST, southDirection.previous());
		Assertions.assertEquals(MapDirection.EAST, southEastDirection.previous());
		Assertions.assertEquals(MapDirection.NORTH_EAST, eastDirection.previous());
		Assertions.assertEquals(MapDirection.NORTH, northEastDirection.previous());
	}

	@Test
	void testToUnitVector() {
		Assertions.assertEquals(new Vector2d(0, 1), MapDirection.NORTH.toUnitVector());
		Assertions.assertEquals(new Vector2d(1, 1), MapDirection.NORTH_EAST.toUnitVector());
		Assertions.assertEquals(new Vector2d(1, 0), MapDirection.EAST.toUnitVector());
		Assertions.assertEquals(new Vector2d(1, -1), MapDirection.SOUTH_EAST.toUnitVector());
		Assertions.assertEquals(new Vector2d(0, -1), MapDirection.SOUTH.toUnitVector());
		Assertions.assertEquals(new Vector2d(-1, -1), MapDirection.SOUTH_WEST.toUnitVector());
		Assertions.assertEquals(new Vector2d(-1, 0), MapDirection.WEST.toUnitVector());
		Assertions.assertEquals(new Vector2d(-1, 1), MapDirection.NORTH_WEST.toUnitVector());
	}
}