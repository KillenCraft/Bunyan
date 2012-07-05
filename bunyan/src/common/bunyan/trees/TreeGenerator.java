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
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.TerrainGenManager;

public abstract class TreeGenerator extends WorldGenerator {

	protected int	blockLeaf;
	protected int	blockWood;
	protected int	metaLeaf;
	protected int	metaWood;

	public TreeGenerator(boolean doNotify) {
		super(doNotify);
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

	protected abstract int getRandomHeight(Random random);

	protected abstract int getRandomHeightLeavesStart(Random random);

	protected abstract int getRandomLeafBlocksAboveTrunk(Random random);

	protected abstract int getRandomMaxLeafRadius(Random random);

	protected abstract void growLeaves(World world, Random random,
			int x, int y, int z, int height, int leaflessHeight,
			int leafWidth);

	protected void growRoots(World world, Random random, int x, int y,
			int z)
	{
		world.setBlock(x, y - 1, z, Block.dirt.blockID);
	}

	protected void growTree(World world, Random random, int x, int y,
			int z, final int height, final int leaflessHeight,
			final int leafWidth)
	{
		growRoots(world, random, x, y, z);

		growLeaves(world, random, x, y, z, height, leaflessHeight,
				leafWidth);

		growTrunk(world, random, x, y, z, height);
	}

	protected abstract void growTrunk(World world, Random random,
			int x, int y, int z, int height);

	protected boolean isGoodSoil(World world, int x, int y, int z) {
		return TerrainGenManager.treesCanGrowOnIDs.contains(Integer
				.valueOf(world.getBlockId(x, y - 1, z)));
	}

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
					if (!world.getChunkProvider().chunkExists(
							xToCheck >> 4, zToCheck >> 4))
						return false;

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

	protected void setLeafBlock(World world, final int x, int y, int z)
	{
		if (!world.getChunkProvider().chunkExists(x >> 4, z >> 4))
			return;

		final Block block = Block.blocksList[world.getBlockId(x, y, z)];

		if (block == null
				|| block.canBeReplacedByLeaves(world, x, y, z))
			setBlockAndMetadata(world, x, y, z, blockLeaf, metaLeaf);
	}

	protected void setWoodBlock(World world, int x, int y, int z) {
		final int id = world.getBlockId(x, y, z);

		if (Block.blocksList[id] == null || id == Block.snow.blockID
				|| Block.blocksList[id].isLeaves(world, x, y, z))
			setBlockAndMetadata(world, x, y, z, blockWood, metaWood);
	}

}
