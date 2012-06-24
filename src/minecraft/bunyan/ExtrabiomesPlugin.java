package bunyan;

import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import extrabiomes.api.IPlugin;
import extrabiomes.api.TerrainGenManager;

public enum ExtrabiomesPlugin implements IPlugin {
	INSTANCE;

	private static boolean inUse = false;
	
	public static boolean isInUse() {
		return inUse;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void inject() {
		inUse = true; // Extrabiomes has activated the plugin
		
		useBunyanWoodInExtrabiomes();
		
	}

	private static void useBunyanWoodInExtrabiomes() {

		// Uses the ExtraBiomes XL API
		TerrainGenManager.blockRedwoodWood = BunyanBlock.wood;
		TerrainGenManager.metaRedwoodWood = CustomLog.metaRedwood;

		TerrainGenManager.blockFirWood = BunyanBlock.wood;
		TerrainGenManager.metaFirWood = CustomLog.metaFir;

		TerrainGenManager.blockAcaciaWood = BunyanBlock.wood;
		TerrainGenManager.metaAcaciaLeaves = CustomLog.metaAcacia;
	}

	@Override
	public String getName() {
		return Bunyan.getName();
	}

}
