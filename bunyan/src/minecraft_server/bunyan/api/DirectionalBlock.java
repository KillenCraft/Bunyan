/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.api;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public abstract class DirectionalBlock extends Block {

	public static int getCompositeDataAndFacing(int data,
			Direction facing)
	{
		return (facing.getValue() << 2) + data;
	}

	public static int getDataFromMetadata(int metadata) {
		return metadata & 3;
	}

	public static Direction getFacing(IBlockAccess iBlockAccess, int x,
			int y, int z)
	{
		return getFacingFromMetadata(iBlockAccess.getBlockMetadata(x,
				y, z));
	}

	public static Direction getFacingFromMetadata(int metadata) {
		return Direction.fromValue(((metadata & 12) >> 2) + 2);
	}

	public static void setFacing(World world, int x, int y, int z,
			Direction facing, boolean doNotify)
	{
		final int data = getDataFromMetadata(world.getBlockMetadata(x,
				y, z));
		world.setBlockMetadata(x, y, z,
				getCompositeDataAndFacing(data, facing));
		if (doNotify) {
			final int id = world.getBlockId(x, y, z);

			if (Block.requiresSelfNotify[id & 4095])
				world.notifyBlockChange(x, y, z, id);
			else
				world.notifyBlocksOfNeighborChange(x, y, z, id);
		}

	}

	protected DirectionalBlock(int id, int index, Material material) {
		super(id, index, material);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return blockIndexInTexture
				+ getTextureOffsetFromFacingSideAndMetadata(
						Direction.NORTH, Direction.fromValue(side),
						metadata);
	}

	public abstract int getTextureOffsetFromFacingSideAndMetadata(
			Direction facing, Direction side, int metadata);

}
