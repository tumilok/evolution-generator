package com.tumilok.main;

public enum MapDirection {
	NORTH,
	NORTH_EAST,
	EAST,
	SOUTH_EAST,
	SOUTH,
	SOUTH_WEST,
	WEST,
	NORTH_WEST;
	
	public String toString() {
		switch(this) {
			case NORTH:
				return "NORTH";
			case NORTH_EAST:
				return "NORTH EAST";
			case NORTH_WEST:
				return "NORTH WEST";
			case SOUTH:
				return "SOUTH";
			case SOUTH_EAST:
				return "SOUTH EAST";
			case SOUTH_WEST:
				return "SOUTH WEST";
			case EAST:
				return "EAST";
			case WEST:
				return "WEST";
			default:
				return null;
		}
	}
	
	public MapDirection next() {
		switch(this) {
			case NORTH:
				return NORTH_EAST;
			case NORTH_EAST:
				return EAST;
			case EAST:
				return SOUTH_EAST;
			case SOUTH_EAST:
				return SOUTH;
			case SOUTH:
				return SOUTH_WEST;
			case SOUTH_WEST:
				return WEST;
			case WEST:
				return NORTH_WEST;
			case NORTH_WEST:
				return NORTH;
			default:
				return null;
		}
	}
	
	public MapDirection previous() {
		switch(this) {
			case NORTH:
				return NORTH_WEST;
			case NORTH_WEST:
				return WEST;
			case WEST:
				return SOUTH_WEST;
			case SOUTH_WEST:
				return SOUTH;
			case SOUTH:
				return SOUTH_EAST;
			case SOUTH_EAST:
				return EAST;
			case EAST:
				return NORTH_EAST;
			case NORTH_EAST:
				return NORTH;
			default:
				return null;
		}
	}
	
	public Vector2d toUnitVector() {
		switch(this) {
			case NORTH:
				return new Vector2d(0, 1);
			case NORTH_WEST:
				return new Vector2d(-1, 1);
			case WEST:
				return new Vector2d(-1, 0);
			case SOUTH_WEST:
				return new Vector2d(-1, -1);
			case SOUTH:
				return new Vector2d(0, -1);
			case SOUTH_EAST:
				return new Vector2d(1, -1);
			case EAST:
				return new Vector2d(1, 0);
			case NORTH_EAST:
				return new Vector2d(1, 1);
			default:
				return null;
		}
	}
}
