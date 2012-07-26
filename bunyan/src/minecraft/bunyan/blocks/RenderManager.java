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
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

import bunyan.Proxy;
import bunyan.api.Direction;
import bunyan.api.DirectionalBlock;

public enum RenderManager {
	INSTANCE;

	private static int	renderID;

	public static int getRenderID() {
		return renderID;
	}

	public static void initialize(BaseMod mod) {
		renderID = Proxy.getUniqueBlockModelID(mod, true);
	}

	public static void onRenderInventoryBlock(RenderBlocks renderer,
			Block block, int metadata, int modelID)
	{
		renderBlockAsItem(block, metadata, 1.0F, renderer);
	}

	public static boolean onRenderWorldBlock(RenderBlocks renderer,
			IBlockAccess world, int x, int y, int z, Block block,
			int modelID)
	{
		if (block == BunyanBlock.turnableVanillaWood
				|| block == BunyanBlock.turnableCustomWood)
			return renderRotatedLog(block, x, y, z, renderer);
		else if (block == BunyanBlock.widewoodBarkBottom)
			return renderRotatedLogBarkBottom(block, x, y, z, renderer);
		else if (block == BunyanBlock.widewoodBarkTop)
			return renderRotatedLogBarkTop(block, x, y, z, renderer);
		return false;
	}

	public static void renderBlockAsItem(Block block, int metadata,
			float color, RenderBlocks renderer)
	{
		if (renderer.useInventoryTint) {
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
		renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(0, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(1, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(2, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(3, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(4, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(5, metadata));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	public static boolean renderRotatedLog(Block block, int x, int y,
			int z, RenderBlocks renderer)
	{
		final int metadata = renderer.blockAccess.getBlockMetadata(x,
				y, z);
		final Direction facing = DirectionalBlock
				.getFacingFromMetadata(metadata);

		switch (facing) {
			case NORTH:
				renderer.uvRotateSouth = 1;
				renderer.uvRotateNorth = 2;
				break;
			case SOUTH:
				renderer.uvRotateSouth = 2;
				renderer.uvRotateNorth = 1;
				renderer.uvRotateTop = 3;
				renderer.uvRotateBottom = 3;
				break;
			case WEST:
				renderer.uvRotateEast = 1;
				renderer.uvRotateWest = 2;
				renderer.uvRotateTop = 2;
				renderer.uvRotateBottom = 1;
				break;
			case EAST:
				renderer.uvRotateEast = 2;
				renderer.uvRotateWest = 1;
				renderer.uvRotateTop = 1;
				renderer.uvRotateBottom = 2;
				break;
			default:
				return false;
		}

		renderer.renderStandardBlock(block, x, y, z);
		renderer.uvRotateEast = 0;
		renderer.uvRotateWest = 0;
		renderer.uvRotateSouth = 0;
		renderer.uvRotateNorth = 0;
		renderer.uvRotateTop = 0;
		renderer.uvRotateBottom = 0;

		return true;
	}

	public static boolean renderRotatedLogBarkBottom(Block block,
			int x, int y, int z, RenderBlocks renderer)
	{
		final int metadata = renderer.blockAccess.getBlockMetadata(x,
				y, z);
		final Direction facing = DirectionalBlock
				.getFacingFromMetadata(metadata);

		switch (facing) {
			case NORTH:
				renderer.uvRotateNorth = 1;
				renderer.uvRotateSouth = 1;
				break;
			case SOUTH:
				renderer.uvRotateNorth = 2;
				renderer.uvRotateSouth = 2;
				renderer.uvRotateTop = 3;
				renderer.uvRotateBottom = 3;
				break;
			case EAST:
				renderer.uvRotateEast = 2;
				renderer.uvRotateWest = 2;
				renderer.uvRotateTop = 1;
				renderer.uvRotateBottom = 2;
				break;
			case WEST:
				renderer.uvRotateEast = 1;
				renderer.uvRotateWest = 1;
				renderer.uvRotateTop = 2;
				renderer.uvRotateBottom = 1;
				break;
			default:
				return false;
		}

		renderer.renderStandardBlock(block, x, y, z);
		renderer.uvRotateEast = 0;
		renderer.uvRotateWest = 0;
		renderer.uvRotateSouth = 0;
		renderer.uvRotateNorth = 0;
		renderer.uvRotateTop = 0;
		renderer.uvRotateBottom = 0;

		return true;
	}

	public static boolean renderRotatedLogBarkTop(Block block, int x,
			int y, int z, RenderBlocks renderer)
	{
		final int metadata = renderer.blockAccess.getBlockMetadata(x,
				y, z);
		final Direction facing = DirectionalBlock
				.getFacingFromMetadata(metadata);

		switch (facing) {
			case NORTH:
				renderer.uvRotateNorth = 2;
				renderer.uvRotateSouth = 2;
				break;
			case SOUTH:
				renderer.uvRotateNorth = 1;
				renderer.uvRotateSouth = 1;
				renderer.uvRotateEast = 2;
				renderer.uvRotateTop = 3;
				renderer.uvRotateBottom = 3;
				break;
			case EAST:
				renderer.uvRotateEast = 1;
				renderer.uvRotateWest = 1;
				renderer.uvRotateTop = 1;
				renderer.uvRotateBottom = 2;
				break;
			case WEST:
				renderer.uvRotateEast = 2;
				renderer.uvRotateWest = 2;
				renderer.uvRotateTop = 2;
				renderer.uvRotateBottom = 1;
				break;
			default:
				return false;
		}

		renderer.renderStandardBlock(block, x, y, z);
		renderer.uvRotateEast = 0;
		renderer.uvRotateWest = 0;
		renderer.uvRotateSouth = 0;
		renderer.uvRotateNorth = 0;
		renderer.uvRotateTop = 0;
		renderer.uvRotateBottom = 0;

		return true;
	}

}
