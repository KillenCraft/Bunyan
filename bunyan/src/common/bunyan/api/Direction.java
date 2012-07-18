
package bunyan.api;

/**
 * This enum represents the standard Minecraft defined direction values
 * often used for metadata and various other functions.
 * 
 * @author CovertJaguar <railcraft.wikispaces.com>
 */
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

	public Direction oppositeSide() {
		return Direction.fromValue(getValue() % 2 == 0 ? getValue() + 1
				: getValue() - 1);
	}

}
