package com.tumilok.main;

import java.util.ArrayList;
import java.util.List;

public class Animal {

	private MapDirection direction;
	private Vector2d position;
	private int energy;
	private WorldMap map;
	private List<IPositionChangeObserver> observers;
	private Genes genes;
	
	public Animal(WorldMap map, Vector2d initialPosition, int startEnergy) {
		int directionNumber = (int) (Math.random() * (8));
		this.direction = MapDirection.NORTH.toMapDirection(directionNumber);
		this.position = initialPosition;
		this.map = map;
		this.observers = new ArrayList<>();
		this.energy = startEnergy;
		this.genes = new Genes();
	}

	public Animal(WorldMap map, Vector2d initialPosition, int energy, int[] genes) {
		int directionNumber = (int) (Math.random() * (8));
		direction = MapDirection.NORTH.toMapDirection(directionNumber);
		position = initialPosition;
		this.map = map;
		this.observers = new ArrayList<>();
		this.energy = energy;
		this.genes = new Genes(genes);
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

	public boolean equalsByEnergy(Animal other) {
		if (this != other)
			if (this.energy == other.energy)
				return true;
		return false;
	}

	public void changeDirection(){
		for (int i = 0; i < this.genes.chooseDirection(); i++)
			this.direction = this.direction.next();
	}
	
	public void move() {
		Vector2d oldPosition = this.position;

		Vector2d moveCords = this.position.add(this.direction.toUnitVector());

		if (moveCords.getX() > map.getUpperRight().getX()){
			moveCords = new Vector2d(map.getLowerLeft().getX(), moveCords.getY());
		}
		if (moveCords.getX() < map.getLowerLeft().getX()){
			moveCords = new Vector2d(map.getUpperRight().getX(), moveCords.getY());
		}
		if (moveCords.getY() > map.getUpperRight().getY()){
			moveCords = new Vector2d(moveCords.getX(), map.getLowerLeft().getY());
		}
		if (moveCords.getY() < map.getLowerLeft().getY()){
			moveCords = new Vector2d(moveCords.getX(), map.getUpperRight().getY());
		}

		this.position = moveCords;
		this.positionChanged(oldPosition);
		this.energy -= map.moveEnergy;
	}

	public Animal healthier(Animal thatAnimal) {
		if (this.energy >= thatAnimal.energy)
			return this;
		else
			return thatAnimal;
	}
	
	public void addObserver(IPositionChangeObserver observer) {
		this.observers.add(observer);
	}
	
	public void removeObserver(IPositionChangeObserver observer) {
		this.observers.remove(observer);
	}
	
	private void positionChanged(Vector2d oldPosition) {
		for (IPositionChangeObserver observer : this.observers) {
			observer.positionChanged(oldPosition, this.position, this);
		}
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getEnergy() {
		return this.energy;
	}

	public MapDirection getDirection() {
		return this.direction;
	}

	public Vector2d getPosition() {
		return this.position;
	}

	public int[] getGenes() {
		return this.genes.getGenes();
	}
}
