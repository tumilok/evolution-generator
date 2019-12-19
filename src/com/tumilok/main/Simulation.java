package com.tumilok.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Simulation implements ActionListener{

    int delay = 1000;
    WorldMap map;
    Timer timer;

    public Simulation() {
        map = new WorldMap(40, 40, 0.2, 2, 10);
        timer = new Timer(1000, this);

        map.place(new Animal(map, new Vector2d(3, 6), 100));
        map.place(new Animal(map, new Vector2d(35, 7), 100));
        map.place(new Animal(map, new Vector2d(20, 20), 100));
        map.place(new Animal(map, new Vector2d(0, 0), 100));
        map.place(new Animal(map, new Vector2d(0, 0), 100));
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
                System.out.println(animal.energy);
            }
            map.eatGrass();
            map.generateGrass();
            System.out.println(map.toString());
            Thread.sleep(100);
        }
    }
}
