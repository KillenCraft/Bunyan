/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.forge.IOreHandler;
import net.minecraft.src.forge.NetworkMod;
import net.minecraft.src.forge.oredict.OreDictionary;
import bunyan.blocks.RenderManager;
import bunyan.config.Config;
import bunyan.trees.DeadTreeHuge;
import bunyan.trees.GenFirTree;
import bunyan.trees.GenFirTreeHuge;
import bunyan.trees.GenOakHuge;
import bunyan.trees.GenRedwood;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.PluginManager;
import extrabiomes.api.TerrainGenManager;

public enum Bunyan {
	INSTANCE; // This enforces this object's singularity

	private static final String	NAME	= "Bunyan";
	private static final String	VERSION	= "1.4";

	private static IOreHandler	woodOreHandler;

	public static boolean clientSideRequired() {
		// Because this mod define custom blocks, the client side mod is
		// always required when the mod is present on the server.
		return true;
	}

	public static String getName() {
		return NAME;
	}

	public static String getVersion() {
		return VERSION;
	}

	public static void onGenerateSurface(World world, Random random,
			int chunkX, int chunkZ)
	{
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
				chunkZ);

		if (biome == BiomeGenBase.forest
				|| biome == BiomeGenBase.forestHills
				|| biome == BiomeManager.forestedhills
				|| biome == BiomeManager.forestedisland
				|| biome == BiomeManager.rainforest)
			if (random.nextInt(100) == 0) {
				final int x = chunkX + random.nextInt(16) + 8;
				final int z = chunkZ + random.nextInt(16) + 8;
				final int y = world.getHeightValue(x, z);
				new GenOakHuge(false).generate(world, random, x, y, z);
			}
		if (biome == BiomeGenBase.plains
				|| biome == BiomeGenBase.extremeHillsEdge)
			if (random.nextInt(1000) == 0) {
				final int x = chunkX + random.nextInt(16) + 8;
				final int z = chunkZ + random.nextInt(16) + 8;
				final int y = world.getHeightValue(x, z);
				new GenOakHuge(false).generate(world, random, x, y, z);
			}
		if (biome == BiomeManager.wasteland)
			if (random.nextInt(50) == 0) {
				final int x = chunkX + random.nextInt(16) + 8;
				final int z = chunkZ + random.nextInt(16) + 8;
				final int y = world.getHeightValue(x, z);
				new DeadTreeHuge(random.nextInt(3)).generate(world,
						random, x, y, z);
			}

		if (!ExtrabiomesPlugin.isActive())
			if (biome == BiomeGenBase.taiga
					|| biome == BiomeGenBase.taigaHills)
			{
				if (random.nextInt(130) == 0) {
					final int x = chunkX + random.nextInt(16) + 8;
					final int z = chunkZ + random.nextInt(16) + 8;
					final int y = world.getHeightValue(x, z);
					new GenRedwood(false).generate(world, random, x, y,
							z);
				}

				if (random.nextInt(9) == 0) {
					final int x = chunkX + random.nextInt(16) + 8;
					final int z = chunkZ + random.nextInt(16) + 8;
					final int y = world.getHeightValue(x, z);
					final WorldGenerator tree = random.nextInt(2) == 0 ? new GenFirTree(
							false) : new GenFirTreeHuge(false);
					tree.generate(world, random, x, y, z);
				}
			}
	}

	/**
	 * Processes events that need to happen when the mod is loaded
	 * 
	 * @param mod
	 *            a reference to the Bunyan mod.
	 */
	public static void onLoad(NetworkMod mod) {
		Proxy.preloadTexture("/bunyan/blocks/blocks.png");
		Proxy.preloadTexture("/bunyan/items/items.png");

		// Delegate mod configuration to the Config object
		Config.onLoad();

		RenderManager.initialize(mod);

		woodOreHandler = new WoodOreHandler();
		OreDictionary.registerOreHandler(woodOreHandler);

		PluginManager.plugins.add(ExtrabiomesPlugin.INSTANCE);

		// Our trees use parts of the Extrabiomes API. Here, we set it
		// up so that it works even if that mod is not installed.
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.dirt.blockID));
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.grass.blockID));
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.tilledField.blockID));
		
		KeyPressManager.registerKeys(mod);
	}

	/**
	 * Processes events that needs to happen after all mods have been
	 * loaded
	 */
	public static void onModsLoaded() {
		// Handle post loading configuration tasks
		Config.onModsLoaded();
		if (modEE.isEnabled()) modEE.INSTANCE.activate();
	}

}
