/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan;

import net.minecraft.src.ItemStack;
import net.minecraft.src.forge.IOreHandler;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomWood;

public class WoodOreHandler implements IOreHandler {

	@Override
	public void registerOre(String oreClass, ItemStack ore) {
		if (oreClass.equals("woodAcacia"))
			Proxy.addRecipe(new ItemStack(BunyanBlock.plank, 4,
					CustomWood.metaAcacia), new Object[] { "#",
					Character.valueOf('#'), ore });
		else if (oreClass.equals("woodFir"))
			Proxy.addRecipe(new ItemStack(BunyanBlock.plank, 4,
					CustomWood.metaFir),
					new Object[] { "#", Character.valueOf('#'), ore });
		else if (oreClass.equals("woodRedWood"))
			Proxy.addRecipe(new ItemStack(BunyanBlock.plank, 4,
					CustomWood.metaRedwood), new Object[] { "#",
					Character.valueOf('#'), ore });
	}

}
