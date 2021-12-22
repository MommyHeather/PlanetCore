package com.PlanetCore.init;

import com.PlanetCore.items.ShinyItem;
import com.PlanetCore.items.ItemAxeX;
import com.PlanetCore.items.ItemPickaxeX;
import com.PlanetCore.items.armor.*;
import com.PlanetCore.items.food.SixEffectFoodItem;
import com.PlanetCore.items.food.EightEffectFoodItem;
import com.PlanetCore.items.food.ThreeEffectFoodItem;
import com.PlanetCore.items.shields.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ModItems {


	private static final List<Item> ITEMS = new ArrayList<>();


	public static final Item HOT_BLAZE_POWDER = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item HOT_MAGMA_CREAM = new Item().setCreativeTab(CreativeTabs.MATERIALS);

	//TOOLS

	//WOODEN//
	public static final ItemSword WOODEN_SWORD = new ItemSword(ToolMaterials.WOODEN);
	public static final ItemSpade WOODEN_SHOVEL = new ItemSpade(ToolMaterials.WOODEN);
	public static final ItemPickaxe WOODEN_PICKAXE = new ItemPickaxeX(ToolMaterials.WOODEN);
	public static final ItemAxe WOODEN_AXE = new ItemAxeX(ToolMaterials.WOODEN);
	public static final ItemHoe WOODEN_HOE = new ItemHoe(ToolMaterials.WOODEN);

	//STONE//
	public static final ItemSword STONE_SWORD = new ItemSword(ToolMaterials.STONE);
	public static final ItemSpade STONE_SHOVEL = new ItemSpade(ToolMaterials.STONE);
	public static final ItemPickaxe STONE_PICKAXE = new ItemPickaxeX(ToolMaterials.STONE);
	public static final ItemAxe STONE_AXE = new ItemAxeX(ToolMaterials.STONE);
	public static final ItemHoe STONE_HOE = new ItemHoe(ToolMaterials.STONE);

	//ALUMINUM//

	public static final ItemSword ALUMINUM_SWORD = new ItemSword(ToolMaterials.ALUMINUM);
	public static final ItemSpade ALUMINUM_SHOVEL = new ItemSpade(ToolMaterials.ALUMINUM);
	public static final ItemPickaxe ALUMINUM_PICKAXE = new ItemPickaxeX(ToolMaterials.ALUMINUM);
	public static final ItemAxe ALUMINUM_AXE = new ItemAxeX(ToolMaterials.ALUMINUM);
	public static final ItemHoe ALUMINUM_HOE = new ItemHoe(ToolMaterials.ALUMINUM);
	public static final ItemSword COMPLETE_ALUMINUM_SWORD = new ItemSword(ToolMaterials.COMPLETE_ALUMINUM);
	public static final ItemSpade COMPLETE_ALUMINUM_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_ALUMINUM);
	public static final ItemPickaxe COMPLETE_ALUMINUM_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_ALUMINUM);
	public static final ItemAxe COMPLETE_ALUMINUM_AXE = new ItemAxeX(ToolMaterials.COMPLETE_ALUMINUM);
	public static final ItemHoe COMPLETE_ALUMINUM_HOE = new ItemHoe(ToolMaterials.COMPLETE_ALUMINUM);



	public static final Item ALUMINUM_HELMET = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_ALUMINUM, 1, EntityEquipmentSlot.HEAD);
	public static final Item ALUMINUM_CHESTPLATE = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_ALUMINUM, 1, EntityEquipmentSlot.CHEST);
	public static final Item ALUMINUM_LEGGINGS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_ALUMINUM, 2, EntityEquipmentSlot.LEGS);
	public static final Item ALUMINUM_BOOTS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_ALUMINUM, 1, EntityEquipmentSlot.FEET);




	//TIN//
	public static final ItemSword TIN_SWORD = new ItemSword(ToolMaterials.TIN);
	public static final ItemSpade TIN_SHOVEL = new ItemSpade(ToolMaterials.TIN);
	public static final ItemPickaxe TIN_PICKAXE = new ItemPickaxeX(ToolMaterials.TIN);
	public static final ItemAxe TIN_AXE = new ItemAxeX(ToolMaterials.TIN);
	public static final ItemHoe TIN_HOE = new ItemHoe(ToolMaterials.TIN);
	public static final ItemSword COMPLETE_TIN_SWORD = new ItemSword(ToolMaterials.COMPLETE_TIN);
	public static final ItemSpade COMPLETE_TIN_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_TIN);
	public static final ItemPickaxe COMPLETE_TIN_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_TIN);
	public static final ItemAxe COMPLETE_TIN_AXE = new ItemAxeX(ToolMaterials.COMPLETE_TIN);
	public static final ItemHoe COMPLETE_TIN_HOE = new ItemHoe(ToolMaterials.COMPLETE_TIN);

	public static final Item TIN_HELMET = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_TIN, 1, EntityEquipmentSlot.HEAD);
	public static final Item TIN_CHESTPLATE = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_TIN, 1, EntityEquipmentSlot.CHEST);
	public static final Item TIN_LEGGINGS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_TIN, 2, EntityEquipmentSlot.LEGS);
	public static final Item TIN_BOOTS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_TIN, 1, EntityEquipmentSlot.FEET);


	//COPPER//
	public static final ItemSword COPPER_SWORD = new ItemSword(ToolMaterials.COPPER);
	public static final ItemSpade COPPER_SHOVEL = new ItemSpade(ToolMaterials.COPPER);
	public static final ItemPickaxe COPPER_PICKAXE = new ItemPickaxeX(ToolMaterials.COPPER);
	public static final ItemAxe COPPER_AXE = new ItemAxeX(ToolMaterials.COPPER);
	public static final ItemHoe COPPER_HOE = new ItemHoe(ToolMaterials.COPPER);
	public static final ItemSword COMPLETE_COPPER_SWORD = new ItemSword(ToolMaterials.COMPLETE_COPPER);
	public static final ItemSpade COMPLETE_COPPER_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_COPPER);
	public static final ItemPickaxe COMPLETE_COPPER_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_COPPER);
	public static final ItemAxe COMPLETE_COPPER_AXE = new ItemAxeX(ToolMaterials.COMPLETE_COPPER);
	public static final ItemHoe COMPLETE_COPPER_HOE = new ItemHoe(ToolMaterials.COMPLETE_COPPER);

	public static final Item COPPER_HELMET = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_COPPER, 1, EntityEquipmentSlot.HEAD);
	public static final Item COPPER_CHESTPLATE = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_COPPER, 1, EntityEquipmentSlot.CHEST);
	public static final Item COPPER_LEGGINGS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_COPPER, 2, EntityEquipmentSlot.LEGS);
	public static final Item COPPER_BOOTS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_COPPER, 1, EntityEquipmentSlot.FEET);

	//IRON//
	public static final ItemSword IRON_SWORD = new ItemSword(ToolMaterials.IRON);
	public static final ItemSpade IRON_SHOVEL = new ItemSpade(ToolMaterials.IRON);
	public static final ItemPickaxe IRON_PICKAXE = new ItemPickaxeX(ToolMaterials.IRON);
	public static final ItemAxe IRON_AXE = new ItemAxeX(ToolMaterials.IRON);
	public static final ItemHoe IRON_HOE = new ItemHoe(ToolMaterials.IRON);
	public static final ItemSword COMPLETE_IRON_SWORD = new ItemSword(ToolMaterials.COMPLETE_IRON);
	public static final ItemSpade COMPLETE_IRON_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_IRON);
	public static final ItemPickaxe COMPLETE_IRON_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_IRON);
	public static final ItemAxe COMPLETE_IRON_AXE = new ItemAxeX(ToolMaterials.COMPLETE_IRON);
	public static final ItemHoe COMPLETE_IRON_HOE = new ItemHoe(ToolMaterials.COMPLETE_IRON);

	public static final Item IRON_HELMET = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_IRON, 1, EntityEquipmentSlot.HEAD);
	public static final Item IRON_CHESTPLATE = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_IRON, 1, EntityEquipmentSlot.CHEST);
	public static final Item IRON_LEGGINGS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_IRON, 2, EntityEquipmentSlot.LEGS);
	public static final Item IRON_BOOTS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_IRON, 1, EntityEquipmentSlot.FEET);

	//STEEL//
	public static final ItemSword STEEL_SWORD = new ItemSword(ToolMaterials.STEEL);
	public static final ItemSpade STEEL_SHOVEL = new ItemSpade(ToolMaterials.STEEL);
	public static final ItemPickaxe STEEL_PICKAXE = new ItemPickaxeX(ToolMaterials.STEEL);
	public static final ItemAxe STEEL_AXE = new ItemAxeX(ToolMaterials.STEEL);
	public static final ItemHoe STEEL_HOE = new ItemHoe(ToolMaterials.STEEL);
	public static final ItemSword COMPLETE_STEEL_SWORD = new ItemSword(ToolMaterials.COMPLETE_STEEL);
	public static final ItemSpade COMPLETE_STEEL_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_STEEL);
	public static final ItemPickaxe COMPLETE_STEEL_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_STEEL);
	public static final ItemAxe COMPLETE_STEEL_AXE = new ItemAxeX(ToolMaterials.COMPLETE_STEEL);
	public static final ItemHoe COMPLETE_STEEL_HOE = new ItemHoe(ToolMaterials.COMPLETE_STEEL);

	public static final Item STEEL_HELMET = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_STEEL, 1, EntityEquipmentSlot.HEAD);
	public static final Item STEEL_CHESTPLATE = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_STEEL, 1, EntityEquipmentSlot.CHEST);
	public static final Item STEEL_LEGGINGS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_STEEL, 2, EntityEquipmentSlot.LEGS);
	public static final Item STEEL_BOOTS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_STEEL, 1, EntityEquipmentSlot.FEET);

	//BRONZE//
	public static final ItemSword BRONZE_SWORD = new ItemSword(ToolMaterials.BRONZE);
	public static final ItemSpade BRONZE_SHOVEL = new ItemSpade(ToolMaterials.BRONZE);
	public static final ItemPickaxe BRONZE_PICKAXE = new ItemPickaxeX(ToolMaterials.BRONZE);
	public static final ItemAxe BRONZE_AXE = new ItemAxeX(ToolMaterials.BRONZE);
	public static final ItemHoe BRONZE_HOE = new ItemHoe(ToolMaterials.BRONZE);
	public static final ItemSword COMPLETE_BRONZE_SWORD = new ItemSword(ToolMaterials.COMPLETE_BRONZE);
	public static final ItemSpade COMPLETE_BRONZE_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_BRONZE);
	public static final ItemPickaxe COMPLETE_BRONZE_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_BRONZE);
	public static final ItemAxe COMPLETE_BRONZE_AXE = new ItemAxeX(ToolMaterials.COMPLETE_BRONZE);
	public static final ItemHoe COMPLETE_BRONZE_HOE = new ItemHoe(ToolMaterials.COMPLETE_BRONZE);

	public static final Item BRONZE_HELMET = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_BRONZE, 1, EntityEquipmentSlot.HEAD);
	public static final Item BRONZE_CHESTPLATE = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_BRONZE, 1, EntityEquipmentSlot.CHEST);
	public static final Item BRONZE_LEGGINGS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_BRONZE, 2, EntityEquipmentSlot.LEGS);
	public static final Item BRONZE_BOOTS = new ArmorBase(ArmorMaterials.ARMOR_MATERIAL_BRONZE, 1, EntityEquipmentSlot.FEET);

	//SILVER//
	public static final ItemSword SILVER_SWORD = new ItemSword(ToolMaterials.SILVER);
	public static final ItemSpade SILVER_SHOVEL = new ItemSpade(ToolMaterials.SILVER);
	public static final ItemPickaxe SILVER_PICKAXE = new ItemPickaxeX(ToolMaterials.SILVER);
	public static final ItemAxe SILVER_AXE = new ItemAxeX(ToolMaterials.SILVER);
	public static final ItemHoe SILVER_HOE = new ItemHoe(ToolMaterials.SILVER);
	public static final ItemSword COMPLETE_SILVER_SWORD = new ItemSword(ToolMaterials.COMPLETE_SILVER);
	public static final ItemSpade COMPLETE_SILVER_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_SILVER);
	public static final ItemPickaxe COMPLETE_SILVER_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_SILVER);
	public static final ItemAxe COMPLETE_SILVER_AXE = new ItemAxeX(ToolMaterials.COMPLETE_SILVER);
	public static final ItemHoe COMPLETE_SILVER_HOE = new ItemHoe(ToolMaterials.COMPLETE_SILVER);

	public static final Item SILVER_HELMET = new SilverHelmet(ArmorMaterials.ARMOR_MATERIAL_SILVER, 1, EntityEquipmentSlot.HEAD);
	public static final Item SILVER_CHESTPLATE = new SilverBody(ArmorMaterials.ARMOR_MATERIAL_SILVER, 1, EntityEquipmentSlot.CHEST);
	public static final Item SILVER_LEGGINGS = new SilverLegs(ArmorMaterials.ARMOR_MATERIAL_SILVER, 2, EntityEquipmentSlot.LEGS);
	public static final Item SILVER_BOOTS = new SilverBoots(ArmorMaterials.ARMOR_MATERIAL_SILVER, 1, EntityEquipmentSlot.FEET);

	//GOLD//
	public static final ItemSword GOLD_SWORD = new ItemSword(ToolMaterials.GOLD);
	public static final ItemSpade GOLD_SHOVEL = new ItemSpade(ToolMaterials.GOLD);
	public static final ItemPickaxe GOLD_PICKAXE = new ItemPickaxeX(ToolMaterials.GOLD);
	public static final ItemAxe GOLD_AXE = new ItemAxeX(ToolMaterials.GOLD);
	public static final ItemHoe GOLD_HOE = new ItemHoe(ToolMaterials.GOLD);
	public static final ItemSword COMPLETE_GOLD_SWORD = new ItemSword(ToolMaterials.COMPLETE_GOLD);
	public static final ItemSpade COMPLETE_GOLD_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_GOLD);
	public static final ItemPickaxe COMPLETE_GOLD_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_GOLD);
	public static final ItemAxe COMPLETE_GOLD_AXE = new ItemAxeX(ToolMaterials.COMPLETE_GOLD);
	public static final ItemHoe COMPLETE_GOLD_HOE = new ItemHoe(ToolMaterials.COMPLETE_GOLD);

	public static final Item GOLD_HELMET = new GoldHelmet(ArmorMaterials.ARMOR_MATERIAL_GOLD, 1, EntityEquipmentSlot.HEAD);
	public static final Item GOLD_CHESTPLATE = new GoldBody(ArmorMaterials.ARMOR_MATERIAL_GOLD, 1, EntityEquipmentSlot.CHEST);
	public static final Item GOLD_LEGGINGS = new GoldLegs(ArmorMaterials.ARMOR_MATERIAL_GOLD, 2, EntityEquipmentSlot.LEGS);
	public static final Item GOLD_BOOTS = new GoldBoots(ArmorMaterials.ARMOR_MATERIAL_GOLD, 1, EntityEquipmentSlot.FEET);

	//PLATINUM//
	public static final ItemSword PLATINUM_SWORD = new ItemSword(ToolMaterials.PLATINUM);
	public static final ItemSpade PLATINUM_SHOVEL = new ItemSpade(ToolMaterials.PLATINUM);
	public static final ItemPickaxe PLATINUM_PICKAXE = new ItemPickaxeX(ToolMaterials.PLATINUM);
	public static final ItemAxe PLATINUM_AXE = new ItemAxeX(ToolMaterials.PLATINUM);
	public static final ItemHoe PLATINUM_HOE = new ItemHoe(ToolMaterials.PLATINUM);
	public static final ItemSword COMPLETE_PLATINUM_SWORD = new ItemSword(ToolMaterials.COMPLETE_PLATINUM);
	public static final ItemSpade COMPLETE_PLATINUM_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_PLATINUM);
	public static final ItemPickaxe COMPLETE_PLATINUM_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_PLATINUM);
	public static final ItemAxe COMPLETE_PLATINUM_AXE = new ItemAxeX(ToolMaterials.COMPLETE_PLATINUM);
	public static final ItemHoe COMPLETE_PLATINUM_HOE = new ItemHoe(ToolMaterials.COMPLETE_PLATINUM);

	public static final Item PLATINUM_HELMET = new PlatinumHelmet(ArmorMaterials.ARMOR_MATERIAL_PLATINUM, 1, EntityEquipmentSlot.HEAD);
	public static final Item PLATINUM_CHESTPLATE = new PlatinumBody(ArmorMaterials.ARMOR_MATERIAL_PLATINUM, 1, EntityEquipmentSlot.CHEST);
	public static final Item PLATINUM_LEGGINGS = new PlatinumLegs(ArmorMaterials.ARMOR_MATERIAL_PLATINUM, 2, EntityEquipmentSlot.LEGS);
	public static final Item PLATINUM_BOOTS = new PlatinumBoots(ArmorMaterials.ARMOR_MATERIAL_PLATINUM, 1, EntityEquipmentSlot.FEET);

	//TITANUM//
	public static final ItemSword TITANUM_SWORD = new ItemSword(ToolMaterials.TITANUM);
	public static final ItemSpade TITANUM_SHOVEL = new ItemSpade(ToolMaterials.TITANUM);
	public static final ItemPickaxe TITANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.TITANUM);
	public static final ItemAxe TITANUM_AXE = new ItemAxeX(ToolMaterials.TITANUM);
	public static final ItemHoe TITANUM_HOE = new ItemHoe(ToolMaterials.TITANUM);
	public static final ItemSword COMPLETE_TITANUM_SWORD = new ItemSword(ToolMaterials.COMPLETE_TITANUM);
	public static final ItemSpade COMPLETE_TITANUM_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_TITANUM);
	public static final ItemPickaxe COMPLETE_TITANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_TITANUM);
	public static final ItemAxe COMPLETE_TITANUM_AXE = new ItemAxeX(ToolMaterials.COMPLETE_TITANUM);
	public static final ItemHoe COMPLETE_TITANUM_HOE = new ItemHoe(ToolMaterials.COMPLETE_TITANUM);

	public static final Item TITANUM_HELMET = new TitanumHelmet(ArmorMaterials.ARMOR_MATERIAL_TITANUM, 1, EntityEquipmentSlot.HEAD);
	public static final Item TITANUM_CHESTPLATE = new TitanumBody(ArmorMaterials.ARMOR_MATERIAL_TITANUM, 1, EntityEquipmentSlot.CHEST);
	public static final Item TITANUM_LEGGINGS = new TitanumHelmet(ArmorMaterials.ARMOR_MATERIAL_TITANUM, 2, EntityEquipmentSlot.LEGS);
	public static final Item TITANUM_BOOTS = new TitanumBoots(ArmorMaterials.ARMOR_MATERIAL_TITANUM, 1, EntityEquipmentSlot.FEET);

	//URANUM//
	public static final ItemSword URANUM_SWORD = new ItemSword(ToolMaterials.URANUM);
	public static final ItemSpade URANUM_SHOVEL = new ItemSpade(ToolMaterials.URANUM);
	public static final ItemPickaxe URANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.URANUM);
	public static final ItemAxe URANUM_AXE = new ItemAxeX(ToolMaterials.URANUM);
	public static final ItemHoe URANUM_HOE = new ItemHoe(ToolMaterials.URANUM);
	public static final ItemSword COMPLETE_URANUM_SWORD = new ItemSword(ToolMaterials.COMPLETE_URANUM);
	public static final ItemSpade COMPLETE_URANUM_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_URANUM);
	public static final ItemPickaxe COMPLETE_URANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_URANUM);
	public static final ItemAxe COMPLETE_URANUM_AXE = new ItemAxeX(ToolMaterials.COMPLETE_URANUM);
	public static final ItemHoe COMPLETE_URANUM_HOE = new ItemHoe(ToolMaterials.COMPLETE_URANUM);

	public static final Item URANUM_HELMET = new UranumHelmet(ArmorMaterials.ARMOR_MATERIAL_URANUM, 1, EntityEquipmentSlot.HEAD);
	public static final Item URANUM_CHESTPLATE = new UranumBody(ArmorMaterials.ARMOR_MATERIAL_URANUM, 1, EntityEquipmentSlot.CHEST);
	public static final Item URANUM_LEGGINGS = new UranumLegs(ArmorMaterials.ARMOR_MATERIAL_URANUM, 2, EntityEquipmentSlot.LEGS);
	public static final Item URANUM_BOOTS = new UranumBoots(ArmorMaterials.ARMOR_MATERIAL_URANUM, 1, EntityEquipmentSlot.FEET);

	//TUNGSTEN//
	public static final ItemSword TUNGSTEN_SWORD = new ItemSword(ToolMaterials.TUNGSTEN);
	public static final ItemSpade TUNGSTEN_SHOVEL = new ItemSpade(ToolMaterials.TUNGSTEN);
	public static final ItemPickaxe TUNGSTEN_PICKAXE = new ItemPickaxeX(ToolMaterials.TUNGSTEN);
	public static final ItemAxe TUNGSTEN_AXE = new ItemAxeX(ToolMaterials.TUNGSTEN);
	public static final ItemHoe TUNGSTEN_HOE = new ItemHoe(ToolMaterials.TUNGSTEN);
	public static final ItemSword COMPLETE_TUNGSTEN_SWORD = new ItemSword(ToolMaterials.COMPLETE_TUNGSTEN);
	public static final ItemSpade COMPLETE_TUNGSTEN_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_TUNGSTEN);
	public static final ItemPickaxe COMPLETE_TUNGSTEN_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_TUNGSTEN);
	public static final ItemAxe COMPLETE_TUNGSTEN_AXE = new ItemAxeX(ToolMaterials.COMPLETE_TUNGSTEN);
	public static final ItemHoe COMPLETE_TUNGSTEN_HOE = new ItemHoe(ToolMaterials.COMPLETE_TUNGSTEN);

	public static final Item TUNGSTEN_HELMET = new TungstenHelmet(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN, 1, EntityEquipmentSlot.HEAD);
	public static final Item TUNGSTEN_CHESTPLATE = new TungstenBody(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN, 1, EntityEquipmentSlot.CHEST);
	public static final Item TUNGSTEN_LEGGINGS = new TungstenLegs(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN, 2, EntityEquipmentSlot.LEGS);
	public static final Item TUNGSTEN_BOOTS = new TungstenBoots(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN, 1, EntityEquipmentSlot.FEET);

	//TITANUM_URANUM//
	public static final ItemSword TITANUM_URANUM_SWORD = new ItemSword(ToolMaterials.TITANUM_URANUM);
	public static final ItemSpade TITANUM_URANUM_SHOVEL = new ItemSpade(ToolMaterials.TITANUM_URANUM);
	public static final ItemPickaxe TITANUM_URANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.TITANUM_URANUM);
	public static final ItemAxe TITANUM_URANUM_AXE = new ItemAxeX(ToolMaterials.TITANUM_URANUM);
	public static final ItemHoe TITANUM_URANUM_HOE = new ItemHoe(ToolMaterials.TITANUM_URANUM);
	public static final ItemSword COMPLETE_TITANUM_URANUM_SWORD = new ItemSword(ToolMaterials.COMPLETE_TITANUM_URANUM);
	public static final ItemSpade COMPLETE_TITANUM_URANUM_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_TITANUM_URANUM);
	public static final ItemPickaxe COMPLETE_TITANUM_URANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_TITANUM_URANUM);
	public static final ItemAxe COMPLETE_TITANUM_URANUM_AXE = new ItemAxeX(ToolMaterials.COMPLETE_TITANUM_URANUM);
	public static final ItemHoe COMPLETE_TITANUM_URANUM_HOE = new ItemHoe(ToolMaterials.COMPLETE_TITANUM_URANUM);

	public static final Item TITANUM_URANUM_HELMET = new Titanum_UranumHelmet(ArmorMaterials.ARMOR_MATERIAL_TITANUM_URANUM, 1, EntityEquipmentSlot.HEAD);
	public static final Item TITANUM_URANUM_CHESTPLATE = new Titanum_UranumBody(ArmorMaterials.ARMOR_MATERIAL_TITANUM_URANUM, 1, EntityEquipmentSlot.CHEST);
	public static final Item TITANUM_URANUM_LEGGINGS = new Titanum_UranumLegs(ArmorMaterials.ARMOR_MATERIAL_TITANUM_URANUM, 2, EntityEquipmentSlot.LEGS);
	public static final Item TITANUM_URANUM_BOOTS = new Titanum_UranumBoots(ArmorMaterials.ARMOR_MATERIAL_TITANUM_URANUM, 1, EntityEquipmentSlot.FEET);

	//TUNGSTEN_URANUM//
	public static final ItemSword TUNGSTEN_URANUM_SWORD = new ItemSword(ToolMaterials.TUNGSTEN_URANUM);
	public static final ItemSpade TUNGSTEN_URANUM_SHOVEL = new ItemSpade(ToolMaterials.TUNGSTEN_URANUM);
	public static final ItemPickaxe TUNGSTEN_URANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.TUNGSTEN_URANUM);
	public static final ItemAxe TUNGSTEN_URANUM_AXE = new ItemAxeX(ToolMaterials.TUNGSTEN_URANUM);
	public static final ItemHoe TUNGSTEN_URANUM_HOE = new ItemHoe(ToolMaterials.TUNGSTEN_URANUM);
	public static final ItemSword COMPLETE_TUNGSTEN_URANUM_SWORD = new ItemSword(ToolMaterials.COMPLETE_TUNGSTEN_URANUM);
	public static final ItemSpade COMPLETE_TUNGSTEN_URANUM_SHOVEL = new ItemSpade(ToolMaterials.COMPLETE_TUNGSTEN_URANUM);
	public static final ItemPickaxe COMPLETE_TUNGSTEN_URANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_TUNGSTEN_URANUM);
	public static final ItemAxe COMPLETE_TUNGSTEN_URANUM_AXE = new ItemAxeX(ToolMaterials.COMPLETE_TUNGSTEN_URANUM);
	public static final ItemHoe COMPLETE_TUNGSTEN_URANUM_HOE = new ItemHoe(ToolMaterials.COMPLETE_TUNGSTEN_URANUM);

	public static final Item TUNGSTEN_URANUM_HELMET = new Tungsten_UranumHelmet(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN_URANUM, 1, EntityEquipmentSlot.HEAD);
	public static final Item TUNGSTEN_URANUM_CHESTPLATE = new Tungsten_UranumBody(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN_URANUM, 1, EntityEquipmentSlot.CHEST);
	public static final Item TUNGSTEN_URANUM_LEGGINGS = new Tungsten_UranumLegs(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN_URANUM, 2, EntityEquipmentSlot.LEGS);
	public static final Item TUNGSTEN_URANUM_BOOTS = new Tungsten_UranumBoots(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN_URANUM, 1, EntityEquipmentSlot.FEET);

	//TUNGSTEN_TITANUM//
	public static final ItemSword TUNGSTEN_TITANUM_SWORD = new ItemSword(ToolMaterials.TUNGSTEN_TITANUM);
	public static final ItemSpade TUNGSTEN_TITANUM_SHOVEL = new ItemSpade(ToolMaterials.TUNGSTEN_TITANUM);
	public static final ItemPickaxe TUNGSTEN_TITANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.TUNGSTEN_TITANUM);
	public static final ItemPickaxe COMPLETE_TUNGSTEN_TITANUM_PICKAXE = new ItemPickaxeX(ToolMaterials.COMPLETE_TUNGSTEN_TITANUM);
	public static final ItemAxe TUNGSTEN_TITANUM_AXE = new ItemAxeX(ToolMaterials.TUNGSTEN_TITANUM);
	public static final ItemHoe TUNGSTEN_TITANUM_HOE = new ItemHoe(ToolMaterials.TUNGSTEN_TITANUM);

	public static final Item TUNGSTEN_TITANUM_HELMET = new Tungsten_TitanumHelmet(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN_TITANUM, 1, EntityEquipmentSlot.HEAD);
	public static final Item TUNGSTEN_TITANUM_CHESTPLATE = new Tungsten_TitanumBody(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN_TITANUM, 1, EntityEquipmentSlot.CHEST);
	public static final Item TUNGSTEN_TITANUM_LEGGINGS = new Tungsten_TitanumLegs(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN_TITANUM, 2, EntityEquipmentSlot.LEGS);
	public static final Item TUNGSTEN_TITANUM_BOOTS = new Tungsten_TitanumBoots(ArmorMaterials.ARMOR_MATERIAL_TUNGSTEN_TITANUM, 1, EntityEquipmentSlot.FEET);

	//EMERALD//
	public static final ItemSword EMERALD_SWORD = new ItemSword(ToolMaterials.EMERALD);
	public static final ItemSpade EMERALD_SHOVEL = new ItemSpade(ToolMaterials.EMERALD);
	public static final ItemPickaxe EMERALD_PICKAXE = new ItemPickaxeX(ToolMaterials.EMERALD);
	public static final ItemAxe EMERALD_AXE = new ItemAxeX(ToolMaterials.EMERALD);
	public static final ItemHoe EMERALD_HOE = new ItemHoe(ToolMaterials.EMERALD);

	public static final Item EMERALD_HELMET = new EmeraldHelmet(ArmorMaterials.ARMOR_MATERIAL_EMERALD, 1, EntityEquipmentSlot.HEAD);
	public static final Item EMERALD_CHESTPLATE = new EmeraldBody(ArmorMaterials.ARMOR_MATERIAL_EMERALD, 1, EntityEquipmentSlot.CHEST);
	public static final Item EMERALD_LEGGINGS = new EmeraldLegs(ArmorMaterials.ARMOR_MATERIAL_EMERALD, 2, EntityEquipmentSlot.LEGS);
	public static final Item EMERALD_BOOTS = new EmeraldBoots(ArmorMaterials.ARMOR_MATERIAL_EMERALD, 1, EntityEquipmentSlot.FEET);

	//RUBY//
	public static final ItemSword RUBY_SWORD = new ItemSword(ToolMaterials.RUBY);
	public static final ItemSpade RUBY_SHOVEL = new ItemSpade(ToolMaterials.RUBY);
	public static final ItemPickaxe RUBY_PICKAXE = new ItemPickaxeX(ToolMaterials.RUBY);
	public static final ItemAxe RUBY_AXE = new ItemAxeX(ToolMaterials.RUBY);
	public static final ItemHoe RUBY_HOE = new ItemHoe(ToolMaterials.RUBY);

	public static final Item RUBY_HELMET = new RubyHelmet(ArmorMaterials.ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.HEAD);
	public static final Item RUBY_CHESTPLATE = new RubyBody(ArmorMaterials.ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.CHEST);
	public static final Item RUBY_LEGGINGS = new RubyLegs(ArmorMaterials.ARMOR_MATERIAL_RUBY, 2, EntityEquipmentSlot.LEGS);
	public static final Item RUBY_BOOTS = new RubyBoots(ArmorMaterials.ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.FEET);

	//SAPPHIRE//
	public static final ItemSword SAPPHIRE_SWORD = new ItemSword(ToolMaterials.SAPPHIRE);
	public static final ItemSpade SAPPHIRE_SHOVEL = new ItemSpade(ToolMaterials.SAPPHIRE);
	public static final ItemPickaxe SAPPHIRE_PICKAXE = new ItemPickaxeX(ToolMaterials.SAPPHIRE);
	public static final ItemAxe SAPPHIRE_AXE = new ItemAxeX(ToolMaterials.SAPPHIRE);
	public static final ItemHoe SAPPHIRE_HOE = new ItemHoe(ToolMaterials.SAPPHIRE);

	public static final Item SAPPHIRE_HELMET = new SapphirHelmet(ArmorMaterials.ARMOR_MATERIAL_SAPPHIRE, 1, EntityEquipmentSlot.HEAD);
	public static final Item SAPPHIRE_CHESTPLATE = new SapphirBody(ArmorMaterials.ARMOR_MATERIAL_SAPPHIRE, 1, EntityEquipmentSlot.CHEST);
	public static final Item SAPPHIRE_LEGGINGS = new SapphirLegs(ArmorMaterials.ARMOR_MATERIAL_SAPPHIRE, 2, EntityEquipmentSlot.LEGS);
	public static final Item SAPPHIRE_BOOTS = new SapphirBoots(ArmorMaterials.ARMOR_MATERIAL_SAPPHIRE, 1, EntityEquipmentSlot.FEET);

	//DIAMOND//
	public static final ItemSword DIAMOND_SWORD = new ItemSword(ToolMaterials.DIAMOND);
	public static final ItemSpade DIAMOND_SHOVEL = new ItemSpade(ToolMaterials.DIAMOND);
	public static final ItemPickaxe DIAMOND_PICKAXE = new ItemPickaxeX(ToolMaterials.DIAMOND);
	public static final ItemAxe DIAMOND_AXE = new ItemAxeX(ToolMaterials.DIAMOND);
	public static final ItemHoe DIAMOND_HOE = new ItemHoe(ToolMaterials.DIAMOND);

	public static final Item DIAMOND_HELMET = new DiamondHelmet(ArmorMaterials.ARMOR_MATERIAL_DIAMOND, 1, EntityEquipmentSlot.HEAD);
	public static final Item DIAMOND_CHESTPLATE = new DiamondBody(ArmorMaterials.ARMOR_MATERIAL_DIAMOND, 1, EntityEquipmentSlot.CHEST);
	public static final Item DIAMOND_LEGGINGS = new DiamondLegs(ArmorMaterials.ARMOR_MATERIAL_DIAMOND, 2, EntityEquipmentSlot.LEGS);
	public static final Item DIAMOND_BOOTS = new DiamondBoots(ArmorMaterials.ARMOR_MATERIAL_DIAMOND, 1, EntityEquipmentSlot.FEET);

	//OLIVINE//
	public static final ItemSword OLIVINE_SWORD = new ItemSword(ToolMaterials.OLIVINE);
	public static final ItemSpade OLIVINE_SHOVEL = new ItemSpade(ToolMaterials.OLIVINE);
	public static final ItemPickaxe OLIVINE_PICKAXE = new ItemPickaxeX(ToolMaterials.OLIVINE);
	public static final ItemAxe OLIVINE_AXE = new ItemAxeX(ToolMaterials.OLIVINE);
	public static final ItemHoe OLIVINE_HOE = new ItemHoe(ToolMaterials.OLIVINE);

	public static final Item OLIVINE_HELMET = new OlivineHelmet(ArmorMaterials.ARMOR_MATERIAL_OLIVINE, 1, EntityEquipmentSlot.HEAD);
	public static final Item OLIVINE_CHESTPLATE = new OlivineBody(ArmorMaterials.ARMOR_MATERIAL_OLIVINE, 1, EntityEquipmentSlot.CHEST);
	public static final Item OLIVINE_LEGGINGS = new OlivineLegs(ArmorMaterials.ARMOR_MATERIAL_OLIVINE, 2, EntityEquipmentSlot.LEGS);
	public static final Item OLIVINE_BOOTS = new OlivineBoots(ArmorMaterials.ARMOR_MATERIAL_OLIVINE, 1, EntityEquipmentSlot.FEET);

	//WADSLEYITE//
	public static final ItemSword WADSLEYITE_SWORD = new ItemSword(ToolMaterials.WADSLEYITE);
	public static final ItemSpade WADSLEYITE_SHOVEL = new ItemSpade(ToolMaterials.WADSLEYITE);
	public static final ItemPickaxe WADSLEYITE_PICKAXE = new ItemPickaxeX(ToolMaterials.WADSLEYITE);
	public static final ItemAxe WADSLEYITE_AXE = new ItemAxeX(ToolMaterials.WADSLEYITE);
	public static final ItemHoe WADSLEYITE_HOE = new ItemHoe(ToolMaterials.WADSLEYITE);

	public static final Item WADSLEYITE_HELMET = new WadsleyiteHelmet(ArmorMaterials.ARMOR_MATERIAL_WADSLEYITE, 1, EntityEquipmentSlot.HEAD);
	public static final Item WADSLEYITE_CHESTPLATE = new WadsleyiteBody(ArmorMaterials.ARMOR_MATERIAL_WADSLEYITE, 1, EntityEquipmentSlot.CHEST);
	public static final Item WADSLEYITE_LEGGINGS = new WadsleyiteLegs(ArmorMaterials.ARMOR_MATERIAL_WADSLEYITE, 2, EntityEquipmentSlot.LEGS);
	public static final Item WADSLEYITE_BOOTS = new WadsleyiteBoots(ArmorMaterials.ARMOR_MATERIAL_WADSLEYITE, 1, EntityEquipmentSlot.FEET);

	//RINGWOODITE//
	public static final ItemSword RINGWOODITE_SWORD = new ItemSword(ToolMaterials.RINGWOODITE);
	public static final ItemSpade RINGWOODITE_SHOVEL = new ItemSpade(ToolMaterials.RINGWOODITE);
	public static final ItemPickaxe RINGWOODITE_PICKAXE = new ItemPickaxeX(ToolMaterials.RINGWOODITE);
	public static final ItemAxe RINGWOODITE_AXE = new ItemAxeX(ToolMaterials.RINGWOODITE);
	public static final ItemHoe RINGWOODITE_HOE = new ItemHoe(ToolMaterials.RINGWOODITE);

	public static final Item RINGWOODITE_HELMET = new RingwooditeHelmet(ArmorMaterials.ARMOR_MATERIAL_RINGWOODITE, 1, EntityEquipmentSlot.HEAD);
	public static final Item RINGWOODITE_CHESTPLATE = new RingwooditeBody(ArmorMaterials.ARMOR_MATERIAL_RINGWOODITE, 1, EntityEquipmentSlot.CHEST);
	public static final Item RINGWOODITE_LEGGINGS = new RingwooditeLegs(ArmorMaterials.ARMOR_MATERIAL_RINGWOODITE, 2, EntityEquipmentSlot.LEGS);
	public static final Item RINGWOODITE_BOOTS = new RingwooditeBoots(ArmorMaterials.ARMOR_MATERIAL_RINGWOODITE, 1, EntityEquipmentSlot.FEET);

	//BRIGMANITE//
	public static final ItemSword BRIGMANITE_SWORD = new ItemSword(ToolMaterials.BRIGMANITE);
	public static final ItemSpade BRIGMANITE_SHOVEL = new ItemSpade(ToolMaterials.BRIGMANITE);
	public static final ItemPickaxe BRIGMANITE_PICKAXE = new ItemPickaxeX(ToolMaterials.BRIGMANITE);
	public static final ItemAxe BRIGMANITE_AXE = new ItemAxeX(ToolMaterials.BRIGMANITE);
	public static final ItemHoe BRIGMANITE_HOE = new ItemHoe(ToolMaterials.BRIGMANITE);

	public static final Item BRIGMANITE_HELMET = new SapphirHelmet(ArmorMaterials.ARMOR_MATERIAL_BRIGMANITE, 1, EntityEquipmentSlot.HEAD);
	public static final Item BRIGMANITE_CHESTPLATE = new SapphirBody(ArmorMaterials.ARMOR_MATERIAL_BRIGMANITE, 1, EntityEquipmentSlot.CHEST);
	public static final Item BRIGMANITE_LEGGINGS = new SapphirLegs(ArmorMaterials.ARMOR_MATERIAL_BRIGMANITE, 2, EntityEquipmentSlot.LEGS);
	public static final Item BRIGMANITE_BOOTS = new SapphirBoots(ArmorMaterials.ARMOR_MATERIAL_BRIGMANITE, 1, EntityEquipmentSlot.FEET);

	//MAJORITE//
	public static final ItemSword MAJORITE_SWORD = new ItemSword(ToolMaterials.MAJORITE);
	public static final ItemSpade MAJORITE_SHOVEL = new ItemSpade(ToolMaterials.MAJORITE);
	public static final ItemPickaxe MAJORITE_PICKAXE = new ItemPickaxeX(ToolMaterials.MAJORITE);
	public static final ItemAxe MAJORITE_AXE = new ItemAxeX(ToolMaterials.MAJORITE);
	public static final ItemHoe MAJORITE_HOE = new ItemHoe(ToolMaterials.MAJORITE);

	public static final Item MAJORITE_HELMET = new SapphirHelmet(ArmorMaterials.ARMOR_MATERIAL_MAJORITE, 1, EntityEquipmentSlot.HEAD);
	public static final Item MAJORITE_CHESTPLATE = new SapphirBody(ArmorMaterials.ARMOR_MATERIAL_MAJORITE, 1, EntityEquipmentSlot.CHEST);
	public static final Item MAJORITE_LEGGINGS = new SapphirLegs(ArmorMaterials.ARMOR_MATERIAL_MAJORITE, 2, EntityEquipmentSlot.LEGS);
	public static final Item MAJORITE_BOOTS = new SapphirBoots(ArmorMaterials.ARMOR_MATERIAL_MAJORITE, 1, EntityEquipmentSlot.FEET);

	//AMAZONITE//
	public static final ItemSword AMAZONITE_SWORD = new ItemSword(ToolMaterials.AMAZONITE);
	public static final ItemSpade AMAZONITE_SHOVEL = new ItemSpade(ToolMaterials.AMAZONITE);
	public static final ItemPickaxe AMAZONITE_PICKAXE = new ItemPickaxeX(ToolMaterials.AMAZONITE);
	public static final ItemAxe AMAZONITE_AXE = new ItemAxeX(ToolMaterials.AMAZONITE);
	public static final ItemHoe AMAZONITE_HOE = new ItemHoe(ToolMaterials.AMAZONITE);

	public static final Item AMAZONITE_HELMET = new AmazoniteHelmet(ArmorMaterials.ARMOR_MATERIAL_AMAZONITE, 1, EntityEquipmentSlot.HEAD);
	public static final Item AMAZONITE_CHESTPLATE = new AmazoniteBody(ArmorMaterials.ARMOR_MATERIAL_AMAZONITE, 1, EntityEquipmentSlot.CHEST);
	public static final Item AMAZONITE_LEGGINGS = new AmazoniteLegs(ArmorMaterials.ARMOR_MATERIAL_AMAZONITE, 2, EntityEquipmentSlot.LEGS);
	public static final Item AMAZONITE_BOOTS = new AmazoniteBoots(ArmorMaterials.ARMOR_MATERIAL_AMAZONITE, 1, EntityEquipmentSlot.FEET);

	//ONYX//
	public static final ItemSword ONYX_SWORD = new ItemSword(ToolMaterials.ONYX);
	public static final ItemSpade ONYX_SHOVEL = new ItemSpade(ToolMaterials.ONYX);
	public static final ItemPickaxe ONYX_PICKAXE = new ItemPickaxeX(ToolMaterials.ONYX);
	public static final ItemAxe ONYX_AXE = new ItemAxeX(ToolMaterials.ONYX);
	public static final ItemHoe ONYX_HOE = new ItemHoe(ToolMaterials.ONYX);

	public static final Item ONYX_HELMET = new OnyxHelmet(ArmorMaterials.ARMOR_MATERIAL_ONYX, 1, EntityEquipmentSlot.HEAD);
	public static final Item ONYX_CHESTPLATE = new OnyxBody(ArmorMaterials.ARMOR_MATERIAL_ONYX, 1, EntityEquipmentSlot.CHEST);
	public static final Item ONYX_LEGGINGS = new OnyxLegs(ArmorMaterials.ARMOR_MATERIAL_ONYX, 2, EntityEquipmentSlot.LEGS);
	public static final Item ONYX_BOOTS = new OnyxBoots(ArmorMaterials.ARMOR_MATERIAL_ONYX, 1, EntityEquipmentSlot.FEET);


	//DEMON//CORE//

	//GEMS//
	public static final Item SULFUR_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item REDSTONE_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item LAPIS_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item EMERALD_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item SAPPHIRE_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item RUBY_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item DIAMOND_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item OLIVINE_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item WADSLEYITE_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item RINGWOODITE_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item BRIGMANITE_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item MAJORITE_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item AMAZONITE_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item ONYX_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);

	public static final Item SAPPHIRE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item EMERALD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item RUBY = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TOPAZ = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item JADE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item DIAMOND = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item OLIVINE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item WADSLEYITE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item RINGWOODITE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item BRIGMANITE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item MAJORITE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item AMAZONITE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item ONYX = new Item().setCreativeTab(CreativeTabs.MATERIALS);

	//ROD//
	public static final Item ALUMINUM_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item COPPER_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TIN_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item IRON_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item STEEL_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item BRONZE_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item SILVER_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item GOLD_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item PLATINUM_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TITANUM_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item URANUM_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TITANUM_URANUM_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_URANUM_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_TITANUM_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item EMERALD_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item SAPPHIRE_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item RUBY_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item DIAMOND_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item OLIVINE_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item WADSLEYITE_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item RINGWOODITE_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item BRIGMANITE_ROD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item MAJORITE_ROD = new ShinyItem().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item AMAZONITE_ROD = new ShinyItem().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item ONYX_ROD = new ShinyItem().setCreativeTab(CreativeTabs.MATERIALS);

	//Ingot//
	public static final Item ALUMINUM_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item COPPER_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TIN_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item IRON_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item STEEL_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item BRONZE_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item SILVER_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item GOLD_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item PLATINUM_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TITANUM_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item URANUM_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TITANUM_URANUM_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_URANUM_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_TITANUM_INGOT = new Item().setCreativeTab(CreativeTabs.MATERIALS);

	//Ingot//
	public static final Item ALUMINUM_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item COPPER_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TIN_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item IRON_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item SILVER_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item GOLD_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item PLATINUM_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TITANUM_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item URANUM_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_ORE = new Item().setCreativeTab(CreativeTabs.MATERIALS);

	//Ingot//
	public static final Item ALUMINUM_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item COPPER_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TIN_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item IRON_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item SILVER_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item GOLD_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item PLATINUM_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TITANUM_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item URANUM_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_SHARD = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item SULFUR = new Item().setCreativeTab(CreativeTabs.MATERIALS);

	public static final Item COAL_BIT = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item ALUMINUM_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item COPPER_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TIN_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item IRON_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item STEEL_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item BRONZE_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item SILVER_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item GOLD_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item PLATINUM_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TITANUM_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item URANUM_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TITANUM_URANUM_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_URANUM_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item TUNGSTEN_TITANUM_NUGGET = new Item().setCreativeTab(CreativeTabs.MATERIALS);

	public static final Item DEMON_STAR = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item DEMON_STAR_CHARGED = new ShinyItem().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item CORE_STAR = new Item().setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item ENHANCING_GEM = new Item().setCreativeTab(CreativeTabs.MATERIALS);

	public static final Item PLATINUM_APPLE = new ThreeEffectFoodItem(10, 3.0F, false,
			new PotionEffect(MobEffects.ABSORPTION, 6000, 4, false, false),
			new PotionEffect(MobEffects.REGENERATION, 400, 2, false, false),
			new PotionEffect(MobEffects.FIRE_RESISTANCE, 3600, 0, false, false));

	public static final Item ENCHANTED_PLATINUM_APPLE = new SixEffectFoodItem(10, 3.0F, false,
			new PotionEffect(MobEffects.ABSORPTION, 6000, 4, false, false),
			new PotionEffect(MobEffects.REGENERATION, 800, 2, false, false),
			new PotionEffect(MobEffects.STRENGTH, 3000, 1, false, false),
			new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 1, false, false),
			new PotionEffect(MobEffects.RESISTANCE, 6000, 1, false, false),
			new PotionEffect(MobEffects.GLOWING, 3000, 0, false, false));

	//SHIELDS
	//public static final Item ALUMINUM_SHIELD = new AluminumShield();
	public static final Item COPPER_SHIELD = new ItemShield();
	public static final Item TIN_SHIELD = new ItemShield();
	public static final Item IRON_SHIELD = new IronShield();
	public static final Item STEEL_SHIELD = new SteelShield();
	public static final Item BRONZE_SHIELD = new ItemShield();
	public static final Item SILVER_SHIELD = new SilverShield();
	public static final Item GOLD_SHIELD = new GoldShield();
	public static final Item PLATINUM_SHIELD = new PlatinumShield();
	public static final Item TITANUM_SHIELD = new ItemShield();
	public static final Item URANUM_SHIELD = new ItemShield();
	public static final Item TUNGSTEN_SHIELD = new ItemShield();
	public static final Item TITANUM_URANUM_SHIELD = new ItemShield();
	public static final Item TUNGSTEN_URANUM_SHIELD = new ItemShield();
	public static final Item TUNGSTEN_TITANUM_SHIELD = new ItemShield();
	public static final Item EMERALD_SHIELD = new EmeraldShield();
	public static final Item SAPPHIRE_SHIELD = new SapphireShield();
	public static final Item RUBY_SHIELD = new RubyShield();
	public static final Item DIAMOND_SHIELD = new DiamondShield();
	public static final Item OLIVINE_SHIELD = new ItemShield();
	public static final Item WADSLEYITE_SHIELD = new ItemShield();
	public static final Item RINGWOODITE_SHIELD = new ItemShield();
	public static final Item BRIGMANITE_SHIELD = new ItemShield();
	public static final Item MAJORITE_SHIELD = new ItemShield();
	public static final Item AMAZONITE_SHIELD = new ItemShield();
	public static final Item ONYX_SHIELD = new OnyxShield();

	public static List<Item> getItems() {
		if (ITEMS.isEmpty()) {
			Field[] fields = ModItems.class.getDeclaredFields();
			for (Field field : fields) {
				try {
					Object obj = field.get(null);
					if (obj instanceof Item) {
						Item item = (Item)obj;
						if (item.getRegistryName() == null) {
							String name = field.getName().toLowerCase(Locale.ROOT);
							item.setRegistryName(name);
							item.setTranslationKey(name);
						}
						ITEMS.add((Item)obj);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return ITEMS;
	}
}
