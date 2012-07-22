/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.api;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

/**
 * Implement this interface to react to the log turner tool
 */
public interface ITurnable {

	/**
	 * Called when the player right clicks with the log turner rool
	 * 
	 * @param player
	 *            who used the tool
	 * @param world
	 *            current world
	 * @param x
	 * @param y
	 * @param z
	 * @param side
	 *            tool used on
	 */
	void onLogTurner(EntityPlayer player, World world, int x, int y,
			int z, Direction side);

}
