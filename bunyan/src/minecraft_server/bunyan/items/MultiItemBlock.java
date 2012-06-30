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

/**
 * This handles all of the mundane tasks of having multiple blocks
 * stuffed into a single blockID. It delegates everything it can to the
 * Block.
 */
public class MultiItemBlock extends ItemBlock {

	public MultiItemBlock(int id) {
		super(id);
		setMaxDamage(0); // Damage values are used to distinguish block
							// types, so tell the game not to use damage
							// values for this type of item.

		setHasSubtypes(true); // Let the game know that there are
								// several blocks/items stuffed onto one
								// blockID
	}

	Block getBlock() {
		return Block.blocksList[getBlockID()];
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return "tile." + Block.blocksList[getBlockID()].getBlockName() + "."
				+ itemstack.getItemDamage();
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}
