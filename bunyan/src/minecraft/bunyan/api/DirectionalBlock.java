/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.api;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

/**
 * DirectionalBlock is a base class that allows a block to face the four
 * directions of the compass. This class handles encapsulates all of the
 * functionality of storing the directional data within the block's
 * metadata, while also allowing up to four directional blocks to share
 * a blockId.
 */
public abstract class DirectionalBlock extends Block {

	public static int getCompositeDataAndFacing(int data,
			Direction facing)
	{
		return (facing.getValue() - 2 << 2) + data;
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

	public static void setData(World world, int x, int y, int z,
			int data, boolean doNotify)
	{
		final Direction facing = getFacingFromMetadata(world
				.getBlockMetadata(x, y, z));
		setDataAndFacing(world, x, y, z, data, facing, doNotify);
	}

	public static void setDataAndFacing(World world, int x, int y,
			int z, int data, Direction facing, boolean doNotify)
	{
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

	public static void setFacing(World world, int x, int y, int z,
			Direction facing, boolean doNotify)
	{
		final int data = getDataFromMetadata(world.getBlockMetadata(x,
				y, z));
		setDataAndFacing(world, x, y, z, data, facing, doNotify);
	}

	protected DirectionalBlock(int id, int index, Material material) {
		super(id, index, material);
	}

	@Override
	protected int damageDropped(int metadata) {
		return getDataFromMetadata(metadata);
	}

	@Override
	protected void dropBlockAsItem_do(World world, int x, int y, int z,
			ItemStack itemstack)
	{
		itemstack.setItemDamage(getDataFromMetadata(itemstack
				.getItemDamage()));
		super.dropBlockAsItem_do(world, x, y, z, itemstack);
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x,
			int y, int z, int metadata, int fortune)
	{
		return super.getBlockDropped(world, x, y, z,
				metadata == 0 ? world.getBlockMetadata(x, y, z)
						: metadata, fortune);
	}

	@Override
	public int getBlockTexture(IBlockAccess iBlockAccess, int x, int y,
			int z, int side)
	{
		final int metadata = iBlockAccess.getBlockMetadata(x, y, z);
		final Direction facing = getFacingFromMetadata(metadata);
		return blockIndexInTexture
				+ getTextureOffsetFromFacingSideAndMetadata(facing,
						Direction.fromValue(side), metadata);
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

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLiving entity)
	{
		Direction facingBlock = Direction.NORTH;
		if (entity != null) {
			final int facingEntity = MathHelper
					.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;

			switch (facingEntity) {
				case 0:
					facingBlock = Direction.NORTH;
					break;
				case 1:
					facingBlock = Direction.EAST;
					break;
				case 2:
					facingBlock = Direction.SOUTH;
					break;
				case 3:
					facingBlock = Direction.WEST;
			}
			setFacing(world, x, y, z, facingBlock, true);
		}
	}

}
