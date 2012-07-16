/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;

public abstract class DirectionalLog extends DirectionalBlock {

	public DirectionalLog(int par1) {
		super(par1);
		setStepSound(soundWoodFootstep);
		setHardness(Block.wood.getHardness());
		setResistance(Block.wood.getExplosionResistance(null) * 5.0F);
		setRequiresSelfNotify();
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

}