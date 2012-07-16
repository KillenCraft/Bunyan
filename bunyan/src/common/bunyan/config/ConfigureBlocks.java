/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.config;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import bunyan.Proxy;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import bunyan.blocks.CustomWood;
import bunyan.blocks.DirectionalVanillaLog;
import bunyan.blocks.WideLog;

public class ConfigureBlocks {

	private static void addCustomLogNames() {
		Proxy.addName(new ItemStack(BunyanBlock.wood, 1,
				CustomLog.metaFir), "Fir Log");
		Proxy.addName(new ItemStack(BunyanBlock.wood, 1,
				CustomLog.metaAcacia), "Acacia Log");
	}

	private static void addDirectionalVanillaLogNames() {
		String name = Proxy.getObjectDisplayName(new ItemStack(
				Block.wood, 1, DirectionalVanillaLog.metaOak));
		Proxy.addName(new ItemStack(BunyanBlock.direcionalVanillaWood,
				1, DirectionalVanillaLog.metaOak), name);
		name = Proxy.getObjectDisplayName(new ItemStack(Block.wood, 1,
				DirectionalVanillaLog.metaPine));
		Proxy.addName(new ItemStack(BunyanBlock.direcionalVanillaWood,
				1, DirectionalVanillaLog.metaPine), name);
		name = Proxy.getObjectDisplayName(new ItemStack(Block.wood, 1,
				DirectionalVanillaLog.metaBirch));
		Proxy.addName(new ItemStack(BunyanBlock.direcionalVanillaWood,
				1, DirectionalVanillaLog.metaBirch), name);
		name = Proxy.getObjectDisplayName(new ItemStack(Block.wood, 1,
				DirectionalVanillaLog.metaJungle));
		Proxy.addName(new ItemStack(BunyanBlock.direcionalVanillaWood,
				1, DirectionalVanillaLog.metaJungle), name);
	}

	public static void addNames() {
		addCustomLogNames();
		addWideLogNames();
		addPlankNames();
		addDirectionalVanillaLogNames();
	}

	private static void addPlankNames() {
		Proxy.addName(new ItemStack(BunyanBlock.planks, 1,
				CustomWood.metaRedwood), "Redwood Planks");
		Proxy.addName(new ItemStack(BunyanBlock.planks, 1,
				CustomWood.metaFir), "Fir Planks");
		Proxy.addName(new ItemStack(BunyanBlock.planks, 1,
				CustomWood.metaAcacia), "Acacia Planks");
	}

	private static void addWideLogNames() {
		Proxy.addName(new ItemStack(BunyanBlock.widewood, 1,
				WideLog.metaRedwood), "Quarter Huge Redwood Log");
		Proxy.addName(new ItemStack(BunyanBlock.widewood, 1,
				WideLog.metaFir), "Quarter Huge Fir Log");
		Proxy.addName(new ItemStack(BunyanBlock.widewood, 1,
				WideLog.metaOak), "Quarter Huge Oak Log");
	}

	public static void initialize() {
		final int woodID = Config.getOrCreateBlockIdProperty("wood.id",
				160);
		BunyanBlock.wood = new CustomLog(woodID)
				.setBlockName("bunyan.wood");

		final int widewoodID = Config.getOrCreateBlockIdProperty(
				"widewood.id", 161);
		BunyanBlock.widewood = new WideLog(widewoodID)
				.setBlockName("bunyan.widewood");

		final int plankID = Config.getOrCreateBlockIdProperty(
				"plank.id", 163);
		BunyanBlock.planks = new CustomWood(plankID)
				.setBlockName("bunyan.plank");

		final int directionalWoodID = Config
				.getOrCreateBlockIdProperty("directional.wood.id", 161);
		BunyanBlock.direcionalVanillaWood = new DirectionalVanillaLog(
				directionalWoodID)
				.setBlockName("bunyan.directional.wood");

		Proxy.registerBlock(BunyanBlock.wood,
				bunyan.items.MultiItemBlock.class);
		Proxy.registerBlock(BunyanBlock.widewood,
				bunyan.items.MultiItemBlock.class);
		Proxy.registerBlock(BunyanBlock.planks,
				bunyan.items.MultiItemBlock.class);
		Proxy.registerBlock(BunyanBlock.direcionalVanillaWood,
				bunyan.items.MultiItemBlock.class);
	}

}
