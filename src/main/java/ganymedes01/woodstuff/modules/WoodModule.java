package ganymedes01.woodstuff.modules;

import ganymedes01.woodstuff.WoodStuff;
import ganymedes01.woodstuff.blocks.BlockWoodBookshelf;
import ganymedes01.woodstuff.blocks.BlockWoodButton;
import ganymedes01.woodstuff.blocks.BlockWoodChest;
import ganymedes01.woodstuff.blocks.BlockWoodCraftingTable;
import ganymedes01.woodstuff.blocks.BlockWoodFence;
import ganymedes01.woodstuff.blocks.BlockWoodFenceGate;
import ganymedes01.woodstuff.blocks.BlockWoodPressurePlate;
import ganymedes01.woodstuff.items.ItemBlockWood;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class WoodModule {

	private static List<WoodModule> modules = new ArrayList<WoodModule>();

	public static void registerModule(WoodModule module) {
		modules.add(module);
	}

	public static void addModulesBlocks() {
		for (WoodModule module : modules)
			if (module.shouldUse())
				module.addBlocks();
	}

	public static List<WoodModule> getModules() {
		return Collections.unmodifiableList(modules);
	}

	private final String modID;
	private boolean iEnabled = true;

	protected WoodModule(String modID) {
		this.modID = modID;
	}

	public abstract void addBlocks();

	public String getName() {
		return modID;
	}

	public boolean shouldUse() {
		return iEnabled && Loader.isModLoaded(modID);
	}

	public void setEnabled(boolean iEnabled) {
		this.iEnabled = iEnabled;
	}

	protected void addWood(Block planks, int meta, boolean addButton, boolean addFence, boolean addGate, boolean addPressurePlate, boolean addBookshelf, boolean addChest, boolean addCraftingTable) {
		if (planks == null || planks == Blocks.air)
			return;

		if (addButton && WoodStuff.button) {
			Block button = new BlockWoodButton(planks, meta);
			registerBlock(button);
			GameRegistry.addShapelessRecipe(new ItemStack(button), new ItemStack(planks, 1, meta));
			OreDictionary.registerOre("buttonWood", button);
			OreDictionary.registerOre("buttonWood", Blocks.wooden_button);
		}
		if (addFence && WoodStuff.fence) {
			Block fence = new BlockWoodFence(planks, meta);
			registerBlock(fence);
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fence, 3), "xyx", "xyx", 'x', new ItemStack(planks, 1, meta), 'y', "stickWood"));
			OreDictionary.registerOre("fenceWood", fence);
			OreDictionary.registerOre("fenceWood", Blocks.fence);
		}
		if (addGate && WoodStuff.gate) {
			Block fenceGate = new BlockWoodFenceGate(planks, meta);
			registerBlock(fenceGate);
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fenceGate), "xyx", "xyx", 'x', "stickWood", 'y', new ItemStack(planks, 1, meta)));
			OreDictionary.registerOre("fencegateWood", fenceGate);
			OreDictionary.registerOre("fencegateWood", Blocks.fence_gate);
		}
		if (addPressurePlate && WoodStuff.pressurePlate) {
			Block pressurePlate = new BlockWoodPressurePlate(planks, meta);
			registerBlock(pressurePlate);
			GameRegistry.addShapedRecipe(new ItemStack(pressurePlate), "xx", 'x', new ItemStack(planks, 1, meta));
			OreDictionary.registerOre("pressureplateWood", pressurePlate);
			OreDictionary.registerOre("pressureplateWood", Blocks.wooden_pressure_plate);
		}
		if (addBookshelf && WoodStuff.bookshelf) {
			Block bookshelf = new BlockWoodBookshelf(planks, meta);
			registerBlock(bookshelf);
			GameRegistry.addShapedRecipe(new ItemStack(bookshelf), "xxx", "yyy", "xxx", 'x', new ItemStack(planks, 1, meta), 'y', new ItemStack(Items.book));
			OreDictionary.registerOre("bookshelfWood", bookshelf);
			OreDictionary.registerOre("bookshelfWood", Blocks.bookshelf);
		}
		if (addChest && WoodStuff.chest) {
			Block chest = new BlockWoodChest(planks, meta);
			registerBlock(chest);
			GameRegistry.addShapedRecipe(new ItemStack(chest), "xxx", "x x", "xxx", 'x', new ItemStack(planks, 1, meta));
			OreDictionary.registerOre("chest", chest);
			OreDictionary.registerOre("chestWood", chest);
			OreDictionary.registerOre("chest", Blocks.chest);
			OreDictionary.registerOre("chestWood", Blocks.chest);
		}
		if (addCraftingTable && WoodStuff.craftingTable) {
			Block craftingTable = new BlockWoodCraftingTable(planks, meta);
			registerBlock(craftingTable);
			GameRegistry.addShapedRecipe(new ItemStack(craftingTable), "xx", "xx", 'x', new ItemStack(planks, 1, meta));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.crafting_table), craftingTable);
			OreDictionary.registerOre("craftingTable", craftingTable);
			OreDictionary.registerOre("craftingTableWood", craftingTable);
			OreDictionary.registerOre("craftingTableWood", Blocks.crafting_table);
		}
	}

	private void registerBlock(Block block) {
		String name = block.getUnlocalizedName().replaceAll("tile.woodstuff.", "").replaceAll(":", ".");
		GameRegistry.registerBlock(block, ItemBlockWood.class, name);
	}
}