/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.config;

import bunyan.recipes.RecipesCrafting;
import bunyan.recipes.RecipesTools;
import bunyan.recipes.RecipesMisc;
import bunyan.recipes.RecipesWeapons;

public class ConfigureRecipes {

	public static void initialize() {
		RecipesCrafting.addRecipes();
		RecipesTools.addRecipes();
		RecipesWeapons.addRecipes();
		RecipesMisc.addRecipes();
	}

}
