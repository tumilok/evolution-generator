package com.tumilok.test;

import com.tumilok.main.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2dTest {

	@Test
	void testEquals() {
		Vector2d vector1 = new Vector2d(1, 1);
		Vector2d vector2 = new Vector2d(1, 1);
		Vector2d vector3 = new Vector2d(-1, 1);
		
		Assertions.assertTrue(vector1.equals(vector1));
		Assertions.assertTrue(vector1.equals(vector2));
		Assertions.assertFalse(vector1.equals(vector3));
		Assertions.assertFalse(vector1.equals(this));
	}
	
	@Test
	void testToString() {
		Vector2d vector = new Vector2d(1, 1);

		Assertions.assertEquals("(1, 1)", vector.toString());
		Assertions.assertNotEquals("(1, 2)", vector.toString());
	}
	
	@Test
	void testPrecedes() {
		Vector2d vector1 = new Vector2d(0, 0);
		Vector2d vector2 = new Vector2d(1, 1);
		Vector2d vector3 = new Vector2d(0, 1);
		Vector2d vector4 = new Vector2d(-1, 0);
		Vector2d vector5 = new Vector2d(-1, -1);
		
		Assertions.assertTrue(vector1.precedes(vector2));
		Assertions.assertTrue(vector1.precedes(vector3));
		Assertions.assertFalse(vector1.precedes(vector4));
		Assertions.assertFalse(vector1.precedes(vector5));
	}
	
	@Test
	void testFollows() {
		Vector2d vector1 = new Vector2d(0, 0);
		Vector2d vector2 = new Vector2d(1, 1);
		Vector2d vector3 = new Vector2d(0, 1);
		Vector2d vector4 = new Vector2d(-1, 0);
		Vector2d vector5 = new Vector2d(-1, -1);
		
		Assertions.assertTrue(vector1.follows(vector4));
		Assertions.assertTrue(vector1.follows(vector5));
		Assertions.assertFalse(vector1.follows(vector2));
		Assertions.assertFalse(vector1.follows(vector3));
	}
	
	@Test
	void testUpperRight() {
		Vector2d vector1 = new Vector2d(5, 5);
		Vector2d vector2 = new Vector2d(1, 2);
		Vector2d vector3 = new Vector2d(0, 0);
		Vector2d vector4 = new Vector2d(-1, 2);
		
		Assertions.assertEquals(vector1, vector1.upperRight(vector2));
		Assertions.assertEquals(vector1, vector2.upperRight(vector1));
		Assertions.assertEquals(new Vector2d(0, 2), vector3.upperRight(vector4));
		Assertions.assertEquals(new Vector2d(0, 2), vector4.upperRight(vector3));
	}
	
	@Test
	void testLowerLeft() {
		Vector2d vector1 = new Vector2d(5, 5);
		Vector2d vector2 = new Vector2d(1, 2);
		Vector2d vector3 = new Vector2d(0, 0);
		Vector2d vector4 = new Vector2d(-1, 2);
		
		Assertions.assertEquals(vector2, vector1.lowerLeft(vector2));
		Assertions.assertEquals(vector2, vector2.lowerLeft(vector1));
		Assertions.assertEquals(new Vector2d(-1, 0), vector3.lowerLeft(vector4));
		Assertions.assertEquals(new Vector2d(-1, 0), vector4.lowerLeft(vector3));
	}
	
	@Test
	void testAdd() {
		Vector2d vector1 = new Vector2d(1, 3);
		Vector2d vector2 = new Vector2d(6, -4);
		Vector2d result = new Vector2d(7, -1);
		
		Assertions.assertEquals(result, vector1.add(vector2));
		Assertions.assertEquals(result, vector2.add(vector1));
	}
	
	@Test
	void testSubstract() {
		Vector2d vector1 = new Vector2d(1, 3);
		Vector2d vector2 = new Vector2d(6, -4);
		Vector2d result1 = new Vector2d(-5, 7);
		Vector2d result2 = new Vector2d(5, -7);
		
		Assertions.assertEquals(result1, vector1.subtract(vector2));
		Assertions.assertEquals(result2, vector2.subtract(vector1));
	}
	
	@Test
	void testOpposite() {
		Vector2d vector1 = new Vector2d(5, 3);
		Vector2d vector2 = new Vector2d(-5, -3);
		
		Assertions.assertEquals(vector1, vector2.opposite());
		Assertions.assertEquals(vector2, vector1.opposite());
	}

}
