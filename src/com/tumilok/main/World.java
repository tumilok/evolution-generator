package com.tumilok.main;

public class World {

	private ReadParameters readParameters;
	private WorldMap worldMap;
	private Simulation simulation;

	public World() {
		this.readParameters = new ReadParameters();

		int width = readParameters.getWidth();
		int height = readParameters.getHeight();
		int startEnergy = readParameters.getStartEnergy();
		int moveEnergy = readParameters.getMoveEnergy();
		int plantEnergy = readParameters.getPlantEnergy();
		double jungleRatio = readParameters.getJungleRatio();

		worldMap = new WorldMap(width, height, moveEnergy, plantEnergy, jungleRatio);

		int startNumberOfAnimals = 20;
		int delay = 1000;
		simulation = new Simulation(worldMap, startEnergy, startNumberOfAnimals, delay);
	}

	public static void main(String[] args) {
		try {
			World world = new World();
			world.simulation.simulateDay();
        } catch (IllegalArgumentException | InterruptedException ex) {
            System.out.println(ex);
        }
	}
}