/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.trees;

import java.util.Random;

import net.minecraft.src.World;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLeaves;
import bunyan.blocks.WideLog;
import extrabiomes.api.TerrainGenManager;

public class GenFirTreeHuge extends TreeGenStraightNoBranchesWide {

	public GenFirTreeHuge(boolean doNotify) {
		super(doNotify);
		blockLeaf = BunyanBlock.leaves.blockID;
		metaLeaf = CustomLeaves.metaFir;
		blockWood = BunyanBlock.widewood.blockID;
		metaWood = WideLog.metaFir;
	}

	@Override
	protected int getRandomHeight(Random random) {
		return random.nextInt(16) + 32;
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
		return 2 + random.nextInt(9);
	}
}
