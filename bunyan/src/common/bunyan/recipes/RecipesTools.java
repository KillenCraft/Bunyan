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

public class RecipesTools {

	public static void addRecipes() {

		Proxy.addRecipe(new ItemStack(Item.pickaxeWood), new Object[] {
				"XXX", " # ", " # ", Character.valueOf('X'),
				BunyanBlock.plank, Character.valueOf('#'), Item.stick });

		Proxy.addRecipe(new ItemStack(Item.shovelWood), new Object[] {
				"X", "#", "#", Character.valueOf('X'),
				BunyanBlock.plank, Character.valueOf('#'), Item.stick });

		Proxy.addRecipe(new ItemStack(Item.axeWood), new Object[] {
				"XX", "X#", " #", Character.valueOf('X'),
				BunyanBlock.plank, Character.valueOf('#'), Item.stick });

		Proxy.addRecipe(new ItemStack(Item.hoeWood), new Object[] {
				"XX", " #", " #", Character.valueOf('X'),
				BunyanBlock.plank, Character.valueOf('#'), Item.stick });
	}

}
