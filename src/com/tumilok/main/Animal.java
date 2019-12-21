package com.tumilok.main;

import java.util.ArrayList;
import java.util.List;

public class Animal {

	public final int startEnergy;
	private int energy;
	private MapDirection direction;
	private Vector2d position;
	private List<IPositionChangeObserver> observers;
	private WorldMap map;
	private Genes genes;


	public Animal(WorldMap map, Vector2d initialPosition, int startEnergy) {
		this.startEnergy = startEnergy;
		this.energy = startEnergy;
		this.direction = MapDirection.NORTH.toMapDirection((int) (Math.random() * 8));
		this.position = initialPosition;
		this.observers = new ArrayList<>();
		this.map = map;
		this.genes = new Genes();
	}

	public Animal(WorldMap map, Vector2d initialPosition, int energy, int[] genes, int startEnergy) {
		this.startEnergy = startEnergy;
		this.energy = energy;
		this.direction = MapDirection.NORTH.toMapDirection((int) (Math.random() * 8));
		this.position = initialPosition;
		this.observers = new ArrayList<>();
		this.map = map;
		this.genes = new Genes(genes);

	}

	public void changeDirection(){
		for (int i = 0; i < this.genes.chooseDirection(); i++)
			this.direction = this.direction.next();
	}

	private Vector2d bordersCheck(Vector2d moveCords) {
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
		int [] secondParentGenes = Parent.getGenesArray();

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
			return this.energy == other.energy;
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

	public int getEnergy() { return this.energy; }

	public MapDirection getDirection() { return this.direction; }

	public Vector2d getPosition() { return this.position; }

	public int[] getGenesArray() { return this.genes.getGenes(); }

	public Genes getGenes() { return this.genes; }
}
