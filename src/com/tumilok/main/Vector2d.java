package com.tumilok.main;

public class Vector2d {

	public final int x, y;
	
	public Vector2d(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {		
		return "(" + x + ", " + y + ")";
	}
	
	public boolean precedes(Vector2d other) {
		return this.x <= other.x && this.y <= other.y;
	}
	
	public boolean follows(Vector2d other) {
		return this.x >= other.x && this.y >= other.y;
	}
	
	public Vector2d upperRight(Vector2d other) {
		return new Vector2d(Math.max(x, other.x), Math.max(y,  other.y));
	}
	
	public Vector2d lowerLeft(Vector2d other) {
		return new Vector2d(Math.min(x, other.x), Math.min(y,  other.y));
	}
	
	public Vector2d add(Vector2d other) {
		return new Vector2d(x + other.x, y + other.y);
	}
	
	public Vector2d subtract(Vector2d other) {
		return new Vector2d(x - other.x, y - other.y);
	}
	
	public boolean equals(Object other){
		  if (this == other)
		    return true;
		  if (!(other instanceof Vector2d))
		    return false;
		  Vector2d that = (Vector2d) other;
		  
		  return (that.x == x && that.y == y);
		}
	
	public Vector2d opposite() {
		return new Vector2d(-x, -y);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	@Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }	
}
