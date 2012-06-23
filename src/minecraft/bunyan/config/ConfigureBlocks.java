/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.config;

import net.minecraft.src.ItemStack;
import bunyan.Proxy;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;

public class ConfigureBlocks {

	public static void addNames() {
		Proxy.addName(new ItemStack(BunyanBlock.wood, 1,
				CustomLog.metaRedwood), "Redwood Log");
		Proxy.addName(new ItemStack(BunyanBlock.wood, 1,
				CustomLog.metaFir), "Fir Log");
		Proxy.addName(new ItemStack(BunyanBlock.wood, 1,
				CustomLog.metaAcacia), "Acacia Log");
	}

	public static void initialize() {

		final int woodID = Config.getOrCreateBlockIdProperty("wood.id",
				160);
		BunyanBlock.wood = new CustomLog(woodID)
				.setBlockName("tile.bunyan.wood");

		Proxy.registerBlock(BunyanBlock.wood,
				bunyan.items.MultiItemBlock.class);
	}

}
