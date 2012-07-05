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

public class CustomLog extends BlockLog {

	// metadata values
	public static final int	metaFir		= 1;
	public static final int	metaAcacia	= 2;

	public CustomLog(int id) {
		super(id);
		blockIndexInTexture = 0;
		setStepSound(soundWoodFootstep);
		setRequiresSelfNotify();
		setHardness(Block.wood.getHardness());
		setResistance(Block.wood.getExplosionResistance(null) * 5.0F);
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(blockID, 1, metaFir));
		itemList.add(new ItemStack(blockID, 1, metaAcacia));
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		final int row = side != 0 && side != 1 ? 0 : 1;
		final int column = metadata;
		return row * 16 + column;
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

}
