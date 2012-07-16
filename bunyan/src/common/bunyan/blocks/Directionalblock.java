/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.BlockLog;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import bunyan.Direction;

public abstract class DirectionalBlock extends BlockLog {

	public static Direction directionFromMetadata(final int metadata) {
		return Direction.fromValue(((metadata & 3 << 2) >> 2) + 2);
	}

	public static Direction getDirection(World world, int x, int y,
			int z)
	{
		return directionFromMetadata(world.getBlockMetadata(x, y, z));
	}

	public static int metadataWithDirection(int metadata,
			Direction direction)
	{
		final byte valueDirection = (byte) (direction.getValue() - 2);
		return metadata | valueDirection << 2;
	}

	public static void setDirection(World world, int x, int y, int z,
			Direction direction)
	{
		final int type = typeFromMetadata(world.getBlockMetadata(x, y, z));
		world.setBlockMetadataWithNotify(x, y, z,
				metadataWithDirection(type, direction));
	}

	public static int typeFromMetadata(int metadata) {
		return metadata & 3;
	}

	public DirectionalBlock(int par1) {
		super(par1);
	}

	@Override
	protected int damageDropped(int metadata) {
		return typeFromMetadata(metadata);
	}

	@Override
	protected void dropBlockAsItem_do(World par1World, int par2,
			int par3, int par4, ItemStack par5ItemStack)
	{
		par5ItemStack.setItemDamage(typeFromMetadata(par5ItemStack
				.getItemDamage()));
		super.dropBlockAsItem_do(par1World, par2, par3, par4,
				par5ItemStack);
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
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		final Direction direction = directionFromMetadata(metadata);
		final Direction face = Direction.fromValue(side);
		final int textureSet = typeFromMetadata(metadata);
		int row = 0;
		int column = 0;

		switch (face) {
			case DOWN:
			case UP:
				switch (direction) {
					case NORTH:
						row = 1;
						column = 1;
						break;
					case SOUTH:
						row = 2;
						column = 1;
						break;
					case WEST:
						row = 2;
						column = 0;
						break;
					case EAST:
						row = 1;
						column = 0;
						break;
					default:
						break;
				}
				break;

			case NORTH:
				switch (direction) {
					case NORTH:
						row = 0;
						column = 0;
						break;
					case SOUTH:
						row = 3;
						column = 0;
						break;
					case WEST:
						row = 3;
						column = 1;
						break;
					case EAST:
						row = 0;
						column = 1;
						break;
					default:
						break;
				}
				break;

			case SOUTH:
				switch (direction) {
					case NORTH:
						row = 3;
						column = 1;
						break;
					case SOUTH:
						row = 0;
						column = 1;
						break;
					case WEST:
						row = 0;
						column = 0;
						break;
					case EAST:
						row = 3;
						column = 0;
						break;
					default:
						break;
				}
				break;

			case WEST:
				switch (direction) {
					case NORTH:
						row = 3;
						column = 0;
						break;
					case SOUTH:
						row = 3;
						column = 1;
						break;
					case WEST:
						row = 0;
						column = 1;
						break;
					case EAST:
						row = 0;
						column = 0;
						break;
					default:
						break;
				}
				break;

			case EAST:
				switch (direction) {
					case NORTH:
						row = 0;
						column = 1;
						break;
					case SOUTH:
						row = 0;
						column = 0;
						break;
					case WEST:
						row = 3;
						column = 0;
						break;
					case EAST:
						row = 3;
						column = 1;
						break;
					default:
						break;
				}
		}

		return blockIndexInTexture + row * 16 + column + textureSet * 2;
	}

	@Override
	public String getTextureFile() {
		return "/bunyan/blocks/blocks.png";
	}

	@Override
	public int idDropped(int metadata, Random random, int alwaysZero) {
		return blockID;
	}

}