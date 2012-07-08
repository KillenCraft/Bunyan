/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.config;

import net.minecraft.src.ItemStack;
import net.minecraft.src.forge.oredict.OreDictionary;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import bunyan.blocks.CustomWood;
import bunyan.blocks.WideLog;

public class ConfigureOreDictionary {

	public static void initialize() {
		OreDictionary.registerOre("woodFir", new ItemStack(
				BunyanBlock.wood, 1, CustomLog.metaFir));
		OreDictionary.registerOre("woodFir", new ItemStack(
				BunyanBlock.widewood, 1, WideLog.metaFir));
		OreDictionary.registerOre("woodRedWood", new ItemStack(
				BunyanBlock.widewood, 1, WideLog.metaRedwood));
		OreDictionary.registerOre("woodAcacia", new ItemStack(
				BunyanBlock.wood, 1, CustomLog.metaAcacia));
	}

}
