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

public abstract class TreeGenStraightNoBranchesWide extends
		TreeGenStraightNoBranches
{
	protected static boolean	doBlockNotify;

	public TreeGenStraightNoBranchesWide(boolean doNotify) {
		super(doNotify);
		doBlockNotify = doNotify;
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
	protected boolean isGoodSoil(World world, int x, int y, int z) {
		for (int zOffset = 0; zOffset > -2; zOffset--)
			for (int xOffset = 0; xOffset > -2; xOffset--)
				if (!super.isGoodSoil(world, x + xOffset, y, z
						+ zOffset)) return false;
		return true;
	}

	@Override
	protected void setLeafBlock(World world, int x, int y, int z) {
		for (int zOffset = 0; zOffset > -2; zOffset--)
			for (int xOffset = 0; xOffset > -2; xOffset--)
				super.setLeafBlock(world, x + xOffset, y, z + zOffset);
	}

	@Override
	protected void setWoodBlock(World world, int x, int y, int z) {
		final int id = world.getBlockId(x, y, z);

		if (Block.blocksList[id] == null || id == Block.snow.blockID
				|| Block.blocksList[id].isLeaves(world, x, y, z))
		{
			final Direction directions[] = { Direction.SOUTH,
					Direction.WEST, Direction.NORTH, Direction.EAST };

			int dir = 0;
			for (int zOffset = 0; zOffset > -2; zOffset--)
				for (int xOffset = 0; xOffset > -2; xOffset--)
					setBlockAndMetadata(world, x + xOffset, y, z
							+ zOffset, blockWood,
							DirectionalBlock.getCompositeDataAndFacing(
									metaWood, directions[dir++]));
		}
	}

}
