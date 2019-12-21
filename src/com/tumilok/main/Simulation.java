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
                position = new Vector2d((int) (Math.random() * map.getWidth()),
                        (int) (Math.random() * map.getHeight()));
            }
            map.place(new Animal(map, position, startEnergy));
        }
    }

    public void simulateDay() throws InterruptedException {
        System.out.println((map.toString()));
        while(true) {
            map.deleteDeadAnimals();
            int i = 1;
            for (Animal animal : map.getAnimalsAndCords()) {
                animal.changeDirection();
                animal.move();
                System.out.println(i + " " + animal.getEnergy() + " " + animal.getGenes().toString());
                i++;
            }
            map.eatGrass();
            map.reproduceAnimals();
            map.generateGrass();
            System.out.println(map.toString());
            Thread.sleep(delay);
        }
    }
}
