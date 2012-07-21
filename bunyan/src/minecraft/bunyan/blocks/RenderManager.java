/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.blocks;

import net.minecraft.src.BaseMod;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import bunyan.Proxy;
import bunyan.api.TurnableLog;

public class RenderManager {

	static RotatedLogRenderer	rotatedLogRenderer	= null;
	private static int			renderID;

	public static int getRenderID() {
		return renderID;
	}

	public static void initialize(BaseMod mod) {
		renderID = Proxy.getUniqueBlockModelID(mod, true);
	}

	public static void onRenderInventoryBlock(RenderBlocks renderer,
			Block block, int metadata, int modelID)
	{
		if (rotatedLogRenderer == null)
			rotatedLogRenderer = new RotatedLogRenderer();
		rotatedLogRenderer.renderBlockAsItem(block, metadata, 1.0F);
	}

	public static boolean onRenderWorldBlock(RenderBlocks renderer,
			IBlockAccess world, int x, int y, int z, Block block,
			int modelID)
	{
		return TurnableLog.RenderBlock(world, x, y, z, block, modelID);
	}

}
