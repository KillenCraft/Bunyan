/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.config;

import java.io.File;

import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.Property;
import bunyan.Proxy;

public enum Config {
	INSTANCE;

	private static final String	CONFIG_BUNYAN_MAIN_CFG	= "/config/extrabiomes/bunyan.cfg";
	public static Configuration	config;

	public static int getOrCreateBlockIdProperty(String key,
			int defaultValue)
	{
		return Integer.parseInt(config.getOrCreateBlockIdProperty(key,
				defaultValue).value);
	}

	public static boolean getOrCreateBooleanProperty(String key,
			String kind, boolean defaultValue)
	{
		return Boolean.parseBoolean(config.getOrCreateBooleanProperty(
				key, kind, defaultValue).value);
	}

	public static int getOrCreateIntProperty(String string,
			String category, int defaultValue)
	{
		return Integer.parseInt(config.getOrCreateIntProperty(string,
				category, defaultValue).value);
	}

	public static Property getProperty(String key, String category,
			String defaultValue)
	{
		return config.getOrCreateProperty(key, category, defaultValue);
	}

	public static void onLoad() {

		config = new Configuration(new File(Proxy.getMinecraftDir(),
				CONFIG_BUNYAN_MAIN_CFG));
		config.load();

		ConfigureBlocks.initialize();
		ConfigureRecipes.initialize();
		config.save();
	}

	public static void onModsLoaded() {
		ConfigureBlocks.addNames();
		ConfigureOreDictionary.initialize();
	}

}
