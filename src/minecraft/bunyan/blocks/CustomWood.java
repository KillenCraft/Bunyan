
package bunyan.blocks;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class CustomWood extends Block {

	// metadata values
	public static final int	metaRedwood	= 0;
	public static final int	metaFir		= 1;
	public static final int	metaAcacia	= 2;

	public CustomWood(int id) {
		super(id, Material.wood);
		setStepSound(soundWoodFootstep);
		setRequiresSelfNotify();
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(blockID, 1, metaRedwood));
		itemList.add(new ItemStack(blockID, 1, metaFir));
		itemList.add(new ItemStack(blockID, 1, metaAcacia));
	}

	@Override
	protected int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		final int row = 2;
		return row * 16 + metadata;
	}

	@Override
	public float getExplosionResistance(Entity entity) {
		return Block.planks.getExplosionResistance(entity);
	}

	@Override
	public int getFireSpreadSpeed(World world, int x, int y, int z,
			int metadata, int face)
	{
		return Block.planks.getFireSpreadSpeed(world, x, y, z,
				metadata, face);
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z,
			int metadata, int face)
	{
		return Block.planks.getFlammability(world, x, y, z, metadata,
				face);
	}

	@Override
	public float getHardness() {
		return Block.planks.getHardness();
	}

	@Override
	public float getHardness(int meta) {
		return Block.planks.getHardness(meta);
	}

	@Override
	public String getTextureFile() {
		return "/bunyan/blocks/blocks.png";
	}

}
