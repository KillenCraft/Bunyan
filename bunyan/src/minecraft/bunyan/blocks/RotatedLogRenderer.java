/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

import bunyan.api.Direction;
import bunyan.api.DirectionalBlock;

public class RotatedLogRenderer extends RenderBlocks {
	Direction	facing	= Direction.NORTH;

	public RotatedLogRenderer() {
		super();
	}

	public RotatedLogRenderer(IBlockAccess blockAccess) {
		super(blockAccess);
	}

	@Override
	public void renderBlockAsItem(Block block, int metadata, float color)
	{
		if (useInventoryTint) {
			final int renderColor = block.getRenderColor(metadata);

			final float red = (renderColor >> 16 & 255) / 255.0F;
			final float green = (renderColor >> 8 & 255) / 255.0F;
			final float blue = (renderColor & 255) / 255.0F;
			GL11.glColor4f(red * color, green * color, blue * color,
					1.0F);
		}

		final Tessellator tessellator = Tessellator.instance;

		block.setBlockBoundsForItemRender();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderBottomFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(0, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderTopFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(1, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderEastFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(2, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderWestFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(3, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderNorthFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(4, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderSouthFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(5, metadata));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	public boolean renderRotatedLog(Block block, int x, int y, int z) {
		final int metadata = blockAccess.getBlockMetadata(x, y, z);
		facing = DirectionalBlock.getFacingFromMetadata(metadata);

		switch (facing) {
			case NORTH:
				uvRotateSouth = 1;
				uvRotateNorth = 2;
				break;
			case SOUTH:
				uvRotateSouth = 2;
				uvRotateNorth = 1;
				uvRotateTop = 3;
				uvRotateBottom = 3;
				break;
			case WEST:
				uvRotateEast = 1;
				uvRotateWest = 2;
				uvRotateTop = 2;
				uvRotateBottom = 1;
				break;
			case EAST:
				uvRotateEast = 2;
				uvRotateWest = 1;
				uvRotateTop = 1;
				uvRotateBottom = 2;
				break;
			default:
				return false;
		}

		renderStandardBlock(block, x, y, z);
		uvRotateEast = 0;
		uvRotateWest = 0;
		uvRotateSouth = 0;
		uvRotateNorth = 0;
		uvRotateTop = 0;
		uvRotateBottom = 0;
		facing = Direction.NORTH;

		return true;
	}

}
