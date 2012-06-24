/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.terrain;

import bunyan.trees.GenRedwood;
import extrabiomes.api.ITreeFactory.TreeType;
import extrabiomes.api.TerrainGenManager;

public class RedwoodLushFirRedwoodGen extends TreeDecoration {

	public RedwoodLushFirRedwoodGen() {
		super(17, new CoinFlipWorldGen(
				TerrainGenManager.treeFactory.makeTreeGenerator(false,
						TreeType.FIR), new GenRedwood(false)));
	}

}
