/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.forge.MinecraftForgeClient;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.FMLRegistry;

public enum Proxy {
	INSTANCE;

	public static void addName(Object instance, String name) {
		if (instance != null)
			FMLCommonHandler.instance().addNameForObject(instance,
					"en_US", name);
	}

	public static File getMinecraftDir() {
		return Minecraft.getMinecraftDir();
	}

	public static void preloadTexture(String filename) {
		MinecraftForgeClient.preloadTexture(filename);
	}

	public static void registerBlock(Block block) {
		if (block != null) FMLRegistry.registerBlock(block);
	}

	public static void registerBlock(Block block, Class type) {
		if (block != null) FMLRegistry.registerBlock(block, type);
	}

}
