
package bunyan;

/**
 * This enum represents the standard Minecraft defined direction values
 * often used for metadata and various other functions.
 * 
 * @author CovertJaguar <railcraft.wikispaces.com>
 */
public enum Direction {

	DOWN(0), UP(1), NORTH(2), SOUTH(3), WEST(4), EAST(5);
	public static Direction fromValue(int i) {
		for (final Direction d : values())
			if (d.getValue() == i) return d;
		return UP;
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
