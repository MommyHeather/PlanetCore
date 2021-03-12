package com.PlanetCore.init;

import java.util.ArrayList;

import java.util.List;


import com.PlanetCore.enchantments.CoolingCore;
import com.PlanetCore.enchantments.RelentlessEnchantment;
import com.PlanetCore.util.Reference;

import net.minecraft.block.material.Material;

import net.minecraft.block.state.IBlockState;

import net.minecraft.enchantment.Enchantment;

import net.minecraft.enchantment.EnchantmentHelper;

import net.minecraft.entity.Entity;

import net.minecraft.entity.EntityLivingBase;


import net.minecraft.potion.Potion;

import net.minecraft.potion.PotionEffect;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.math.MathHelper;

import net.minecraft.world.World;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



@Mod.EventBusSubscriber(modid= Reference.MOD_ID)

public class EnchantmentInit

{

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();



    public static final Enchantment CoolingCore = new CoolingCore();

    public static final Enchantment Relentless = new RelentlessEnchantment();




    @SubscribeEvent
    public static void createMagma(LivingUpdateEvent event)

    {

        EntityLivingBase living = event.getEntityLiving();

        int CoolingCorelevel = EnchantmentHelper.getMaxEnchantmentLevel(CoolingCore, living);


        BlockPos pos = living.getPosition();



        //living.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ModItems.AMAZONITE_BOOTS
        if (CoolingCorelevel > 0)
        {
            World world = event.getEntity().world;

            if (living.onGround)

            {

                float f = (float)Math.min(8, 1);

                BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(0, 0, 0);



                for (BlockPos.MutableBlockPos blockpos1 : BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f)))

                {

                    if (blockpos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double)(f * f))

                    {

                        blockpos.setPos(blockpos1.getX(), blockpos1.getY() + 1, blockpos1.getZ());

                        IBlockState iblockstate = world.getBlockState(blockpos);



                        if (iblockstate.getMaterial() == Material.AIR)

                        {

                            IBlockState iblockstate1 = world.getBlockState(blockpos1);


//-------------------->>>>
                                if (iblockstate1.getMaterial() == Material.LAVA && (iblockstate1.getBlock() instanceof CoreLavaFluid) && world.mayPlace(ModBlocks.CORESTONE, blockpos1, false, EnumFacing.DOWN, (Entity)null))
                            {

                                world.setBlockState(blockpos1, ModBlocks.CORESTONE.getDefaultState());

                                world.scheduleUpdate(blockpos1.toImmutable(), ModBlocks.CORESTONE, MathHelper.getInt(living.getRNG(), 60, 120));

                                living.addPotionEffect(new PotionEffect(Potion.getPotionById(12)));

                            }

                        }

                    }

                }

            }

        }

    }

}