/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan;

import net.minecraft.src.Block;
import net.minecraft.src.forge.NetworkMod;
import bunyan.config.Config;
import extrabiomes.api.PluginManager;
import extrabiomes.api.TerrainGenManager;

public enum Bunyan {
	INSTANCE; // This enforces this object's singularity

	private static final String	NAME	= "Bunyan";
	private static final String	VERSION	= "0.2";

	public static boolean clientSideRequired() {
		// Because this mod define custom blocks, the client side mod is
		// always required when the mod is present on the server.
		return true;
	}

	public static String getName() {
		return NAME;
	}

	public static String getVersion() {
		return VERSION;
	}

	/**
	 * Processes events that need to happen when the mod is loaded
	 * 
	 * @param mod
	 *            a reference to the Buntan mod.
	 */
	public static void onLoad(NetworkMod mod) {
		Proxy.preloadTexture("/bunyan/blocks/blocks.png");

		// Delegate mod configuration to the Config object
		Config.onLoad();

		PluginManager.plugins.add(ExtrabiomesPlugin.INSTANCE);

		// Our trees use parts of the Extrabiomes API. Here, we set it
		// up so that it works even if that mod is not installed.
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.dirt.blockID));
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.grass.blockID));
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.tilledField.blockID));
	}

	public static void onModsLoaded() {
		// Handle post loading configuration tasks
		Config.onModsLoaded();
	}

}
