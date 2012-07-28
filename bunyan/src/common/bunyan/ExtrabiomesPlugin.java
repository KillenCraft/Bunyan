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
import bunyan.blocks.WideLog;
import bunyan.trees.GenFirTree;
import bunyan.trees.GenFirTreeHuge;
import bunyan.trees.GenRedwood;
import extrabiomes.api.IPlugin;
import extrabiomes.api.ITreeFactory.TreeType;
import extrabiomes.api.TerrainGenManager;

public enum ExtrabiomesPlugin implements IPlugin {
	INSTANCE;

	private static boolean	isActive	= false;

	public static boolean isActive() {
		return isActive;
	}

	private static void replaceExtrabiomesTrees() {
		TerrainGenManager.treeFactory.registerTreeGen(TreeType.FIR,
				new GenFirTree(false), false);
		TerrainGenManager.treeFactory.registerTreeGen(TreeType.FIR,
				new GenFirTree(true), true);
		TerrainGenManager.treeFactory.registerTreeGen(
				TreeType.FIR_HUGE, new GenFirTreeHuge(false), false);
		TerrainGenManager.treeFactory.registerTreeGen(
				TreeType.FIR_HUGE, new GenFirTreeHuge(true), true);
		TerrainGenManager.treeFactory.registerTreeGen(TreeType.REDWOOD,
				new GenRedwood(false), false);
		TerrainGenManager.treeFactory.registerTreeGen(TreeType.REDWOOD,
				new GenRedwood(true), true);
	}

	private static void useBunyanWoodInExtrabiomes() {

		// Uses the ExtraBiomes XL API to set block to use in terrain
		// generation
		TerrainGenManager.blockRedwoodWood = BunyanBlock.widewood;
		TerrainGenManager.metaRedwoodWood = WideLog.metaRedwood;

		TerrainGenManager.blockFirWood = BunyanBlock.wood;
		TerrainGenManager.metaFirWood = CustomLog.metaFir;

		TerrainGenManager.blockAcaciaWood = BunyanBlock.wood;
		TerrainGenManager.metaAcaciaWood = CustomLog.metaAcacia;
	}

	@Override
	public String getName() {
		return "Bunyan";
	}

	@Override
	public void inject() {

		isActive = true;
		useBunyanWoodInExtrabiomes();
		replaceExtrabiomesTrees();

	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
