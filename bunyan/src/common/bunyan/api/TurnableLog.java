/**
 * Copyright (c) Scott Killen, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package bunyan.api;

import java.util.Random;

import net.minecraft.src.World;

/**
 * TurnableLog allows mod authors to quickly implement turnable logs by
 * extending this class. Requiring implementation of ITurnable ensures
 * that the resulting block will react to the Log Turner.
 */
public abstract class TurnableLog extends DirectionalBlock implements
		ITurnable
{

	private static int renderType = 0;
	
	protected TurnableLog(int id, int index) {
		super(id, index, wood.blockMaterial);
		setStepSound(soundWoodFootstep);
		setHardness(wood.getHardness());
		setResistance(wood.getExplosionResistance(null) * 5.0F);
		setRequiresSelfNotify();
		setBurnProperties(id, blockFireSpreadSpeed[wood.blockID],
				blockFlammability[wood.blockID]);
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public int getRenderType() {
		return renderType;
	}

	public static void setRenderType(int renderType) {
		TurnableLog.renderType = renderType;
	}

	@Override
	public abstract int idDropped(int metadata, Random random,
			int alwaysZero);

	@Override
	public boolean isWood(World world, int x, int y, int z) {
		return true;
	}

}
