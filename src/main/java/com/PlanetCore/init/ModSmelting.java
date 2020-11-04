package com.PlanetCore.init;


import com.PlanetCore.blocks.BlockBase;
import com.PlanetCore.blocks.Crustrock;
import com.PlanetCore.items.ItemBase;
import com.PlanetCore.items.tools.ItemPickaxe;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import static org.apache.commons.io.FilenameUtils.getName;

public class ModSmelting {

	private static final String[] Item1 = {"INGOT", "SWORD", "AXE", "PICKAXE", "SPADE", "HOE",
			"HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS", "ROD"};
	//"STEEL", "BRONZE", "TITANIUM_URANIUM", "TUNGSTEN_URANIUM", "TUNGSTEN_TITANIUM",
	private static final String[] Material = {"ALUMINIUM", "ZINC", "LEAD", "TIN", "SILICON", "COPPER", "IRON", "SILVER",
			"GOLD", "PLATINUM", "TITANIUM", "URANIUM", "TUNGSTEN", "REDSTONE", "TOPAZ", "JADE", "EMERALD", "RUBY", "SAPPHIRE", "DIAMOND", "OLIVINE",
			"WADSLEYITE", "RINGWOODITE", "BRIGMANITE", "MAJORITE", "AMAZONITE", "ONYX"};

	private static final String[] Rock = {"ORE", "CRUSTROCK", "MANTLEROCK", "CORESTONE"};
	private static final String[] Size = {"SMALL", "", "COMPACT"};
	private static final int[] Amount = {3,1,6};


	public static void init()
	{
		String blockname;
		String itemname = new String();
		Block block;
		Item item;
		/**
		 * NORMAL FURNACE RECIPES
		 */
		for (int size = 0; size < 3; size++) {
			for (int material = 0; (material < 27); material++) {

				if (material < 13)
				{
					if (size < 1)
					{
						itemname = "planetcore:" + Material[material].toLowerCase(Locale.ROOT) + "_nugget";
					}
					else itemname = "planetcore:" + Material[material].toLowerCase(Locale.ROOT) + "_ingot";
				}
				else
				{
					if (size < 1)
					{
						itemname = "planetcore:" + Material[material].toLowerCase(Locale.ROOT) + "_shard";
					}
					else
					{
						itemname = "planetcore:" + Material[material].toLowerCase(Locale.ROOT);
						if (Material[material].contains("EMERALD") || Material[material].contains("REDSTONE"))
						{
							itemname = "minecraft:" + Material[material].toLowerCase(Locale.ROOT);
						}
					}
				}

				item = ItemBase.getByNameOrId(itemname).getDefaultInstance().getItem();

				for (int rock = 0; rock < 4; rock++)
				{
					if (rock == 3 && material < 13)
					{
						continue;
					}

					if (size == 1)
					{
						blockname = "planetcore:" + Rock[rock].toLowerCase(Locale.ROOT) + "_" + Material[material].toLowerCase(Locale.ROOT);
						block = BlockBase.getBlockFromName(blockname).getBlockState().getBlock();
						GameRegistry.addSmelting(block, new ItemStack(item, Amount[size]), 0.25F * Amount[size]);
					}
					if (size != 1)
					{
						blockname = "planetcore:" + Rock[rock].toLowerCase(Locale.ROOT) + "_" + Size[size].toLowerCase(Locale.ROOT) + "_" + Material[material].toLowerCase(Locale.ROOT);
						block = BlockBase.getBlockFromName(blockname).getBlockState().getBlock();
						GameRegistry.addSmelting(block, new ItemStack(item, Amount[size]), 0.25F * Amount[size]);
					}
				}
			}
		}

		GameRegistry.addSmelting(ModItems.IRON_COAL, new ItemStack(ModItems.STEEL_INGOT, 1), 1.0F);



		GameRegistry.addSmelting(Blocks.REDSTONE_ORE, new ItemStack(Items.REDSTONE, 5), 1.5F);




		GameRegistry.addSmelting(Items.IRON_HORSE_ARMOR, new ItemStack(ModItems.IRON_NUGGET, 1), 0.0F);

	}

	public static void RemoveSmelting(ItemStack resultStack) {
		ItemStack recipeResult = null;
		Map<ItemStack,ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
		Iterator<ItemStack> iterator = recipes.keySet().iterator();
		resultStack = new ItemStack(Items.IRON_HORSE_ARMOR, 1, 0);
		while(iterator.hasNext()) {
			ItemStack tmpRecipe = iterator.next();
			recipeResult = recipes.get(tmpRecipe);
			if (ItemStack.areItemStacksEqual(resultStack, recipeResult)) {
				iterator.remove();
			}
		}
	}
}
