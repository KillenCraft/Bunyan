/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan;

import net.minecraft.src.forge.NetworkMod;

public class KeyPressManager {

	public static boolean isModeKeyPressed() {
		// No op on server
		return false;
	}

	public static void registerKeys(NetworkMod mod) {
		// No op on server
	}

}
