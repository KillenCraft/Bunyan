/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.trees;

import java.util.Random;

import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import extrabiomes.api.TerrainGenManager;

public class GenFirTree extends TreeGenStraightNoBranches {

	public GenFirTree(boolean doNotify) {
		super(doNotify);
		blockLeaf = TerrainGenManager.blockFirLeaves.blockID;
		metaLeaf = TerrainGenManager.metaFirLeaves;
		blockWood = BunyanBlock.wood.blockID;
		metaWood = CustomLog.metaFir;
	}

	@Override
	protected int getRandomHeight(Random random) {
		return random.nextInt(8) + 24;
	}

	@Override
	protected int getRandomHeightLeavesStart(Random random) {
		return 1 + random.nextInt(12);
	}

	@Override
	protected int getRandomLeafBlocksAboveTrunk(Random random) {
		return random.nextInt(3);
	}

	@Override
	protected int getRandomMaxLeafRadius(Random random) {
		return 2 + random.nextInt(6);
	}
}