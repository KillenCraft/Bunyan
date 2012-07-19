
package bunyan.recipes;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import bunyan.Proxy;
import bunyan.blocks.BunyanBlock;
import bunyan.blocks.CustomLog;
import bunyan.blocks.TurnableCustomLog;
import bunyan.blocks.TurnableVanillaLog;
import bunyan.blocks.WideLog;
import bunyan.items.BunyanItem;

public class CraftingRecipes {

	private static final Character	SYMBOLS[][]	= {
			{ Character.valueOf('#'), Character.valueOf('A') },
			{ Character.valueOf('A'), Character.valueOf('#') } };

	private static void addAxeRecipes() {
		final String rows[][] = { { "##", "A#", "#A", "AA" },
				{ "#s", "As" } };
		final ItemStack output = new ItemStack(Item.axeWood);

		for (final String row1 : rows[0])
			for (final String row2 : rows[1])
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(
							output,
							new Object[] { row1, row2, " s", symbol[0],
									BunyanBlock.planks, symbol[1],
									Block.planks,
									Character.valueOf('s'), Item.stick });
	}

	private static void addBedRecipes() {
		final String rows[] = { "###", "#A#", "A#A", "A##", "##A",
				"#AA", "AA#", "AAA" };
		final ItemStack output = new ItemStack(Item.bed);

		for (final String row : rows)
			for (final Character symbol[] : SYMBOLS)
				Proxy.addRecipe(output, new Object[] { "www", row,
						symbol[0], BunyanBlock.planks, symbol[1],
						Block.planks, Character.valueOf('w'),
						Block.cloth });
	}

	private static void addBoatRecipes() {
		final String rows[][] = {
				{ "# #", "A #", "# A", "A A" },
				{ "###", "#A#", "A#A", "A##", "##A", "#AA", "AA#",
						"AAA" } };
		final ItemStack output = new ItemStack(Item.boat);

		for (final String row1 : rows[0])
			for (final String row2 : rows[1])
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(output, new Object[] { row1, row2,
							symbol[0], BunyanBlock.planks, symbol[1],
							Block.planks });
	}

	private static void addBookshelfRecipes() {
		final String rows[] = { "###", "#A#", "A#A", "A##", "##A",
				"#AA", "AA#", "AAA" };
		final ItemStack output = new ItemStack(Block.bookShelf);

		for (final String row1 : rows)
			for (final String row2 : rows)
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(
							output,
							new Object[] { row1, "bbb", row2,
									symbol[0], BunyanBlock.planks,
									symbol[1], Block.planks,
									Character.valueOf('b'), Item.book });
	}

	private static void addBowlRecipes() {
		final String rows[][] = { { "# #", "A #", "# A", "A A" },
				{ " # ", " A " } };
		final ItemStack output = new ItemStack(Item.bowlEmpty, 4);

		for (final String row1 : rows[0])
			for (final String row2 : rows[1])
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(output, new Object[] { row1, row2,
							symbol[0], BunyanBlock.planks, symbol[1],
							Block.planks });
	}

	private static void addChestRecipes() {
		final String rows[][] = {
				{ "###", "#A#", "A#A", "A##", "##A", "#AA", "AA#",
						"AAA" }, { "# #", "A #", "# A", "A A" } };
		final ItemStack output = new ItemStack(Block.chest);

		for (final String row1 : rows[0])
			for (final String row2 : rows[1])
				for (final String row3 : rows[0])
					for (final Character symbol[] : SYMBOLS)
						Proxy.addRecipe(output, new Object[] { row1,
								row2, row3, symbol[0],
								BunyanBlock.planks, symbol[1],
								Block.planks });
	}

	private static void addDoorRecipes() {
		final String rows[] = { "##", "A#", "#A", "AA" };
		final ItemStack output = new ItemStack(Item.doorWood);

		for (final String row1 : rows)
			for (final String row2 : rows)
				for (final String row3 : rows)
					for (final Character symbol[] : SYMBOLS)
						Proxy.addRecipe(output, new Object[] { row1,
								row2, row3, symbol[0],
								BunyanBlock.planks, symbol[1],
								Block.planks });
	}

	private static void addGateRecipes() {
		final String rows[] = { "s#s", "sAs" };
		final ItemStack output = new ItemStack(Block.fenceGate);

		for (final String row1 : rows)
			for (final String row2 : rows)
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(output, new Object[] { row1, row2,
							symbol[0], BunyanBlock.planks, symbol[1],
							Block.planks, Character.valueOf('s'),
							Item.stick });
	}

	private static void addHoeRecipes() {
		final String rows[] = { "##", "A#", "#A", "AA" };
		final ItemStack output = new ItemStack(Item.hoeWood);

		for (final String row : rows)
			for (final Character symbol[] : SYMBOLS)
				Proxy.addRecipe(output, new Object[] { row, " s", " s",
						symbol[0], BunyanBlock.planks, symbol[1],
						Block.planks, Character.valueOf('s'),
						Item.stick });
	}

	private static void addJukeboxRecipes() {
		final String rows[][] = {
				{ "###", "#A#", "A#A", "A##", "##A", "#AA", "AA#",
						"AAA" }, { "#d#", "Ad#", "#dA", "AdA" } };
		final ItemStack output = new ItemStack(Block.music);

		for (final String row1 : rows[0])
			for (final String row2 : rows[1])
				for (final String row3 : rows[0])
					for (final Character symbol[] : SYMBOLS)
						Proxy.addRecipe(output, new Object[] { row1,
								row2, row3, symbol[0],
								BunyanBlock.planks, symbol[1],
								Block.planks, Character.valueOf('d'),
								Item.diamond });
	}

	private static void addNoteblockRecipes() {
		final String rows[][] = {
				{ "###", "#A#", "A#A", "A##", "##A", "#AA", "AA#",
						"AAA" }, { "#r#", "Ar#", "#rA", "ArA" } };
		final ItemStack output = new ItemStack(Block.jukebox);

		for (final String row1 : rows[0])
			for (final String row2 : rows[1])
				for (final String row3 : rows[0])
					for (final Character symbol[] : SYMBOLS)
						Proxy.addRecipe(output, new Object[] { row1,
								row2, row3, symbol[0],
								BunyanBlock.planks, symbol[1],
								Block.planks, Character.valueOf('r'),
								Item.redstone });
	}

	private static void addPickaxeRecipes() {
		final String rows[] = { "###", "#A#", "A#A", "A##", "##A",
				"#AA", "AA#", "AAA" };
		final ItemStack output = new ItemStack(Item.pickaxeWood);

		for (final String row : rows)
			for (final Character symbol[] : SYMBOLS)
				Proxy.addRecipe(output, new Object[] { row, " s ",
						" s ", symbol[0], BunyanBlock.planks,
						symbol[1], Block.planks,
						Character.valueOf('s'), Item.stick });
	}

	private static void addPistonRecipes() {
		final String rows[] = { "###", "#A#", "A#A", "A##", "##A",
				"#AA", "AA#", "AAA" };
		final ItemStack output = new ItemStack(Block.pistonBase);

		for (final String row : rows)
			for (final Character symbol[] : SYMBOLS)
				Proxy.addRecipe(output, new Object[] { row, "cic",
						"crc", symbol[0], BunyanBlock.planks,
						symbol[1], Block.planks,
						Character.valueOf('c'), Block.cobblestone,
						Character.valueOf('i'), Item.ingotIron,
						Character.valueOf('r'), Item.redstone });
	}

	private static void addPlankRecipes() {
		Proxy.addRecipe(new ItemStack(Block.planks, 4),
				new Object[] {
						"#",
						Character.valueOf('#'),
						new ItemStack(BunyanBlock.widewood, 1,
								WideLog.metaOak) });
		Proxy.addRecipe(new ItemStack(Block.planks, 4), new Object[] {
				"#",
				Character.valueOf('#'),
				new ItemStack(BunyanBlock.turnableVanillaWood, 1,
						TurnableVanillaLog.metaOak) });
		Proxy.addRecipe(new ItemStack(Block.planks, 4, 1),
				new Object[] {
						"#",
						Character.valueOf('#'),
						new ItemStack(BunyanBlock.turnableVanillaWood,
								1, TurnableVanillaLog.metaPine) });
		Proxy.addRecipe(new ItemStack(Block.planks, 4, 2),
				new Object[] {
						"#",
						Character.valueOf('#'),
						new ItemStack(BunyanBlock.turnableVanillaWood,
								1, TurnableVanillaLog.metaBirch) });
		Proxy.addRecipe(new ItemStack(Block.planks, 4, 3),
				new Object[] {
						"#",
						Character.valueOf('#'),
						new ItemStack(BunyanBlock.turnableVanillaWood,
								1, TurnableVanillaLog.metaJungle) });
	}

	private static void addPressurePlateRecipes() {
		final String rows[] = { "##", "A#", "#A", "AA" };
		final ItemStack output = new ItemStack(
				Block.pressurePlatePlanks);

		for (final String row : rows)
			for (final Character symbol[] : SYMBOLS)
				Proxy.addRecipe(output, new Object[] { row, symbol[0],
						BunyanBlock.planks, symbol[1], Block.planks });
	}

	private static void addShovelRecipes() {
		Proxy.addRecipe(new ItemStack(Item.shovelWood),
				new Object[] { "#", "s", "s", Character.valueOf('#'),
						BunyanBlock.planks, Character.valueOf('s'),
						Item.stick });
	}

	private static void addSignRecipes() {
		final String rows[] = { "###", "#A#", "A#A", "A##", "##A",
				"#AA", "AA#", "AAA" };
		final ItemStack output = new ItemStack(Item.sign);

		for (final String row1 : rows)
			for (final String row2 : rows)
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(
							output,
							new Object[] { row1, row2, " s ",
									symbol[0], BunyanBlock.planks,
									symbol[1], Block.planks,
									Character.valueOf('s'), Item.stick });
	}

	private static void addSlabRecipes() {
		final String rows[] = { "###", "#A#", "A#A", "A##", "##A",
				"#AA", "AA#", "AAA" };
		final ItemStack output = new ItemStack(Block.stairSingle, 6, 2);

		for (final String row : rows)
			for (final Character symbol[] : SYMBOLS)
				Proxy.addRecipe(output, new Object[] { row, symbol[0],
						BunyanBlock.planks, symbol[1], Block.planks });
	}

	private static void addSmelting() {
		final ItemStack output = new ItemStack(Item.coal, 1, 1);
		final int blockIDMetas[][] = {
				{ BunyanBlock.wood.blockID, CustomLog.metaAcacia },
				{ BunyanBlock.wood.blockID, CustomLog.metaFir },
				{ BunyanBlock.widewood.blockID, WideLog.metaFir },
				{ BunyanBlock.widewood.blockID, WideLog.metaOak },
				{ BunyanBlock.widewood.blockID, WideLog.metaRedwood },
				{ BunyanBlock.turnableVanillaWood.blockID,
						TurnableVanillaLog.metaOak },
				{ BunyanBlock.turnableVanillaWood.blockID,
						TurnableVanillaLog.metaPine },
				{ BunyanBlock.turnableVanillaWood.blockID,
						TurnableVanillaLog.metaBirch },
				{ BunyanBlock.turnableVanillaWood.blockID,
						TurnableVanillaLog.metaJungle },
				{ BunyanBlock.turnableCustomWood.blockID,
						TurnableCustomLog.metaAcacia },
				{ BunyanBlock.turnableCustomWood.blockID,
						TurnableCustomLog.metaFir } };

		for (final int blockIDMeta[] : blockIDMetas)
			Proxy.addSmelting(blockIDMeta[0], blockIDMeta[1], output);
	}

	private static void addStairRecipes() {
		final String rows[][] = {
				{ "  #", "  A" },
				{ " ##", " A#", " #A", " AA" },
				{ "###", "##A", "#A#", "#AA", "A##", "A#A", "AA#",
						"AAA" } };
		final ItemStack output = new ItemStack(
				Block.stairCompactPlanks, 4);

		for (final String row1 : rows[0])
			for (final String row2 : rows[1])
				for (final String row3 : rows[2])
					for (final Character symbol[] : SYMBOLS)
						Proxy.addRecipe(output, new Object[] { row1,
								row2, row3, symbol[0],
								BunyanBlock.planks, symbol[1],
								Block.planks });
	}

	private static void addStickRecipes() {
		final String rows[] = { "#", "A" };
		final ItemStack output = new ItemStack(Item.stick, 4);

		for (final String row1 : rows)
			for (final String row2 : rows)
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(output, new Object[] { row1, row2,
							symbol[0], BunyanBlock.planks, symbol[1],
							Block.planks });
	}

	private static void addSwordRecipes() {
		final String rows[] = { "#", "A" };
		final ItemStack output = new ItemStack(Item.swordWood);

		for (final String row1 : rows)
			for (final String row2 : rows)
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(
							output,
							new Object[] { row1, row2, "s", symbol[0],
									BunyanBlock.planks, symbol[1],
									Block.planks,
									Character.valueOf('s'), Item.stick });
	}

	private static void addTrapDoorRecipes() {
		final String rows[] = { "###", "#A#", "A#A", "A##", "##A",
				"#AA", "AA#", "AAA" };
		final ItemStack output = new ItemStack(Block.trapdoor, 2);

		for (final String row1 : rows)
			for (final String row2 : rows)
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(output, new Object[] { row1, row2,
							symbol[0], BunyanBlock.planks, symbol[1],
							Block.planks });
	}

	private static void addTurnerRecipes() {
		final ItemStack output = new ItemStack(BunyanItem.logTurner);

		Proxy.addRecipe(output, new Object[] { "ss", " s", "ss",
				Character.valueOf('s'), Item.stick });
	}

	private static void addWorkbenchRecipes() {
		final String rows[] = { "##", "A#", "#A", "AA" };
		final ItemStack output = new ItemStack(Block.workbench);

		for (final String row1 : rows)
			for (final String row2 : rows)
				for (final Character symbol[] : SYMBOLS)
					Proxy.addRecipe(output, new Object[] { row1, row2,
							symbol[0], BunyanBlock.planks, symbol[1],
							Block.planks });
	}

	public static void initialize() {
		addAxeRecipes();
		addBedRecipes();
		addBoatRecipes();
		addBookshelfRecipes();
		addBowlRecipes();
		addChestRecipes();
		addDoorRecipes();
		addGateRecipes();
		addHoeRecipes();
		addJukeboxRecipes();
		addNoteblockRecipes();
		addPickaxeRecipes();
		addPistonRecipes();
		addPlankRecipes();
		addPressurePlateRecipes();
		addShovelRecipes();
		addSignRecipes();
		addSlabRecipes();
		addStairRecipes();
		addStickRecipes();
		addSwordRecipes();
		addTrapDoorRecipes();
		addWorkbenchRecipes();

		addTurnerRecipes();

		addSmelting();
	}
}
