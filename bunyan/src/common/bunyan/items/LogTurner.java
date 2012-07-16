/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.items;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import bunyan.Direction;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.DirectionalBlock;

public class LogTurner extends Item {

	public LogTurner(int par1) {
		super(par1);
		setIconIndex(0);
	}

	@Override
	public String getTextureFile() {
		return "/bunyan/items/items.png";
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side)
	{
		final int id = world.getBlockId(x, y, z);
		if (id != Block.wood.blockID
				&& id != BunyanBlock.direcionalVanillaWood.blockID)
			return false;
		final int metadata = world.getBlockMetadata(x, y, z);
		if (id == Block.wood.blockID) {
			if (side != 0 && side != 1) {
				world.setBlockAndMetadata(x, y, z,
						BunyanBlock.direcionalVanillaWood.blockID,
						metadata);
				DirectionalBlock.setDirection(world, x, y, z,
						Direction.fromValue(side));
			}
		} else if (id == BunyanBlock.direcionalVanillaWood.blockID) {
			if (side == 0 || side == 1)
				world.setBlockAndMetadataWithNotify(x, y, z,
						Block.wood.blockID,
						DirectionalBlock.typeFromMetadata(metadata));
			DirectionalBlock.setDirection(world, x, y, z,
					Direction.fromValue(side));
		}
		return true;
	}

}
