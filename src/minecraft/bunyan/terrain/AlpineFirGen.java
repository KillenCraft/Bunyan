/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.terrain;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import extrabiomes.api.ITreeFactory.TreeType;
import extrabiomes.api.TerrainGenManager;

public class AlpineFirGen extends TreeDecoration {

	public AlpineFirGen() {
		super(7, TerrainGenManager.treeFactory.makeTreeGenerator(false,
				TreeType.FIR));
	}

	@Override
	public int getYToTry(World world, int x, int z) {
		final int y = super.getYToTry(world, x, z);
		return world.getBlockId(x, y, z) == Block.snow.blockID ? y - 1
				: y;
	}

}
