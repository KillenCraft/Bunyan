/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.blocks;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import bunyan.api.Direction;
import bunyan.api.DirectionalBlock;

public class WideLogBarkOnTop extends WideLog {

	public WideLogBarkOnTop(int id) {
		super(id);
	}

	@Override
	public int getRenderType() {
		return RenderManager.getRenderID();
	}

	@Override
	public int getTextureOffsetFromFacingSideAndMetadata(
			Direction facing, Direction side, int metadata)
	{
		int row = 0;
		int column = 0;

		if (side == Direction.DOWN)
			row = 3;
		else if (side != Direction.UP) if (side == facing.rightSide())
			row = 3;
		else if (side == facing) {
			row = 1;
			column = 1;
		} else if (side == facing.oppositeSide())
			row = 1;
		else
			column = 1;

		final int textureSet = getDataFromMetadata(metadata);
		return row * 16 + column + textureSet * 2;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLiving entity)
	{
		Direction facingBlock = Direction.NORTH;
		if (entity != null) {
			final int facingEntity = MathHelper
					.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;

			switch (facingEntity) {
				case 0:
					facingBlock = Direction.SOUTH;
					break;
				case 1:
					facingBlock = Direction.WEST;
					break;
				case 2:
					facingBlock = Direction.NORTH;
					break;
				case 3:
					facingBlock = Direction.EAST;
			}
			setFacing(world, x, y, z, facingBlock, true);
		}
	}

	@Override
	public void onLogTurner(EntityPlayer player, World world, int x,
			int y, int z, Direction side)
	{
		if (side == Direction.UP || side == Direction.DOWN) {
			final int metadata = DirectionalBlock
					.getDataFromMetadata(world
							.getBlockMetadata(x, y, z));
			world.setBlockAndMetadata(x, y, z,
					BunyanBlock.widewood.blockID, metadata);
			BunyanBlock.widewood
					.onBlockPlacedBy(world, x, y, z, player);
		} else
			DirectionalBlock.setFacing(world, x, y, z,
					side.oppositeSide(), true);
	}

	@Override
	public boolean render(IBlockAccess world, int x, int y, int z,
			int modelID)
	{
		return RenderManager.renderRotatedWideLogBarkTop(this, world,
				x, y, z, modelID);
	}

}
