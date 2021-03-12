package com.PlanetCore.enchantments;

import com.PlanetCore.init.EnchantmentInit;
import com.PlanetCore.util.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;


public class Efficiency extends Enchantment

{

    public Efficiency()

    {

        super(Rarity.COMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});

        this.setName("efficiency");

        this.setRegistryName(new ResourceLocation(Reference.MOD_ID + ":efficiency"));



        EnchantmentInit.ENCHANTMENTS.add(this);

    }



    @Override

    public int getMinEnchantability(int enchantmentLevel)

    {

        return 20 * enchantmentLevel;

    }



    @Override

    public int getMaxEnchantability(int enchantmentLevel)

    {

        return this.getMinEnchantability(enchantmentLevel) + 10;

    }



    @Override

    public int getMaxLevel()

    {

        return 10;

    }





}