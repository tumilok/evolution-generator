package com.tumilok.main;

import java.util.*;

public class WorldMap implements IPositionChangeObserver {
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final Vector2d jungleLowerLeft;
    private final Vector2d jungleUpperRight;
    private final int width;
    private final int height;
    public final int moveEnergy;
    public final int plantEnergy;
    private int maxSteppeGrass;
    private int maxJungleGrass;

    private List<Vector2d> animalsCords = new ArrayList<>();
    private List<Vector2d> grassCords = new ArrayList<>();
    private Map<Vector2d, List<Animal> > animals = new HashMap<>();
    private List<Animal> animalsAndCords = new ArrayList<>();

    private MapVisualizer picture = new MapVisualizer(this);

    public WorldMap(int width, int height, int moveEnergy, int plantEnergy, double jungleRatio) {
        this.width = width;
        this.height = height;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.jungleLowerLeft = new Vector2d((width - (int)(width*jungleRatio))/2, (height - (int)(height*jungleRatio))/2);
        this.jungleUpperRight = new Vector2d((width + (int)(width*jungleRatio))/2 - 1, (height + (int)(height*jungleRatio))/2 - 1);
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.maxJungleGrass = (int)(width * height * jungleRatio * jungleRatio);
        this.maxSteppeGrass = (width * height) - maxJungleGrass;
    }

    public String toString() { return picture.draw(lowerLeft, upperRight); }

    public boolean place(Animal animal) {
        if (!animal.getPosition().precedes(upperRight) || !animal.getPosition().follows(lowerLeft)) {
            throw new IllegalArgumentException("Out of borders");
        }

        List<Animal> tempAnimals = animals.get(animal.getPosition());
        if (tempAnimals == null) {
            tempAnimals = new ArrayList<>();
            animalsCords.add(animal.getPosition());
        }

        tempAnimals.add(animal);
        animals.put(animal.getPosition(), tempAnimals);
        animal.addObserver(this);
        animalsAndCords.add(animal);

        return true;
    }

    public List<Animal> objectAt(Vector2d position) {
        return animals.get(position);
    }

    public boolean isOccupied(Vector2d position) {
        for (Vector2d vector2d : this.grassCords) {
            if (vector2d.equals(position))
                return true;
        }
        return animals.containsKey(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        List<Animal> oldAnimalsList = this.animals.get(oldPosition);
        if (oldAnimalsList.size() == 1) {
            this.animalsCords.remove(oldPosition);
            this.animals.remove(oldPosition);
        } else {
            oldAnimalsList.remove(animal);
        }

        List<Animal> newAnimalsList = animals.get(newPosition);
        if (newAnimalsList == null) {
            newAnimalsList = new ArrayList<>();
        }
        newAnimalsList.add(animal);
        this.animals.put(newPosition, newAnimalsList);
        if (!animalsCords.contains(newPosition)) {
            animalsCords.add(newPosition);
        }
    }

    private boolean belongsToJungle(Vector2d object) {
        return object.precedes(jungleUpperRight) && object.follows(jungleLowerLeft);
    }

    private void generateSteppeGrass() {
        int animalsInSteppe = 0;
        for (Vector2d position : animalsCords) {
            if (!belongsToJungle(position)) {
                animalsInSteppe++;
            }
        }
        if (this.maxSteppeGrass > animalsInSteppe) {
            Vector2d steppeGrassPosition;
            int x, y;
            do {
                x = (int) (Math.random() * (upperRight.getX() - lowerLeft.getX() + 1) + lowerLeft.getX());
                y = (int) (Math.random() * (upperRight.getY() - lowerLeft.getY() + 1) + lowerLeft.getY());
                steppeGrassPosition = new Vector2d(x, y);
            } while (isOccupied(steppeGrassPosition) || belongsToJungle(steppeGrassPosition));
            this.grassCords.add(steppeGrassPosition);
            this.maxSteppeGrass--;
        }
    }

    private void generateJungleGrass() {
        int animalsInJungle = 0;
        for (Vector2d position : animalsCords) {
            if (belongsToJungle(position)) {
                animalsInJungle++;
            }
        }
        if (this.maxJungleGrass > animalsInJungle) {
            Vector2d jungleGrassPosition;
            int x, y;
            do {
                x = (int) (Math.random() * (jungleUpperRight.getX() - jungleLowerLeft.getX() + 1)) + jungleLowerLeft.getX();
                y = (int) (Math.random() * (jungleUpperRight.getY() - jungleLowerLeft.getY() + 1)) + jungleLowerLeft.getY();
                jungleGrassPosition = new Vector2d(x, y);
            } while (isOccupied(jungleGrassPosition));
            this.grassCords.add(jungleGrassPosition);
            this.maxJungleGrass--;
        }
    }

    public void generateGrass() {
        generateJungleGrass();
        generateSteppeGrass();
    }

    private void deleteGrass(Vector2d position) {
        this.grassCords.remove(position);
    }

    public void eatGrass() {
        for (Vector2d position : animalsCords) {
            if (grassCords.contains(position)) {
                List<Animal> animals = this.animals.get(position);

                int i = 0;
                Animal currentAnimal = animals.get(0);
                Animal nextAnimal = null;
                Animal animalToAdd = currentAnimal;
                while (i + 1 < animals.size()) {
                    currentAnimal = animals.get(i);
                    nextAnimal = animals.get(i + 1);
                    animalToAdd = currentAnimal.healthier(nextAnimal);
                    i++;
                }

                List<Animal> finalAnimals = new ArrayList<>();
                finalAnimals.add(animalToAdd);

                for (Animal animal : animals) {
                    if (animalToAdd.equalsByEnergy(animal))
                        finalAnimals.add(animal);
                }

                int energy = (int)(this.plantEnergy / finalAnimals.size());
                for (Animal animal : finalAnimals) {
                    animal.setEnergy(animal.getEnergy() + energy);
                }
                this.deleteGrass(position);

                if (this.belongsToJungle(position)) {
                    maxJungleGrass++;
                } else {
                    maxSteppeGrass++;
                }
            }
        }
    }

    public void deleteDeadAnimals() {
        List<Animal> animalsToRemove = new ArrayList<>();
        for (Animal animal : animalsAndCords) {
            if (animal.getEnergy() <= 0) {
                List<Animal> animals = this.animals.get(animal.getPosition());

                if (animals.size() == 1) {
                    this.animals.remove(animal.getPosition());
                    animalsCords.remove(animal.getPosition());
                } else {
                    animals.remove(animal);
                }
                animalsToRemove.add(animal);
                animal.removeObserver(this);
            }
        }
        animalsAndCords.removeAll(animalsToRemove);
    }

    public int getWidth() { return this.width; }

    public int getHeight() { return this.height; }

    public Vector2d getLowerLeft() { return this.lowerLeft; }

    public Vector2d getUpperRight() { return this.upperRight; }

    public List<Vector2d> getGrassCords() { return this.grassCords; }

    public List<Vector2d> getAnimalsCords() { return this.animalsCords; }

    public List<Animal> getAnimalsAndCords() { return this.animalsAndCords; }

    public Map<Vector2d, List<Animal>> getAnimals() { return this.animals; }
}
