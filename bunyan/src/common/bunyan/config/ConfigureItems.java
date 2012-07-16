/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.config;

import net.minecraft.src.forge.Configuration;
import bunyan.Proxy;
import bunyan.items.BunyanItem;
import bunyan.items.LogTurner;

public class ConfigureItems {

	public static void addNames() {
		Proxy.addName(BunyanItem.logTurner, "Log Turner");
	}

	public static void initialize() {
		final int turnerID = Config.getOrCreateIntProperty(
				"logturner.id", Configuration.CATEGORY_ITEM, 7858);
		BunyanItem.logTurner = new LogTurner(turnerID)
				.setItemName("log.turner");
	}

}
