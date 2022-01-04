package com.PlanetCore.items.arrows;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class MajoriteArrow extends Item
{
    public MajoriteArrow()
    {
    }

    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {

        EntityMajoriteArrow entityArrow = new EntityMajoriteArrow(worldIn, shooter);
        return entityArrow;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        tooltip.add(net.minecraft.client.resources.I18n.format("Damage: 12"));
        tooltip.add(net.minecraft.client.resources.I18n.format("Can be used by majorite tier bow or higher"));
    }
}
