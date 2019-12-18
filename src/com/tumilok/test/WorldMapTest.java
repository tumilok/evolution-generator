package com.tumilok.test;

import com.tumilok.main.Animal;
import com.tumilok.main.Vector2d;
import com.tumilok.main.WorldMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorldMapTest {

    @Test
    void testPlace() {
        WorldMap map = new WorldMap();

        Animal animal1 = new Animal(map, new Vector2d(0, 0));
        Animal animal2 = new Animal(map, new Vector2d(0, 0));

        Assertions.assertTrue(map.place(animal1));
        Assertions.assertTrue(map.place(animal2));
    }
}
