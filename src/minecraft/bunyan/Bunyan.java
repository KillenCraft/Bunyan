/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan;

import net.minecraft.src.forge.NetworkMod;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import bunyan.config.Config;
import extrabiomes.api.TerrainGenManager;

public enum Bunyan {
	INSTANCE;

	private static final String	NAME	= "Bunyan";
	private static final String	VERSION	= "0.1";

	public static boolean clientSideRequired() {
		return true;
	}

	public static String getName() {
		return NAME;
	}

	public static String getVersion() {
		return VERSION;
	}

	public static void onLoad(NetworkMod mod) {
		Proxy.preloadTexture("/bunyan/blocks/blocks.png");

		Config.onLoad();
	}

	public static void onModsLoaded() {
		Config.onModsLoaded();

		TerrainGenManager.blockRedwoodWood = BunyanBlock.wood;
		TerrainGenManager.blockFirWood = BunyanBlock.wood;
		TerrainGenManager.blockAcaciaWood = BunyanBlock.wood;

		TerrainGenManager.metaRedwoodWood = CustomLog.metaRedwood;
		TerrainGenManager.metaFirWood = CustomLog.metaFir;
		TerrainGenManager.metaAcaciaLeaves = CustomLog.metaAcacia;
	}

}
