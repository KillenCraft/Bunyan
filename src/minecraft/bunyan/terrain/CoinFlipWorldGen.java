/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.terrain;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class CoinFlipWorldGen extends WorldGenerator {

	private final WorldGenerator	heads;
	private final WorldGenerator	tails;

	public CoinFlipWorldGen(WorldGenerator heads, WorldGenerator tails) {
		this.heads = heads;
		this.tails = tails;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y,
			int z)
	{
		return random.nextInt(2) == 0 ?
				heads.generate(world, random, x, y, z) :
				tails.generate(world, random, x, y, z);
	}

}
