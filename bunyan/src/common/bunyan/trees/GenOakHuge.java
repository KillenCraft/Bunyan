
package bunyan.trees;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import bunyan.Direction;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.WideLog;

public class GenOakHuge extends TreeGenerator {

	enum Acuteness {
		LOOSE, TIGHTER, TIGHT
	}

	enum BendDirection {
		LEFT, STRAIGHT, RIGHT
	}

	final int	blockTrunk;
	final int	metaTrunk;

	public GenOakHuge(boolean doNotify) {
		super(doNotify);
		blockLeaf = Block.leaves.blockID;
		metaLeaf = 0;
		blockWood = Block.wood.blockID;
		metaWood = 0;
		blockTrunk = BunyanBlock.widewood.blockID;
		metaTrunk = WideLog.metaOak;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y,
			int z)
	{

		if (!isGoodSoil(world, x, y, z)) return false;

		final int height = getRandomHeight(random);
		final int size = getRandomMaxLeafRadius(random);

		growTree(world, random, x, y, z, height, 0, size);

		return true;
	}

	@Override
	protected int getRandomHeight(Random random) {
		return random.nextInt(4) + 3;
	}

	@Override
	protected int getRandomHeightLeavesStart(Random random) {
		return 0;
	}

	@Override
	protected int getRandomLeafBlocksAboveTrunk(Random random) {
		return 0;
	}

	@Override
	protected int getRandomMaxLeafRadius(Random random) {
		return 15 + random.nextInt(25);
	}

	private void growLeafNode(World world, int x, int y, int z) {
		for (int xOffset = -3; xOffset <= 3; xOffset++)
			for (int zOffset = -3; zOffset <= 3; zOffset++) {
				if ((Math.abs(xOffset) != 3 || Math.abs(zOffset) != 3)
						&& (Math.abs(xOffset) != 3 || Math.abs(zOffset) != 2)
						&& (Math.abs(xOffset) != 2 || Math.abs(zOffset) != 3)
						&& (xOffset != 0 || zOffset != 0))
					if (world.getBlockId(x + xOffset, y, z + zOffset) == 0)
						setBlockAndMetadata(world, x + xOffset, y, z
								+ zOffset, blockLeaf, metaLeaf);
				if (Math.abs(xOffset) >= 3 || Math.abs(zOffset) >= 3
						|| Math.abs(xOffset) == 2
						&& Math.abs(zOffset) == 2) continue;
				if (world.getBlockId(x + xOffset, y - 1, z + zOffset) == 0)
					setBlockAndMetadata(world, x + xOffset, y - 1, z
							+ zOffset, blockLeaf, metaLeaf);
				if (world.getBlockId(x + xOffset, y + 1, z + zOffset) != 0)
					continue;
				setBlockAndMetadata(world, x + xOffset, y + 1, z
						+ zOffset, blockLeaf, metaLeaf);
			}
	}

	@Override
	protected void growLeaves(World world, Random random, int x, int y,
			int z, int height, int leaflessHeight, int leafWidth)
	{
		for (final BendDirection xDirection : BendDirection.values())
			for (final BendDirection zDirection : BendDirection
					.values())
			{
				if (xDirection == BendDirection.STRAIGHT
						&& zDirection == BendDirection.STRAIGHT)
					continue;
				primary(world, random, leafWidth, xDirection,
						zDirection, x, y + height, z);
				inside(world, random, leafWidth, xDirection,
						zDirection, x, y + height, z);
				insideSmall(world, random, leafWidth, xDirection,
						zDirection, x, y + height, z);
			}
	}

	@Override
	protected void growRoots(World world, Random random, int x, int y,
			int z)
	{
		for (int zOffset = 0; zOffset > -2; zOffset--)
			for (int xOffset = 0; xOffset > -2; xOffset--)
				super.growRoots(world, random, x + xOffset, y, z
						+ zOffset);
	}

	@Override
	protected void growTree(World world, Random random, int x, int y,
			int z, int height, int leaflessHeight, int leafWidth)
	{
		growRoots(world, random, x, y, z);

		growTrunk(world, random, x, y, z, height);

		growLeaves(world, random, x, y, z, height, leaflessHeight,
				leafWidth);

	}

	@Override
	protected void growTrunk(World world, Random random, int x, int y,
			int z, int height)
	{

		final Direction directions[] = { Direction.SOUTH,
				Direction.WEST, Direction.NORTH, Direction.EAST };

		for (int yOffset = 0; yOffset < height + 1; yOffset++) {
			int dir = 0;
			for (int zOffset = 0; zOffset > -2; zOffset--)
				for (int xOffset = 0; xOffset > -2; xOffset--)
					setBlockAndMetadata(world, x + xOffset,
							y + yOffset, z + zOffset, blockTrunk,
							WideLog.metadataWithDirection(metaTrunk,
									directions[dir++]));
		}

	}

	private void inside(World world, Random random, int size,
			BendDirection xDirection, BendDirection zDirection, int x,
			int y, int z)
	{
		int length = 0;
		while (length < 2 * size / 3) {
			setBlockAndMetadata(world, x, y, z, blockWood, metaWood);
			if (random.nextInt(3) == 0 || length == 2 * size / 3 - 1)
				growLeafNode(world, x, y, z);
			switch (xDirection) {
				case STRAIGHT:
					x += random.nextInt(3) - 1;
					break;
				case RIGHT:
					x += random.nextInt(2);
					break;
				case LEFT:
					x -= random.nextInt(2);
			}
			switch (zDirection) {
				case STRAIGHT:
					z += random.nextInt(3) - 1;
					break;
				case RIGHT:
					z += random.nextInt(2);
					break;
				case LEFT:
					z -= random.nextInt(2);
			}
			if (random.nextInt(6) == 0 && length > size / 3)
				secondary(world, random, size / 3 - length / 3,
						xDirection, zDirection, x, y, z);
			y++;
			length++;
		}
	}

	private void insideSmall(World world, Random random, int size,
			BendDirection xDirection, BendDirection zDirection, int x,
			int y, int z)
	{
		int length = 0;
		while (length < size / 3) {
			setBlockAndMetadata(world, x, y, z, blockWood, metaWood);
			if (random.nextInt(3) == 0 || length == size / 3 - 1)
				growLeafNode(world, x, y, z);
			switch (xDirection) {
				case STRAIGHT:
					x += random.nextInt(3) - 1;
					break;
				case RIGHT:
					x += random.nextInt(2);
					break;
				case LEFT:
					x -= random.nextInt(2);
			}
			switch (zDirection) {
				case STRAIGHT:
					z += random.nextInt(3) - 1;
					break;
				case RIGHT:
					z += random.nextInt(2);
					break;
				case LEFT:
					z -= random.nextInt(2);
			}
			if (random.nextInt(6) == 0 && length > size / 6)
				secondary(world, random, size / 6 - length / 6,
						xDirection, zDirection, x, y, z);
			y++;
			length++;
		}
	}

	@Override
	protected boolean isGoodSoil(World world, int x, int y, int z) {
		if (!super.isGoodSoil(world, x, y, z)) return false;

		final int id = world.getBlockId(x, y - 2, z);
		if (id == Block.waterStill.blockID
				|| id == Block.waterMoving.blockID
				|| id == Block.stone.blockID) return false;
		return true;
	}

	private void primary(World world, Random random, int size,
			BendDirection xDirection, BendDirection zDirection, int x,
			int y, int z)
	{
		Acuteness acuteness = Acuteness.LOOSE;
		int length = 0;
		if (xDirection == BendDirection.RIGHT) x += 2;
		if (xDirection == BendDirection.LEFT) x -= 2;
		if (zDirection == BendDirection.RIGHT) z += 2;
		if (zDirection == BendDirection.LEFT) z -= 2;
		while (length < size) {
			switch (acuteness) {
				case LOOSE:
					if (random.nextInt(4) == 0) y++;
					break;
				case TIGHTER:
					if (random.nextInt(2) == 0) y++;
					break;
				case TIGHT:
					y++;
			}
			setBlockAndMetadata(world, x, y, z, blockWood, metaWood);
			if (random.nextInt(3) == 0 || length == size - 1)
				growLeafNode(world, x, y, z);
			switch (xDirection) {
				case STRAIGHT:
					x += random.nextInt(3) - 1;
					break;
				case RIGHT:
					x += random.nextInt(2);
					break;
				case LEFT:
					x -= random.nextInt(2);
			}
			switch (zDirection) {
				case STRAIGHT:
					z += random.nextInt(3) - 1;
					break;
				case RIGHT:
					z += random.nextInt(2);
					break;
				case LEFT:
					z -= random.nextInt(2);
			}
			if (length == size / 3) acuteness = Acuteness.TIGHTER;
			if (length == 2 * size / 3) acuteness = Acuteness.TIGHT;
			if (random.nextInt(4) == 0)
				secondary(world, random, size / 2 - length / 2,
						xDirection, zDirection, x, y, z);
			length++;
		}
	}

	private void secondary(World world, Random random, int size,
			BendDirection xDirection, BendDirection zDirection, int x,
			int y, int z)
	{
		int length = 0;
		for (int branch = 0; branch < 2; branch++) {
			int x1 = x;
			int y1 = y;
			int z1 = z;
			while (length < size) {
				if (random.nextInt(2) == 0) y1++;
				setBlockAndMetadata(world, x1, y1, z1, blockWood,
						metaWood);
				if (random.nextInt(4) == 0 || length == size - 1)
					growLeafNode(world, x1, y1, z1);
				if (zDirection == BendDirection.STRAIGHT) {
					if (xDirection == BendDirection.RIGHT)
						x1 += random.nextInt(2);
					else
						x1 -= random.nextInt(2);
					if (branch == 0)
						z1 += random.nextInt(2);
					else
						z1 -= random.nextInt(2);
				}
				if (xDirection == BendDirection.STRAIGHT) {
					if (zDirection == BendDirection.RIGHT)
						z1 += random.nextInt(2);
					else
						z1 -= random.nextInt(2);
					if (branch == 0)
						x1 += random.nextInt(2);
					else
						x1 -= random.nextInt(2);
				}
				if (xDirection == BendDirection.RIGHT) {
					if (zDirection == BendDirection.RIGHT)
						if (branch == 0)
							z1 += random.nextInt(2);
						else
							x1 += random.nextInt(2);
					if (zDirection == BendDirection.LEFT)
						if (branch == 0)
							z1 -= random.nextInt(2);
						else
							x1 += random.nextInt(2);
				}
				if (xDirection == BendDirection.LEFT) {
					if (zDirection == BendDirection.RIGHT)
						if (branch == 0)
							z1 += random.nextInt(2);
						else
							x1 -= random.nextInt(2);
					if (zDirection == BendDirection.LEFT)
						if (branch == 0)
							z1 -= random.nextInt(2);
						else
							x1 -= random.nextInt(2);
				}
				length++;
			}
		}
	}
}