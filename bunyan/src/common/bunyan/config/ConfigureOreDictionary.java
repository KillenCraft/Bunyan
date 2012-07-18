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
import bunyan.blocks.DirectionalVanillaLog;
import bunyan.blocks.WideLog;

public class ConfigureOreDictionary {

	public static void initialize() {
		OreDictionary.registerOre("woodAcacia", new ItemStack(
				BunyanBlock.wood, 1, CustomLog.metaAcacia));
		OreDictionary.registerOre("woodFir", new ItemStack(
				BunyanBlock.wood, 1, CustomLog.metaFir));
		OreDictionary.registerOre("woodFir", new ItemStack(
				BunyanBlock.widewood, 1, WideLog.metaFir));
		OreDictionary.registerOre("woodOak", new ItemStack(
				BunyanBlock.widewood, 1, WideLog.metaOak));
		OreDictionary.registerOre("woodRedWood", new ItemStack(
				BunyanBlock.widewood, 1, WideLog.metaRedwood));
		OreDictionary.registerOre("woodRedWood", new ItemStack(
				BunyanBlock.widewood, 1, WideLog.metaRedwood));
		OreDictionary.registerOre("woodOak", new ItemStack(
				BunyanBlock.turnableVanillaWood, 1,
				DirectionalVanillaLog.metaOak));
		OreDictionary.registerOre("woodPine", new ItemStack(
				BunyanBlock.turnableVanillaWood, 1,
				DirectionalVanillaLog.metaPine));
		OreDictionary.registerOre("woodBirch", new ItemStack(
				BunyanBlock.turnableVanillaWood, 1,
				DirectionalVanillaLog.metaBirch));
		OreDictionary.registerOre("woodJungle", new ItemStack(
				BunyanBlock.turnableVanillaWood, 1,
				DirectionalVanillaLog.metaJungle));

		OreDictionary.registerOre("logWood", new ItemStack(
				BunyanBlock.wood, 1, CustomLog.metaAcacia));
		OreDictionary.registerOre("logWood", new ItemStack(
				BunyanBlock.wood, 1, CustomLog.metaFir));
		OreDictionary.registerOre("logWood", new ItemStack(
				BunyanBlock.widewood, 1, WideLog.metaFir));
		OreDictionary.registerOre("logWood", new ItemStack(
				BunyanBlock.widewood, 1, WideLog.metaRedwood));
		OreDictionary.registerOre("logWood", new ItemStack(
				BunyanBlock.turnableVanillaWood, 1,
				DirectionalVanillaLog.metaOak));
		OreDictionary.registerOre("logWood", new ItemStack(
				BunyanBlock.turnableVanillaWood, 1,
				DirectionalVanillaLog.metaPine));
		OreDictionary.registerOre("logWood", new ItemStack(
				BunyanBlock.turnableVanillaWood, 1,
				DirectionalVanillaLog.metaBirch));
		OreDictionary.registerOre("logWood", new ItemStack(
				BunyanBlock.turnableVanillaWood, 1,
				DirectionalVanillaLog.metaJungle));

		OreDictionary.registerOre("planksAcacia", new ItemStack(
				BunyanBlock.planks, 1, CustomWood.metaAcacia));
		OreDictionary.registerOre("planksFir", new ItemStack(
				BunyanBlock.planks, 1, CustomWood.metaFir));
		OreDictionary.registerOre("planksRedWood", new ItemStack(
				BunyanBlock.planks, 1, CustomWood.metaRedwood));

		OreDictionary.registerOre("planksWood", new ItemStack(
				BunyanBlock.planks, 1, CustomWood.metaAcacia));
		OreDictionary.registerOre("planksWood", new ItemStack(
				BunyanBlock.planks, 1, CustomWood.metaFir));
		OreDictionary.registerOre("planksWood", new ItemStack(
				BunyanBlock.planks, 1, CustomWood.metaRedwood));
	}

}
