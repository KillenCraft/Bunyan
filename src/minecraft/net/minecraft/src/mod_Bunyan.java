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
