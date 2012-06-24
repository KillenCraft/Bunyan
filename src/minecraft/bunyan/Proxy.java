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
import net.minecraft.src.forge.IBonemealHandler;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.MinecraftForgeClient;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.FMLRegistry;

/**
 * The proxy object is used to communicate with the mod API, currently
 * Forge and Forge ModLoader. The reason for this abstraction is to make
 * it easier to support any new modding API and easier to adapt to
 * changes with the current API. When possible, the obsolete ModLoader,
 * now a proxy itself, is bypassed.
 * 
 */
public enum Proxy {
	INSTANCE; // This enforces this object's singularity

	/**
	 * Used to set the name of a game object as know to a player. If
	 * localizations are available, they are applied automatically.
	 * 
	 * @param instance
	 *            Block or ItemStack to name
	 * @param name
	 *            The name to use for this object
	 */
	public static void addName(Object instance, String name) {
		if (instance != null)
			FMLCommonHandler.instance().addNameForObject(instance,
					"en_US", name);
	}

	/**
	 * @return the root folder where game files are stored.
	 */
	public static File getMinecraftDir() {
		return Minecraft.getMinecraftDir();
	}

	/**
	 * Preload a texture. Textures must be preloaded before the first
	 * use, or they will cause visual anomalies.
	 * 
	 * @param filename
	 *            The path of the texture file to load, relative to
	 *            {@link #getMinecraftDir()}
	 */
	public static void preloadTexture(String filename) {
		MinecraftForgeClient.preloadTexture(filename);
	}

	/**
	 * Registers a new block with the game. Used for blocks that are
	 * <em>not</em> multifaceted.
	 * 
	 * @param block
	 *            Block to register.
	 */
	public static void registerBlock(Block block) {
		if (block != null) FMLRegistry.registerBlock(block);
	}

	/**
	 * Registers a new block with the game. Used for blocks that are
	 * multifaceted.
	 * 
	 * @param block
	 *            Block to register.
	 * @param type
	 *            The type to use to create an item for this block.
	 */
	public static void registerBlock(Block block, Class type) {
		if (block != null) FMLRegistry.registerBlock(block, type);
	}

	/**
	 * Registers a new bonemeal handler.
	 * 
	 * @param handler
	 *            The Handler to be registered
	 */
	public static void registerBonemealHandler(IBonemealHandler handler)
	{
		MinecraftForge.registerBonemealHandler(handler);
	}

}
