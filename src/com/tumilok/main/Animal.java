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
	public final int startEnergy;

	public Animal(WorldMap map, Vector2d initialPosition, int startEnergy) {
		this.direction = MapDirection.NORTH.toMapDirection((int) (Math.random() * 8));
		this.position = initialPosition;
		this.map = map;
		this.observers = new ArrayList<>();
		this.energy = startEnergy;
		this.genes = new Genes();
		this.startEnergy = startEnergy;
	}

	public Animal(WorldMap map, Vector2d initialPosition, int energy, int[] genes, int startEnergy) {
		this.direction = MapDirection.NORTH.toMapDirection((int) (Math.random() * 8));
		this.position = initialPosition;
		this.map = map;
		this.observers = new ArrayList<>();
		this.energy = energy;
		this.genes = new Genes(genes);
		this.startEnergy = startEnergy;
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

	public void changeDirection(){
		for (int i = 0; i < this.genes.chooseDirection(); i++)
			this.direction = this.direction.next();
	}

	private Vector2d bordersCheck(Vector2d moveCords) {
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
		return moveCords;
	}

	public void move() {
		Vector2d oldPosition = this.position;
		Vector2d moveCords = this.position.add(this.direction.toUnitVector());

		this.position = bordersCheck(moveCords);
		this.positionChanged(oldPosition);
		this.energy -= map.moveEnergy;
	}

	public void reproduce(Animal Parent, List<Animal> animalsToAdd) {
		int []genes = new int[this.getGenesArray().length];

		int[] divideIndexes = new int[4];
		divideIndexes[3] = genes.length;
		divideIndexes[1] = (int) (Math.random() * 29 + 1);
		while (divideIndexes[2] <= divideIndexes[1])
			divideIndexes[2] = (int) (Math.random() * 30 + 1);

		int [] firstParentGenes = this.getGenesArray();
		int [] secondParentGenes = this.getGenesArray();

		int partsFromFirstParent = 0;
		int partsFromSecondParent = 0;

		int i = 0;

		while (i < 3) {
			var length = divideIndexes[i + 1] - divideIndexes[i];
			if (partsFromFirstParent == 2) {
				System.arraycopy(firstParentGenes, divideIndexes[i], genes, divideIndexes[i], length);
			}
			else if (partsFromSecondParent == 2) {
				System.arraycopy(secondParentGenes, divideIndexes[i], genes, divideIndexes[i], length);
			}
			else {
				if ((int) (Math.random() * 2) == 0) {
					System.arraycopy(firstParentGenes, divideIndexes[i], genes, divideIndexes[i], length);
					partsFromFirstParent++;
				} else {
					System.arraycopy(secondParentGenes, divideIndexes[i], genes, divideIndexes[i], length);
					partsFromSecondParent++;
				}
			}
			i++;
		}

		Vector2d babyAnimalPosition = map.choosePositionToPlace(position);
		Animal babyAnimal = new Animal(map, babyAnimalPosition, this.energy/4 + Parent.getEnergy()/4, genes, startEnergy);
		animalsToAdd.add(babyAnimal);
	}

	public boolean equalsByEnergy(Animal other) {
		if (this != other)
			if (this.energy == other.energy)
				return true;
		return false;
	}

	public Animal healthier(Animal thatAnimal) {
		if (this.energy >= thatAnimal.energy)
			return this;
		else
			return thatAnimal;
	}

	private void positionChanged(Vector2d oldPosition) {
		for (IPositionChangeObserver observer : this.observers) {
			observer.positionChanged(oldPosition, this.position, this);
		}
	}
	
	public void addObserver(IPositionChangeObserver observer) {
		this.observers.add(observer);
	}
	
	public void removeObserver(IPositionChangeObserver observer) {
		this.observers.remove(observer);
	}

	public void setEnergy(int energy) {	this.energy = energy; }

	public int getEnergy() {
		return this.energy;
	}

	public MapDirection getDirection() {
		return this.direction;
	}

	public Vector2d getPosition() {
		return this.position;
	}

	public int[] getGenesArray() {
		return this.genes.getGenes();
	}

	public Genes getGenes() { return this.genes; }

}
