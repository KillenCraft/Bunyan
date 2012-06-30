/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.terrain;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import bunyan.trees.GenFirTree;

public class AlpineFirGen extends TreeDecoration {

	public AlpineFirGen() {
		super(7, new GenFirTree(false));
	}

}
