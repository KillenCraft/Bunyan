/**
 * Copyright (c) Scott Killen, 2012
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * /MMPL-1.0.txt
 */

package net.minecraft.src;

import bunyan.Bunyan;
import net.minecraft.src.forge.NetworkMod;

public class mod_Bunyan extends NetworkMod {

	@Override
	public String getName() {
		return Bunyan.getName();
	}

	@Override
	public String getVersion() {
		return Bunyan.getVersion();
	}

	@Override
	public void load() {
		Bunyan.onLoad(this);
	}

}
