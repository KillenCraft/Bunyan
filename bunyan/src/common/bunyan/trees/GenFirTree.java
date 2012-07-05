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
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import extrabiomes.api.TerrainGenManager;

public class GenFirTree extends TreeGenerator {

	public GenFirTree(boolean doNotify) {
		super(doNotify);
		blockLeaf = TerrainGenManager.blockFirLeaves.blockID;
		metaLeaf = TerrainGenManager.metaFirLeaves;
		blockWood = BunyanBlock.wood.blockID;
		metaWood = CustomLog.metaFir;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		if (!isGoodSoil(world, x, y, z)) return false;

		final int height = getRandomHeight(rand);
		final int leaflessHeight = getRandomHeightLeavesStart(rand);
		final int leafWidth = getRandomMaxLeafRadius(rand);

		if (!isRoomToGrow(world, x, y, z, height, leaflessHeight,
				leafWidth)) return false;

		growTree(world, rand, x, y, z, height, leaflessHeight,
				leafWidth);

		return true;
	}

	@Override
	protected int getRandomHeight(Random random) {
		return random.nextInt(8) + 24;
	}

	@Override
	protected int getRandomHeightLeavesStart(Random random) {
		return 1 + random.nextInt(12);
	}

	@Override
	protected int getRandomLeafBlocksAboveTrunk(Random random) {
		return random.nextInt(3);
	}

	@Override
	protected int getRandomMaxLeafRadius(Random random) {
		return 2 + random.nextInt(6);
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
	protected void growRoots(World world, Random random, int x, int y,
			int z)
	{
		world.setBlock(x, y - 1, z, Block.dirt.blockID);
	}

	@Override
	protected void growTree(World world, Random random, int x, int y,
			int z, final int height, final int leaflessHeight,
			final int leafWidth)
	{
		growRoots(world, random, x, y, z);

		growLeaves(world, random, x, y, z, height, leaflessHeight,
				leafWidth);

		growTrunk(world, random, x, y, z, height);
	}

	@Override
	protected void growTrunk(World world, Random rand, int x, int y,
			int z, final int height)
	{
		final int leafBlocksAbove = getRandomLeafBlocksAboveTrunk(rand);

		for (int layer = 0; layer < height - leafBlocksAbove; layer++)
			setWoodBlock(world, x, y + layer, z);
	}

	@Override
	protected boolean isRoomToGrow(World world, int x, int y, int z,
			final int height, final int leaflessHeight,
			final int leafWidth)
	{
		if (y < 1 || y + height + 1 > 256) return false;

		for (int yToCheck = y; yToCheck <= y + 1 + height; yToCheck++) {

			final int radiusToCheck = yToCheck - y < leaflessHeight ? 0
					: leafWidth;

			for (int xToCheck = x - radiusToCheck; xToCheck <= x
					+ radiusToCheck; xToCheck++)
				for (int zToCheck = z - radiusToCheck; zToCheck <= z
						+ radiusToCheck; zToCheck++)
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
		}
		return true;
	}

}