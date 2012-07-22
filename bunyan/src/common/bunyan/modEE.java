/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan;

import java.lang.reflect.Method;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import bunyan.blocks.CustomWood;
import bunyan.blocks.WideLog;
import bunyan.items.BunyanItem;

public enum modEE {
	INSTANCE;

	public static Method	setEMC;
	public static Method	getEMC;

	public static int getEMC(int id) {
		return getEMC(id, 0);
	}

	public static int getEMC(int id, int metadata) {
		final ItemStack itemstack = new ItemStack(id, 1, metadata);
		return getEMC(itemstack);
	}

	public static int getEMC(ItemStack itemstack) {
		final Object arglist[] = new Object[1];
		arglist[0] = itemstack;
		Object retobj;
		try {
			retobj = getEMC.invoke(null, arglist);
			return ((Integer) retobj).intValue();
		} catch (final Exception e) {
			ModLoader.getLogger().fine("Could not get EMC.");
			return 0;
		}
	}

	public static boolean isEnabled() {
		return ModLoader.isModLoaded("mod_EE");
	}

	public static void setEMC(int id, int emcValue) {
		setEMC(id, 0, emcValue);
	}

	public static void setEMC(int id, int metadata, int emcValue) {
		final Object arglist[] = new Object[3];
		arglist[0] = new Integer(id);
		arglist[1] = new Integer(metadata);
		arglist[2] = new Integer(emcValue);
		try {
			setEMC.invoke(null, arglist);
		} catch (final Exception e) {
			ModLoader.getLogger().fine("Could not set EMC.");
		}
	}

	public static void setEMC(ItemStack itemstack, int emcValue) {
		setEMC(itemstack.itemID, itemstack.getItemDamage(), emcValue);
	}

	static private void setEMCValues() {
		final int emcLog = getEMC(Block.wood.blockID);
		final int emcPlanks = getEMC(Block.planks.blockID);
		final int emcStick = getEMC(new ItemStack(Item.stick));

		setEMC(BunyanBlock.wood.blockID, CustomLog.metaAcacia, emcLog);
		setEMC(BunyanBlock.wood.blockID, CustomLog.metaFir, emcLog);
		setEMC(BunyanBlock.widewood.blockID, WideLog.metaFir, emcLog);
		setEMC(BunyanBlock.widewood.blockID, WideLog.metaOak, emcLog);
		setEMC(BunyanBlock.widewood.blockID, WideLog.metaRedwood,
				emcLog);

		setEMC(BunyanBlock.planks.blockID, CustomWood.metaAcacia,
				emcPlanks);
		setEMC(BunyanBlock.planks.blockID, CustomWood.metaFir,
				emcPlanks);
		setEMC(BunyanBlock.planks.blockID, CustomWood.metaRedwood,
				emcPlanks);

		setEMC(BunyanItem.logTurner.shiftedIndex, emcStick * 5);
	}

	public void activate() {
		Class proxy;
		try {
			proxy = Class.forName("EEProxy");
			final Class partypes[] = { Integer.TYPE, Integer.TYPE,
					Integer.TYPE };
			setEMC = proxy.getMethod("setEMC", partypes);

			final Class partypes2[] = { ItemStack.class };
			getEMC = proxy.getMethod("getEMC", partypes2);
		} catch (final Exception e) {
			ModLoader.getLogger().fine("Could not find EE proxy.");
			return;
		}
		setEMCValues();
	}

}
