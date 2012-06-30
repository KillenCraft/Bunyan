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

public class OneInThreeWorldGen extends WorldGenerator {

	private final WorldGenerator	rare;
	private final WorldGenerator	common;

	public OneInThreeWorldGen(WorldGenerator rare,
			WorldGenerator common)
	{
		this.rare = rare;
		this.common = common;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y,
			int z)
	{
		return random.nextInt(3) == 0 ?
				rare.generate(world, random, x,	y, z) :
				common.generate(world, random, x, y, z);
	}

}
