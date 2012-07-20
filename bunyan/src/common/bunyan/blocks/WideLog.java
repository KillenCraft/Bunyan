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

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import bunyan.api.Direction;
import bunyan.api.DirectionalBlock;
import bunyan.api.TurnableLog;

public class WideLog extends TurnableLog {

	public static final int	metaRedwood	= 0;
	public static final int	metaFir		= 1;
	public static final int	metaOak		= 2;

	public WideLog(int id) {
		super(id, 48);
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(blockID, 1, metaRedwood));
		itemList.add(new ItemStack(blockID, 1, metaFir));
		itemList.add(new ItemStack(blockID, 1, metaOak));
	}

	@Override
	public int getRenderType() {
		// even though it is turnable, render like a standard block.
		return 0;
	}

	@Override
	public String getTextureFile() {
		return "/bunyan/blocks/blocks.png";
	}

	@Override
	public int getTextureOffsetFromFacingSideAndMetadata(
			Direction facing, Direction side, int metadata)
	{
		final int textureSet = getDataFromMetadata(metadata);
		int row = 0;
		int column = 0;

		switch (side) {
			case DOWN:
			case UP:
				switch (facing) {
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
				switch (facing) {
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
				switch (facing) {
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
				switch (facing) {
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
				switch (facing) {
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

		return row * 16 + column + textureSet * 2;
	}

	@Override
	public int idDropped(int metadata, Random random, int alwaysZero) {
		return blockID;
	}

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
					facingBlock = Direction.SOUTH;
					break;
				case 1:
					facingBlock = Direction.WEST;
					break;
				case 2:
					facingBlock = Direction.EAST;
					break;
				case 3:
					facingBlock = Direction.NORTH;
			}
			setFacing(world, x, y, z, facingBlock, true);
		}
	}

	@Override
	public void onLogTurner(EntityPlayer player, World world, int x,
			int y, int z, Direction side)
	{
		if (side == Direction.UP || side == Direction.DOWN) {
			final int metadata = DirectionalBlock
					.getDataFromMetadata(world
							.getBlockMetadata(x, y, z));
			world.setBlockAndMetadataWithNotify(x, y, z,
					BunyanBlock.wood.blockID, metadata);
		} else {
			Direction facing = Direction.NORTH;
			switch (side) {
				case NORTH:
				case EAST:
					facing = side.oppositeSide();
					break;
				default:
					facing = side.rightSide();
			}
			DirectionalBlock.setFacing(world, x, y, z, facing, true);
		}
	}

}
