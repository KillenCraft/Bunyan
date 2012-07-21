/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.forge.NetworkMod;

public class RenderManager {

	public static int getRenderID() {
		// No op on server
		return 0;
	}

	public static void initialize(NetworkMod mod) {
		// No op on server
	}

	public static boolean renderRotatedLog(Block block,
			IBlockAccess world, int x, int y, int z, int modelID)
	{
		// No op on server
		return false;
	}

	public static boolean renderRotatedWideLogBarkBottom(Block block,
			IBlockAccess world, int x, int y, int z, int modelID)
	{
		// No op on server
		return false;
	}

	public static boolean renderRotatedWideLogBarkTop(Block block,
			IBlockAccess world, int x, int y, int z, int modelID)
	{
		// No op on server
		return false;
	}

}
