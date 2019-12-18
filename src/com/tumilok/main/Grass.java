package com.tumilok.main;

public class Grass implements IMapElement {

	private Vector2d position;
	
	public Grass(Vector2d position) {
		this.position = position;
	}
	
	public String toString() {
		return "*";
	}
	
	public Vector2d getPosition() {
		return position;
	}
}
