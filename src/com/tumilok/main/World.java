package com.tumilok.main;

public class World {

	private final int width;
	private final int height;
	private final int startEnergy;
	private final int moveEnergy;
	private final int plantEnergy;
	private final double jungleRatio;
	private final int startNumberOfAnimals = 5;
	private int delay = 1000;

	ReadParameters readParameters;
	WorldMap worldMap;
	Simulation simulation;

	public World() {
		this.readParameters = new ReadParameters();

		this.width = readParameters.getWidth();
		this.height = readParameters.getHeight();
		this.startEnergy = readParameters.getStartEnergy();
		this.moveEnergy = readParameters.getMoveEnergy();
		this.plantEnergy = readParameters.getPlantEnergy();
		this.jungleRatio = readParameters.getJungleRatio();

		worldMap = new WorldMap(this.width, this.height, this.moveEnergy, this.plantEnergy, this.jungleRatio);
		simulation = new Simulation(worldMap, this.startEnergy, this.startNumberOfAnimals, delay);
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