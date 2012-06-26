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
import bunyan.blocks.WideLog;

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
		super.growRoots(world, random, x, y, z);
		super.growRoots(world, random, x - 1, y, z);
		super.growRoots(world, random, x, y, z - 1);
		super.growRoots(world, random, x - 1, y, z - 1);
	}

	@Override
	protected boolean isGoodSoil(World world, int x, int y, int z) {
		return super.isGoodSoil(world, x, y, z)
				&& super.isGoodSoil(world, x, y, z - 1)
				&& super.isGoodSoil(world, x - 1, y, z)
				&& super.isGoodSoil(world, x - 1, y, z - 1);
	}

	@Override
	protected void setLeafBlock(World world, int x, int y, int z) {
		super.setLeafBlock(world, x, y, z);
		super.setLeafBlock(world, x - 1, y, z);
		super.setLeafBlock(world, x, y, z - 1);
		super.setLeafBlock(world, x - 1, y, z - 1);
	}

	protected void setMetadata(World world, int x, int y, int z,
			int metadata)
	{
		if (doBlockNotify)
			world.setBlockMetadataWithNotify(x, y, z, metadata);
		else if (world.blockExists(x, y, z)
				&& world.getChunkFromBlockCoords(x, z).field_50120_o)
		{
			if (world.setBlockMetadata(x, y, z, metadata))
				world.markBlockNeedsUpdate(x, y, z);
		} else
			world.setBlockMetadata(x, y, z, metadata);
	}

	@Override
	protected void setWoodBlock(World world, int x, int y, int z) {
		final int id = world.getBlockId(x, y, z);

		if (Block.blocksList[id] == null || id == Block.snow.blockID
				|| Block.blocksList[id].isLeaves(world, x, y, z))
		{
			setBlockAndMetadata(world, x, y, z, blockWood, metaWood);
			setMetadata(world, x, y, z,
					WideLog.metadataWithDirection(metaWood, 3));

			setBlockAndMetadata(world, x - 1, y, z, blockWood, metaWood);
			setMetadata(world, x - 1, y, z,
					WideLog.metadataWithDirection(metaWood, 4));

			setBlockAndMetadata(world, x, y, z - 1, blockWood, metaWood);
			setMetadata(world, x, y, z - 1,
					WideLog.metadataWithDirection(metaWood, 2));

			setBlockAndMetadata(world, x - 1, y, z - 1, blockWood,
					metaWood);
			setMetadata(world, x - 1, y, z - 1,
					WideLog.metadataWithDirection(metaWood, 5));
		}
	}

}
