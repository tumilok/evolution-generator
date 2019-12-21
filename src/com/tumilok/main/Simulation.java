package com.tumilok.main;

public class Simulation {

    private WorldMap map;
    private int delay;

    public Simulation(WorldMap map, int startEnergy, int startNumberOfAnimals, int delay) {
        this.map = map;
        this.delay = delay;

        for (int i = 0; i < startNumberOfAnimals; i++) {
            Vector2d position = null;
            while (position == null || map.isOccupied(position)) {
                position = new Vector2d((int) (Math.random() * map.width),
                        (int) (Math.random() * map.height));
            }
            map.place(new Animal(map, position, startEnergy));
        }
    }

    public void simulateDay() throws InterruptedException {
        while(true) {
            System.out.println(map.toString());

            map.deleteDeadAnimals();
            int i = 1;
            for (Animal animal : map.getListOfAnimals()) {
                animal.changeDirection();
                animal.move();
                System.out.println(i + " " + animal.getEnergy() + " " + animal.getGenes().toString());
                i++;
            }
            map.eatGrass();
            map.reproduceAnimals();
            map.generateGrass();
            Thread.sleep(delay);
        }
    }
}
