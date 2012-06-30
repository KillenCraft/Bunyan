/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.terrain;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.IBiomeDecoration;

public class TreeDecoration implements IBiomeDecoration {

	private final int				attempts;
	private final WorldGenerator	worldGen;

	public TreeDecoration(int attempts, WorldGenerator worldGen) {
		this.attempts = attempts;
		this.worldGen = worldGen;
	}

	@Override
	public void decorate(World world, Random random, int x, int z) {

		int tries = attempts;

		if (random.nextInt(10) == 0) ++tries;

		for (int i = 0; i < tries; ++i) {
			final int xToTry = x + random.nextInt(16) + 8;
			final int zToTry = z + random.nextInt(16) + 8;
			worldGen.setScale(1.0D, 1.0D, 1.0D);
			worldGen.generate(world, random, xToTry,
					getYToTry(world, xToTry, zToTry), zToTry);
		}
	}

	protected int getYToTry(World world, int x, int z) {
		return world.getHeightValue(x, z);
	}
}
