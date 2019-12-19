package com.tumilok.main;

import java.util.List;

public class World {

	public static void main(String[] args) {	
		
		try {
		    Simulation simulation = new Simulation();
		    simulation.simulateDay();
        } catch (IllegalArgumentException | InterruptedException ex) {
            System.out.println(ex);
        }
	}
}