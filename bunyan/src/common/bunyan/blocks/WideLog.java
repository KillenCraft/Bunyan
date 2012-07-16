/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.blocks;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import bunyan.Direction;

public class WideLog extends DirectionalBlock {

	public static final int	metaRedwood	= 0;
	public static final int	metaFir		= 1;
	public static final int	metaOak		= 2;

	public WideLog(int id) {
		super(id);
		blockIndexInTexture = 48;
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(blockID, 1, metaRedwood));
		itemList.add(new ItemStack(blockID, 1, metaFir));
		itemList.add(new ItemStack(blockID, 1, metaOak));
	}

	@Override
	public float getExplosionResistance(Entity entity) {
		return Block.wood.getExplosionResistance(entity);
	}

	@Override
	public int getFireSpreadSpeed(World world, int x, int y, int z,
			int metadata, int face)
	{
		return Block.wood.getFireSpreadSpeed(world, x, y, z, metadata,
				face);
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z,
			int metadata, int face)
	{
		return Block.wood.getFlammability(world, x, y, z, metadata,
				face);
	}

	@Override
	public float getHardness() {
		return Block.wood.getHardness();
	}

	@Override
	public float getHardness(int meta) {
		return Block.wood.getHardness(meta);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		setDefaultDirection(world, x, y, z);
	}

	private void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			final int eastBlock = world.getBlockId(x, y, z - 1);
			final int westBlock = world.getBlockId(x, y, z + 1);
			final int northBlock = world.getBlockId(x - 1, y, z);
			final int southBlock = world.getBlockId(x + 1, y, z);
			Direction direction = Direction.SOUTH;

			if (eastBlock != blockID && westBlock != blockID
					&& northBlock != blockID && southBlock != blockID)
			{

				if (Block.opaqueCubeLookup[eastBlock]
						&& !Block.opaqueCubeLookup[westBlock])
					direction = Direction.SOUTH;

				if (Block.opaqueCubeLookup[westBlock]
						&& !Block.opaqueCubeLookup[eastBlock])
					direction = Direction.NORTH;

				if (Block.opaqueCubeLookup[northBlock]
						&& !Block.opaqueCubeLookup[southBlock])
					direction = Direction.EAST;

				if (Block.opaqueCubeLookup[southBlock]
						&& !Block.opaqueCubeLookup[northBlock])
					direction = Direction.WEST;
				setDirection(world, x, y, z, direction);
			} else
				setSmartDirection(world, x, y, z);
		}
	}

	private void setSmartDirection(World world, int x, int y, int z) {
		int block = world.getBlockId(x, y, z + 1);
		Direction direction = Direction.SOUTH;
		if (block == blockID) {
			final Direction blockDirection = directionFromMetadata(world
					.getBlockMetadata(x, y, z + 1));
			direction = blockDirection == Direction.SOUTH ? Direction.NORTH
					: blockDirection == Direction.NORTH ? Direction.SOUTH
							: blockDirection == Direction.WEST ? Direction.EAST
									: Direction.WEST;
		}

		block = world.getBlockId(x, y, z - 1);
		if (block == blockID) {
			final Direction blockDirection = directionFromMetadata(world
					.getBlockMetadata(x, y, z - 1));
			direction = blockDirection == Direction.SOUTH ? Direction.NORTH
					: blockDirection == Direction.NORTH ? Direction.SOUTH
							: blockDirection == Direction.WEST ? Direction.EAST
									: Direction.WEST;
		}

		block = world.getBlockId(x - 1, y, z);
		if (block == blockID) {
			final Direction blockDirection = directionFromMetadata(world
					.getBlockMetadata(x - 1, y, z));
			direction = blockDirection == Direction.SOUTH ? Direction.WEST
					: blockDirection == Direction.WEST ? Direction.SOUTH
							: blockDirection == Direction.NORTH ? Direction.EAST
									: Direction.NORTH;
		}

		block = world.getBlockId(x + 1, y, z);
		if (block == blockID) {
			final Direction blockDirection = directionFromMetadata(world
					.getBlockMetadata(x + 1, y, z));
			direction = blockDirection == Direction.SOUTH ? Direction.WEST
					: blockDirection == Direction.WEST ? Direction.SOUTH
							: blockDirection == Direction.NORTH ? Direction.EAST
									: Direction.NORTH;
		}
		setDirection(world, x, y, z, direction);
	}

}
