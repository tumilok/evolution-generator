package com.tumilok.main;

public enum MoveDirection {
	ZERO,
	ONE,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN;

	public int toInt(){
		switch (this){
			case ZERO:
				return 0;
			case ONE:
				return 1;
			case TWO:
				return 2;
			case THREE:
				return 3;
			case FOUR:
				return 4;
			case FIVE:
				return 5;
			case SIX:
				return 6;
			case SEVEN:
				return 7;
			default:
				return -1;
		}
	}
}
