package bunyan.recipes;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import bunyan.Proxy;
import bunyan.blocks.BunyanBlock;

public class RecipesCrafting
{
    /**
     * Adds the crafting recipes to the CraftingManager.
     */
    public static void addRecipes()
    {
        Proxy.addRecipe(new ItemStack(Block.chest), new Object[] {"###", "# #", "###", '#', BunyanBlock.plank});
        Proxy.addRecipe(new ItemStack(Block.workbench), new Object[] {"##", "##", '#', BunyanBlock.plank});
     }
}
