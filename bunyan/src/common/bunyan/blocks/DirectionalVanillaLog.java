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

import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import bunyan.api.Direction;

public class DirectionalVanillaLog extends DirectionalLog {

	public static final int	metaOak		= 0;

	public static final int	metaPine	= 1;
	public static final int	metaBirch	= 2;
	public static final int	metaJungle	= 3;

	public DirectionalVanillaLog(int id) {
		super(id);
		blockIndexInTexture = 6;
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(blockID, 1, metaOak));
		itemList.add(new ItemStack(blockID, 1, metaPine));
		itemList.add(new ItemStack(blockID, 1, metaBirch));
		itemList.add(new ItemStack(blockID, 1, metaJungle));
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		final Direction direction = directionFromMetadata(metadata);
		final Direction face = Direction.fromValue(side);
		final int textureSet = typeFromMetadata(metadata);
		int column = 0;

		switch (face) {
			case DOWN:
			case UP:
				switch (direction) {
					case NORTH:
						column = 1;
						break;
					case SOUTH:
						column = 3;
						break;
					case WEST:
						column = 4;
						break;
					case EAST:
						column = 2;
						break;
					default:
						break;
				}
				if (face == Direction.DOWN) column += 4;
				break;
			case NORTH:
				switch (direction) {
					case NORTH:
					case SOUTH:
						column = 0;
						break;
					case WEST:
						column = 2;
						break;
					case EAST:
						column = 4;
						break;
					default:
						break;
				}
				break;
			case SOUTH:
				switch (direction) {
					case NORTH:
					case SOUTH:
						column = 0;
						break;
					case WEST:
						column = 4;
						break;
					case EAST:
						column = 2;
						break;
					default:
						break;
				}
				break;
			case WEST:
				switch (direction) {
					case NORTH:
						column = 4;
						break;
					case SOUTH:
						column = 2;
						break;
					case WEST:
					case EAST:
						column = 0;
						break;
					default:
						break;
				}
				break;
			case EAST:
				switch (direction) {
					case NORTH:
						column = 2;
						break;
					case SOUTH:
						column = 4;
						break;
					case WEST:
					case EAST:
						column = 0;
						break;
					default:
						break;
				}
		}

		return blockIndexInTexture
				+ (column == 0 ? 0 : column + textureSet * 16);
	}

	@Override
	public int idDropped(int metadata, Random random, int alwaysZero) {
		return Block.wood.blockID;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLiving entity)
	{
		if (entity == null)
			setDirection(world, x, y, z, Direction.NORTH);
		else {
			final int facing = MathHelper
					.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;

			if (facing == 0)
				setDirection(world, x, y, z, Direction.NORTH);

			if (facing == 1)
				setDirection(world, x, y, z, Direction.EAST);

			if (facing == 2)
				setDirection(world, x, y, z, Direction.SOUTH);

			if (facing == 3)
				setDirection(world, x, y, z, Direction.WEST);
		}
	}

}
