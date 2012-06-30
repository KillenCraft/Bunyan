/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.terrain;

import extrabiomes.api.TerrainGenManager;
import extrabiomes.api.ITreeFactory.TreeType;
import net.minecraft.src.WorldGenerator;

public class SavannaAcaciaGen extends TreeDecoration {

	public SavannaAcaciaGen() {
		super(0, TerrainGenManager.treeFactory.makeTreeGenerator(false, TreeType.ACACIA));
	}

}
