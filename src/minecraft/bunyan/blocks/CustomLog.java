/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.blocks;

import java.util.Random;

import net.minecraft.src.BlockLog;
import net.minecraft.src.forge.ITextureProvider;

public class CustomLog extends BlockLog implements ITextureProvider {

	// metadata values
	public static final int		metaRedwood	= 0;
	public static final int		metaFir		= 1;
	public static final int		metaAcacia	= 2;

	// attributes
	private static final float	HARDNESS	= 2.0F;

	public CustomLog(int id) {
		super(id);
		blockIndexInTexture = 0;
		setStepSound(soundWoodFootstep);
		setRequiresSelfNotify();
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		final int row = side != 0 && side != 1 ? 0 : 1;
		final int column = metadata;
		return row * 16 + column;
	}

	@Override
	public float getHardness() {
		return getHardness(0);
	}

	@Override
	public float getHardness(int meta) {
		return HARDNESS;
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
