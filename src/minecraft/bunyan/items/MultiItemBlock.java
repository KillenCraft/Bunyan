/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.items;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class MultiItemBlock extends ItemBlock {

	public MultiItemBlock(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	Block getBlock() {
		return Block.blocksList[getBlockID()];
	}

	@Override
	public int getColorFromDamage(int md, int par2) {
		return getBlock().getRenderColor(md);
	}

	@Override
	public int getIconFromDamage(int md) {
		return getBlock().getBlockTextureFromSideAndMetadata(0, md);
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return Block.blocksList[getBlockID()].getBlockName()
				+ "." + itemstack.getItemDamage();
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}
