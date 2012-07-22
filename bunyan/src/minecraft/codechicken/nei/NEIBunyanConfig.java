/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package codechicken.nei;

import java.lang.reflect.Method;

import bunyan.Proxy;
import bunyan.blocks.BunyanBlock;

public class NEIBunyanConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		try {
			final Class neiAPI = Class.forName("codechicken.nei.API");
			final Method hideItem = neiAPI.getMethod("hideItem",
					new Class[] { Integer.TYPE });
			hideItem.invoke(null, new Object[] { Integer
					.valueOf(BunyanBlock.turnableCustomWood.blockID) });
			hideItem.invoke(null, new Object[] { Integer
					.valueOf(BunyanBlock.turnableVanillaWood.blockID) });
			hideItem.invoke(null, new Object[] { Integer
					.valueOf(BunyanBlock.widewoodBarkBottom.blockID) });
			hideItem.invoke(null, new Object[] { Integer
					.valueOf(BunyanBlock.widewoodBarkTop.blockID) });
		} catch (final Exception e) {
			Proxy.getLogger().warning(
					"[EE2] Could not load EE2-NEI plugin");
		}

	}

}
