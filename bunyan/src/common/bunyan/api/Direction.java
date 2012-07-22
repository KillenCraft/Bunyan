/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.api;

public enum Direction {

	DOWN(0), UP(1), NORTH(2), SOUTH(3), WEST(4), EAST(5);

	public static Direction fromValue(int i) {

		switch (i) {
			case 0:
				return DOWN;
			case 1:
				return UP;
			case 2:
				return NORTH;
			case 3:
				return SOUTH;
			case 4:
				return WEST;
			case 5:
				return EAST;
			default:
				return null;
		}
	}

	private final byte	value;

	private Direction(int value) {
		this.value = (byte) value;
	}

	public byte getValue() {
		return value;
	}

	public Direction leftSide() {
		switch (this) {
			case NORTH:
				return WEST;
			case SOUTH:
				return EAST;
			case WEST:
				return SOUTH;
			case EAST:
				return NORTH;
			default:
				return this;
		}
	}

	public Direction oppositeSide() {
		return Direction.fromValue(getValue() % 2 == 0 ? getValue() + 1
				: getValue() - 1);
	}

	public Direction rightSide() {
		if (this == UP || this == DOWN) return this;
		return leftSide().oppositeSide();
	}

}
