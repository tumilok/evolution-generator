package com.tumilok.main;

public class World {

	public static void main(String[] args) {	
		
		try {
			WorldMap map = new WorldMap();
            map.place(new Animal(map, new Vector2d(3, 6)));
            map.place(new Animal(map, new Vector2d(55, 70)));
            map.place(new Animal(map, new Vector2d(99, 99)));
            map.place(new Animal(map, new Vector2d(0, 0)));
            map.place(new Animal(map, new Vector2d(34, 89)));
            System.out.println(map.toString());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
	}
}