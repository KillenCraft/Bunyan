/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.recipes;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import bunyan.Proxy;
import bunyan.blocks.BunyanBlock;

public class RecipesWeapons {

	public static void addRecipes() {
		Proxy.addRecipe(new ItemStack(Item.swordWood), new Object[] {
				"X", "X", "#", Character.valueOf('X'),
				BunyanBlock.planks, Character.valueOf('#'), Item.stick });
	}

}
