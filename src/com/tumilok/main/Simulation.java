package com.tumilok.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation implements ActionListener{

    private int delay;
    private int startEnergy;
    private WorldMap map;
    private Timer timer;

    public Simulation(WorldMap map, int startEnergy, int startNumberOfAnimals, int delay) {
        this.delay = delay;
        this.startEnergy = startEnergy;
        this.map = map;
        this.timer = new Timer(delay, this);

        for (int i = 0; i < startNumberOfAnimals; i++) {
            map.place(new Animal(map, new Vector2d((int) (Math.random() * map.getWidth()),
                    (int) (Math.random() * map.getHeight())), startEnergy));
        }
    }

    public void startTimer() {
        timer.start();
    }

    public void actionPerformed(ActionEvent actionEvent) {

    }

    public void simulateDay() throws InterruptedException {

        System.out.println((map.toString()));
        for (Animal animal : map.getAnimalsAndCords()) {
            System.out.println(animal.getGenes().toString());
        }
        while(true) {
            map.deleteDeadAnimals();
            for (Animal animal : map.getAnimalsAndCords()) {
                animal.changeDirection();
                animal.move();
            }
            map.eatGrass();
            map.generateGrass();
            System.out.println(map.toString());
            Thread.sleep(100);
        }
    }
}
