/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan;

import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import bunyan.terrain.AlpineFirGen;
import bunyan.terrain.RedwoodForestRedwoodGen;
import bunyan.terrain.RedwoodLushFirRedwoodGen;
import bunyan.terrain.SavannaAcaciaGen;
import bunyan.terrain.TemperateRainforestFirGen;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IPlugin;
import extrabiomes.api.TerrainGenManager;

public enum ExtrabiomesPlugin implements IPlugin {
	INSTANCE;

	private static void useBunyanWoodInExtrabiomes() {

		// Uses the ExtraBiomes XL API to set block to use in terrain
		// generation
		TerrainGenManager.blockRedwoodWood = BunyanBlock.wood;
		TerrainGenManager.metaRedwoodWood = CustomLog.metaRedwood;

		TerrainGenManager.blockFirWood = BunyanBlock.wood;
		TerrainGenManager.metaFirWood = CustomLog.metaFir;

		TerrainGenManager.blockAcaciaWood = BunyanBlock.wood;
		TerrainGenManager.metaAcaciaLeaves = CustomLog.metaAcacia;
	}

	@Override
	public String getName() {
		return Bunyan.getName();
	}

	@Override
	public void inject() {

		useBunyanWoodInExtrabiomes();
		replaceExtrabiomesTrees();

	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private static void replaceExtrabiomesTrees() {
		TerrainGenManager.enableAcaciaGen = false; // disable
													// Extrabiomes
													// normal acacia
													// generation
		BiomeDecorationsManager.biomeDecorations.get(
				BiomeManager.savanna).add(new SavannaAcaciaGen());

		TerrainGenManager.enableFirGen = false; // disable Extrabiomes
												// normal fir generation
		BiomeDecorationsManager.biomeDecorations.get(
				BiomeManager.alpine).add(new AlpineFirGen());
		BiomeDecorationsManager.biomeDecorations.get(
				BiomeManager.temperaterainforest).add(
				new TemperateRainforestFirGen());

		TerrainGenManager.enableRedwoodGen = false; // disable
													// Extrabiomes
													// normal redwood
													// generation
		BiomeDecorationsManager.biomeDecorations.get(
				BiomeManager.redwoodforest).add(
				new RedwoodForestRedwoodGen());

		BiomeDecorationsManager.biomeDecorations.get(
				BiomeManager.redwoodlush).add(
				new RedwoodLushFirRedwoodGen());
	}

}
