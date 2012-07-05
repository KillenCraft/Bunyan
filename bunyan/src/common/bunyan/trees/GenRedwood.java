/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.trees;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.World;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.WideLog;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.TerrainGenManager;

public class GenRedwood extends TreeGenStraightNoBranchesWide {

	public GenRedwood(boolean doNotify) {
		super(doNotify);
		blockLeaf = TerrainGenManager.blockRedwoodLeaves.blockID;
		metaLeaf = TerrainGenManager.metaRedwoodLeaves;
		blockWood = BunyanBlock.widewood.blockID;
		metaWood = WideLog.metaRedwood;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		final BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		if (biome == BiomeManager.wasteland)
			if (rand.nextInt(4) == 0)
				return new DeadTreeHuge(metaWood).generate(world, rand,
						x, y, z);

		return super.generate(world, rand, x, y, z);
	}

	@Override
	protected int getRandomHeight(Random random) {
		return random.nextInt(30) + 32;
	}

	@Override
	protected int getRandomHeightLeavesStart(Random random) {
		return 1 + random.nextInt(24);
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
