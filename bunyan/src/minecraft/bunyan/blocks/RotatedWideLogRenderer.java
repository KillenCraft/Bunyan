
package bunyan.blocks;

import net.minecraft.src.Block;
import bunyan.api.DirectionalBlock;

public class RotatedWideLogRenderer extends RotatedLogRenderer {

	public boolean renderRotatedLogBarkBottom(Block block, int x,
			int y, int z)
	{
		final int metadata = blockAccess.getBlockMetadata(x, y, z);
		facing = DirectionalBlock.getFacingFromMetadata(metadata);

		switch (facing) {
			case NORTH:
				uvRotateNorth = 1;
				uvRotateSouth = 1;
				break;
			case SOUTH:
				uvRotateNorth = 2;
				uvRotateSouth = 2;
				uvRotateTop = 3;
				uvRotateBottom = 3;
				break;
			case EAST:
				uvRotateEast = 2;
				uvRotateWest = 2;
				uvRotateTop = 1;
				uvRotateBottom = 2;
				break;
			case WEST:
				uvRotateEast = 1;
				uvRotateWest = 1;
				uvRotateTop = 2;
				uvRotateBottom = 1;
				break;
			default:
				return false;
		}

		renderStandardBlock(block, x, y, z);
		resetRenderer();

		return true;
	}

	public boolean renderRotatedLogBarkTop(Block block, int x, int y,
			int z)
	{
		final int metadata = blockAccess.getBlockMetadata(x, y, z);
		facing = DirectionalBlock.getFacingFromMetadata(metadata);

		switch (facing) {
			case NORTH:
				uvRotateNorth = 2;
				uvRotateSouth = 2;
				break;
			case SOUTH:
				uvRotateNorth = 1;
				uvRotateSouth = 1;
				uvRotateEast = 2;
				uvRotateTop = 3;
				uvRotateBottom = 3;
				break;
			case EAST:
				uvRotateEast = 1;
				uvRotateWest = 1;
				uvRotateTop = 1;
				uvRotateBottom = 2;
				break;
			case WEST:
				uvRotateEast = 2;
				uvRotateWest = 2;
				uvRotateTop = 2;
				uvRotateBottom = 1;
				break;
			default:
				return false;
		}

		renderStandardBlock(block, x, y, z);
		resetRenderer();

		return true;
	}

	private void resetRenderer() {
		uvRotateNorth = 0;
		uvRotateSouth = 0;
		uvRotateEast = 0;
		uvRotateWest = 0;
		uvRotateTop = 0;
		uvRotateBottom = 0;
	}

}
