/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan;

import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import bunyan.blocks.CustomWood;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class Crafting {

	public static void initialize() {
		
		//Wood to planks
		Proxy.addShapelessRecipe(new ItemStack(BunyanBlock.plank, 4, CustomWood.metaAcacia), new Object[] {
			new ItemStack(BunyanBlock.wood, 1, CustomLog.metaAcacia)});
		Proxy.addShapelessRecipe(new ItemStack(BunyanBlock.plank, 4, CustomWood.metaFir), new Object[] {
			new ItemStack(BunyanBlock.wood, 1, CustomLog.metaFir)});
		Proxy.addShapelessRecipe(new ItemStack(BunyanBlock.plank, 4, CustomWood.metaRedwood), new Object[] {
			new ItemStack(BunyanBlock.wood, 1, CustomLog.metaRedwood)});
		Proxy.addShapelessRecipe(new ItemStack(BunyanBlock.plank, 4, CustomWood.metaFir), new Object[] {
			new ItemStack(BunyanBlock.widewood, 1, CustomLog.metaFir)});
		Proxy.addShapelessRecipe(new ItemStack(BunyanBlock.plank, 4, CustomWood.metaRedwood), new Object[] {
			new ItemStack(BunyanBlock.widewood, 1, CustomLog.metaRedwood)});
		
		// planks to ...
		
		// sticks
		Proxy.addRecipe(new ItemStack(Item.stick, 4),
				new Object[] { "a", "a", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Item.stick, 4),
				new Object[] { "a", "a", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Item.stick, 4),
				new Object[] { "a", "a", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });
		
		// pressure plate
		Proxy.addRecipe(new ItemStack(Block.pressurePlatePlanks),
				new Object[] { "aa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Block.pressurePlatePlanks),
				new Object[] { "aa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Block.pressurePlatePlanks),
				new Object[] { "aa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });
		
		// bowl
		Proxy.addRecipe(new ItemStack(Item.bowlEmpty),
				new Object[] { "a a", " a ", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Item.bowlEmpty),
				new Object[] { "a a", " a ", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Item.bowlEmpty),
				new Object[] { "a a", " a ", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });
		
		// slabs
		Proxy.addRecipe(new ItemStack(Block.stairSingle, 6, 2),
				new Object[] { "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Block.stairSingle, 6, 2),
				new Object[] { "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Block.stairSingle, 6, 2),
				new Object[] { "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });
		
		// workbench
		Proxy.addRecipe(new ItemStack(Block.workbench),
				new Object[] { "aa", "aa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Block.workbench),
				new Object[] { "aa", "aa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Block.workbench),
				new Object[] { "aa", "aa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });
		
		// boat
		Proxy.addRecipe(new ItemStack(Item.boat),
				new Object[] { "a a", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Item.boat),
				new Object[] { "a a", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Item.boat),
				new Object[] { "a a", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });
		
		// door
		Proxy.addRecipe(new ItemStack(Item.doorWood),
				new Object[] { "aa", "aa", "aa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Item.doorWood),
				new Object[] { "aa", "aa", "aa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Item.doorWood),
				new Object[] { "aa", "aa", "aa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });
		
		// trapdoor
		Proxy.addRecipe(new ItemStack(Block.trapdoor, 2),
				new Object[] { "aaa", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Block.trapdoor, 2),
				new Object[] { "aaa", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Block.trapdoor, 2),
				new Object[] { "aaa", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });
		
		// stairs
		Proxy.addRecipe(new ItemStack(Block.stairCompactPlanks, 4),
				new Object[] { "  a", " aa", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Block.stairCompactPlanks, 4),
				new Object[] { "  a", " aa", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Block.stairCompactPlanks, 4),
				new Object[] { "  a", " aa", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });
		
		// chest
		Proxy.addRecipe(new ItemStack(Block.chest),
				new Object[] { "aaa", "a a", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaFir) });
		Proxy.addRecipe(new ItemStack(Block.chest),
				new Object[] { "aaa", "a a", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaAcacia) });
		Proxy.addRecipe(new ItemStack(Block.chest),
				new Object[] { "aaa", "a a", "aaa", Character.valueOf('a'),
			new ItemStack(BunyanBlock.plank, 1, CustomWood.metaRedwood) });

	}

}
