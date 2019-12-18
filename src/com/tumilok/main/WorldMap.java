package com.tumilok.main;

import java.util.*;

public class WorldMap implements IPositionChangeObserver {
    final Vector2d lowerLeft = new Vector2d(0, 0);
    final Vector2d upperRight = new Vector2d(99, 99);

    private List<Animal> animals = new ArrayList<>();
    private Map<Vector2d, IMapElement> animalsCords = new HashMap<>();
    private MapVisualizer picture = new MapVisualizer(this);

    public String toString() {
        return picture.draw(lowerLeft, upperRight);
    }

    public boolean place(Animal animal) {
        if (!animal.getPosition().precedes(upperRight) || !animal.getPosition().follows(lowerLeft)) {
            throw new IllegalArgumentException("Position is occupied");
        }

        this.animalsCords.put(animal.getPosition(), animal);
        this.animals.add(animal);
        animal.addObserver(this);

        return true;
    }

    public IMapElement objectAt(Vector2d position) {
        return animalsCords.get(position);
    }

    public boolean isOccupied(Vector2d position) {
        return animalsCords.containsKey(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement tempAnimal = this.objectAt(oldPosition);
        this.animalsCords.remove(oldPosition);
        this.animalsCords.put(newPosition, tempAnimal);
    }

    /*
    public void generateGrass() {
        Vector2d grassPoss;
        int x,y;
        do {
            x = generate.nextInt((int) Math.sqrt(n*10)+1);
            y = generate.nextInt((int) Math.sqrt(n*10)+1);
            grassPoss=new Vector2d(x,y);
        }while (isOccupied(grassPoss));
        this.animalsCords.put(grassPoss, new Grass(grassPoss));
    }
     */

    public void deleteGrass(Vector2d position) {
        this.animalsCords.remove(position);
    }

}
