/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.trees;

import java.util.Random;

import net.minecraft.src.World;

public abstract class TreeGenStraightNoBranches extends TreeGenerator {

	public TreeGenStraightNoBranches(boolean doNotify) {
		super(doNotify);
	}

	@Override
	protected void growLeaves(World world, Random random, int x, int y,
			int z, final int height, final int leaflessHeight,
			final int leafWidth)
	{
		final int leafedHeight = height - leaflessHeight;
		int leafRadiusAtCurrentLayer = random.nextInt(2);
		int maxLeafRadius = 1;
		boolean firstTime = false;

		for (int currentLayer = 0; currentLayer <= leafedHeight; currentLayer++)
		{
			final int yToGrow = y + height - currentLayer;

			for (int xToGrow = x - leafRadiusAtCurrentLayer; xToGrow <= x
					+ leafRadiusAtCurrentLayer; xToGrow++)
			{
				final int xRadius = xToGrow - x;

				for (int zToGrow = z - leafRadiusAtCurrentLayer; zToGrow <= z
						+ leafRadiusAtCurrentLayer; zToGrow++)
				{
					final int zRadius = zToGrow - z;

					if (Math.abs(xRadius) != leafRadiusAtCurrentLayer
							|| Math.abs(zRadius) != leafRadiusAtCurrentLayer
							|| leafRadiusAtCurrentLayer <= 0)
						setLeafBlock(world, xToGrow, yToGrow, zToGrow);
				}
			}

			if (leafRadiusAtCurrentLayer >= maxLeafRadius) {
				leafRadiusAtCurrentLayer = firstTime ? 1 : 0;
				firstTime = true;

				if (++maxLeafRadius > leafWidth)
					maxLeafRadius = leafWidth;
			} else
				leafRadiusAtCurrentLayer++;
		}
	}

	@Override
	protected void growTrunk(World world, Random rand, int x, int y,
			int z, final int height)
	{
		final int leafBlocksAbove = getRandomLeafBlocksAboveTrunk(rand);

		for (int layer = 0; layer < height - leafBlocksAbove; layer++)
			setWoodBlock(world, x, y + layer, z);
	}

}
