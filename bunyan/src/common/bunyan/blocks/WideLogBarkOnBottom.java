/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.blocks;

import net.minecraft.src.IBlockAccess;
import bunyan.api.Direction;

public class WideLogBarkOnBottom extends WideLogBarkOnTop {

	public WideLogBarkOnBottom(int id) {
		super(id);
	}

	@Override
	public int getTextureOffsetFromFacingSideAndMetadata(
			Direction facing, Direction side, int metadata)
	{
		int row = 0;
		int column = 1;

		if (side == Direction.UP)
			row = 3;
		else if (side != Direction.DOWN)
			if (side == facing.rightSide())
				column = 0;
			else if (side == facing) {
				row = 2;
				column = 0;
			} else if (side == facing.oppositeSide())
				row = 2;
			else
				row = 3;

		final int textureSet = getDataFromMetadata(metadata);
		return row * 16 + column + textureSet * 2;
	}

}
