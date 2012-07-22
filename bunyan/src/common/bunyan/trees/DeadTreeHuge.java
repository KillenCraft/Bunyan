/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.trees;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import bunyan.api.Direction;
import bunyan.api.DirectionalBlock;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.WideLog;
import extrabiomes.api.TerrainGenManager;

public class DeadTreeHuge extends TreeGenStraightNoBranchesWide {

	private static int max(int[] height) {
		int max = 0;
		for (final int i : height)
			if (i > max) max = i;
		return max;
	}

	public DeadTreeHuge(int metaWood) {
		super(false);
		blockLeaf = 0;
		metaLeaf = 0;
		blockWood = BunyanBlock.widewood.blockID;
		this.metaWood = metaWood;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		if (!isGoodSoil(world, x, y, z)) return false;

		final int minHeight = rand.nextInt(4) + 1;

		final int height[] = { rand.nextInt(8) + minHeight,
				rand.nextInt(8) + minHeight,
				rand.nextInt(8) + minHeight,
				rand.nextInt(8) + minHeight };

		final int maxHeight = max(height);
		if (!isRoomToGrow(world, maxHeight, x, y, z)) return false;

		for (int yOffset = 0; yOffset < maxHeight; yOffset++) {
			final Direction directions[] = { Direction.SOUTH,
					Direction.WEST, Direction.NORTH, Direction.EAST };
			int dir = 0;
			for (int zOffset = 0; zOffset > -2; zOffset--)
				for (int xOffset = 0; xOffset > -2; xOffset--) {
					final int index = 0 - zOffset * 2 - xOffset;
					if (yOffset < height[index]) {
						setBlockAndMetadata(world, x + xOffset, y
								+ yOffset, z + zOffset, blockWood,
								DirectionalBlock.getCompositeDataAndFacing(metaWood, directions[dir]));
					}
					dir++;

				}
		}

		return true;
	}

	@Override
	protected int getRandomHeight(Random random) {
		return 0;
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
		return 0;
	}

	@Override
	protected boolean isGoodSoil(World world, int x, int y, int z) {
		for (int zOffset = 0; zOffset > -2; zOffset--)
			for (int xOffset = 0; xOffset > -2; xOffset--) {
				final int id = world.getBlockId(x + xOffset, y - 1, z
						+ zOffset);
				if (id != TerrainGenManager.blockWasteland.blockID)
					return false;
			}
		return true;
	}

	protected boolean isRoomToGrow(World world, int height, int x,
			int y, int z)
	{
		if (y < 1 || y + height + 1 > 256) return false;

		for (int yToCheck = y; yToCheck <= y + 1 + height; yToCheck++)
			for (int xToCheck = x - 2; xToCheck <= x + 1; xToCheck++)
				for (int zToCheck = z - 2; zToCheck <= z + 1; zToCheck++)
				{
					final int idToCheck = world.getBlockId(xToCheck,
							yToCheck, zToCheck);

					if (Block.blocksList[idToCheck] != null
							&& idToCheck != Block.snow.blockID
							&& !Block.blocksList[idToCheck]
									.isLeaves(world, xToCheck,
											yToCheck, zToCheck))
						return false;
				}
		return true;
	}
}
