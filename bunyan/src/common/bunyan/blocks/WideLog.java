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
import net.minecraft.src.BlockLog;
import net.minecraft.src.Entity;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;
import bunyan.Direction;

public class WideLog extends BlockLog implements ITextureProvider {

	public static final int	metaRedwood	= 0;
	public static final int	metaFir		= 1;
	public static final int	metaOak		= 2;

	private static Direction directionFromMetadata(final int metadata) {
		return Direction.fromValue(((metadata & 3 << 2) >> 2) + 2);
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
		final int metadata = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z,
				metadataWithDirection(metadata, direction));
	}

	public WideLog(int id) {
		super(id);
		blockIndexInTexture = 0;
		setStepSound(soundWoodFootstep);
		setHardness(Block.wood.getHardness());
		setResistance(Block.wood.getExplosionResistance(null) * 5.0F);
		setRequiresSelfNotify();
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(blockID, 1, metaRedwood));
		itemList.add(new ItemStack(blockID, 1, metaFir));
		itemList.add(new ItemStack(blockID, 1, metaOak));
	}

	@Override
	protected int damageDropped(int metadata) {
		return metadata & 3;
	}

	@Override
	protected void dropBlockAsItem_do(World par1World, int par2,
			int par3, int par4, ItemStack par5ItemStack)
	{
		par5ItemStack.setItemDamage(damageDropped(par5ItemStack
				.getItemDamage()));
		super.dropBlockAsItem_do(par1World, par2, par3, par4,
				par5ItemStack);
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x,
			int y, int z, int metadata, int fortune)
	{
		return super.getBlockDropped(world, x, y, z,
				world.getBlockMetadata(x, y, z), fortune);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		final Direction direction = directionFromMetadata(metadata);
		final Direction face = Direction.fromValue(side);
		final int textureSet = metadata & 3;
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

		return (row + 3) * 16 + column + textureSet * 2;
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
	public String getTextureFile() {
		return "/bunyan/blocks/blocks.png";
	}

	@Override
	public int idDropped(int metadata, Random random, int alwaysZero) {
		return blockID;
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
