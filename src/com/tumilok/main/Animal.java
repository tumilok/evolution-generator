package com.tumilok.main;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {

	private MapDirection direction;
	private Vector2d position;
	private WorldMap map;
	private List<IPositionChangeObserver> observers;
	
	public Animal(WorldMap map, Vector2d initialPosition) {
		direction = MapDirection.NORTH;
		position = initialPosition;
		this.map = map;
		this.observers = new ArrayList<>();
	}
	
	public String toString() {
		switch(direction) {
			case NORTH:
				return "N";
			case NORTH_EAST:
				return "NE";
			case NORTH_WEST:
				return "NW";
			case SOUTH:
				return "S";
			case SOUTH_EAST:
				return "SE";
			case SOUTH_WEST:
				return "SW";
			case EAST:
				return "E";
			case WEST:
				return "W";
			default:
				return null;
		}
	}

	public void changeDirection(MoveDirection direction){
		for (int i = 0; i < direction.toInt(); i++)
			this.direction = this.direction.next();
	}
	
	public void move() {
		Vector2d oldPosition = this.position;

		Vector2d moveCords = this.direction.toUnitVector();
		moveCords = this.position.add(moveCords);

		if (moveCords.getX() > map.upperRight.getX()){
			moveCords = new Vector2d(map.lowerLeft.getX(), moveCords.getY());
		}
		if (moveCords.getX() < map.lowerLeft.getX()){
			moveCords = new Vector2d(map.upperRight.getX(), moveCords.getY());
		}
		if (moveCords.getY() > map.upperRight.getY()){
			moveCords = new Vector2d(moveCords.getX(), map.lowerLeft.getY());
		}
		if (moveCords.getY() < map.lowerLeft.getY()){
			moveCords = new Vector2d(moveCords.getX(), map.upperRight.getY());
		}

		this.position = moveCords;
		positionChanged(oldPosition);
	}
	
	public MapDirection getDirection() {
		return direction;
	}
	
	public Vector2d getPosition() {
		return position;
	}
	
	public void addObserver(IPositionChangeObserver observer) {
		this.observers.add(observer);
	}
	
	public void removeObserver(IPositionChangeObserver observer) {
		this.observers.remove(observer);
	}
	
	private void positionChanged(Vector2d oldPosition) {
		for (IPositionChangeObserver obs : this.observers) {
			obs.positionChanged(oldPosition, this.position);
		}
	}
	

}
