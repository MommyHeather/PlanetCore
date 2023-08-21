package com.PlanetCore.util.handlers;

import com.PlanetCore.items.Drills.IronDrill;
import com.PlanetCore.items.ItemPickaxeX;
import com.PlanetCore.items.armor.ArmorBase;
import com.PlanetCore.items.shields.Shield;
import com.PlanetCore.util.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.util.GeckoLibUtil;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientHandler {

    @SubscribeEvent
    public static void addTooltips(ItemTooltipEvent event) {
        if (event.getEntityPlayer() != null) {
            ItemStack stack = event.getItemStack();
            EntityPlayer player = event.getEntityPlayer();
            if (stack.getItem() instanceof ItemPickaxe || stack.getDisplayName().contains("ickaxe")) {
                float efficiency = event.getItemStack().getDestroySpeed(Blocks.STONE.getDefaultState());
                float relentless = PickaxeRelentlessHandler.getRelentless(stack);
                Item item = stack.getItem();
                ItemTool toolItem = (ItemTool) item;
                IBlockState blockState = Blocks.STONE.getDefaultState();
                String toolClass = blockState.getBlock().getHarvestTool(blockState);
                int tierLevel = toolItem.getHarvestLevel(stack, toolClass, player, blockState);

                if (stack.getItem() instanceof ItemPickaxeX)
                {
                    event.getToolTip().add("Tier level: §9" + tierLevel);
                }
                event.getToolTip().add("Efficiency: §9" + efficiency);
                event.getToolTip().add("Relentless: §9" + relentless);


            }

            if (stack.getItem() instanceof ItemArmor && isItemInArmorSlot(stack, player)) {

                float toughness = (float) player.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue();
                float armor = (float) player.getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue();
                event.getToolTip().add("Total damage reduction:");

                ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack leggings = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                ItemStack shield = player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);

                float helmetArmor = 0;
                float chestArmor = 0;
                float legsArmor = 0;
                float bootsArmor = 0;
                float shieldArmor = 0;

                if (helmet.getItem() instanceof ArmorBase)
                    helmetArmor = ((ArmorBase) helmet.getItem()).extraArmor;
                if (chestplate.getItem() instanceof ArmorBase)
                    chestArmor = ((ArmorBase) chestplate.getItem()).extraArmor;
                if (leggings.getItem() instanceof ArmorBase)
                    legsArmor = ((ArmorBase) leggings.getItem()).extraArmor;
                if (boots.getItem() instanceof ArmorBase)
                    bootsArmor = ((ArmorBase) boots.getItem()).extraArmor;
                if (shield.getItem() instanceof Shield)
                    shieldArmor = ((Shield) shield.getItem()).extraArmor;

                // Calculate the total extra armor value
                float totalExtraArmor = ( helmetArmor + chestArmor + legsArmor + bootsArmor + shieldArmor );

                float modifiedDamage = (float) (((armor + toughness + totalExtraArmor) * 0.03) / (float) (1 + 0.03 * (armor + toughness + totalExtraArmor)));
                event.getToolTip().add("§9"+ String.format("%.2f", modifiedDamage * 100) + "%");
            }
        }
    }

    private static boolean isItemInArmorSlot(ItemStack itemStack, EntityPlayer player) {
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            if (player.getItemStackFromSlot(slot) == itemStack && slot != EntityEquipmentSlot.MAINHAND && slot != EntityEquipmentSlot.OFFHAND) {
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void trackLeftClick(InputUpdateEvent e) {
        boolean attacking = Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown();
        ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
        if (stack.getItem() instanceof IronDrill) {
            IronDrill ironDrill = (IronDrill) stack.getItem();
            AnimationController<?> controller =
                    GeckoLibUtil.getControllerForStack(ironDrill.getFactory(), stack, IronDrill.CTRL_NAME);
            if (attacking) {
                controller.setAnimation(IronDrill.ACTIVE_DRILL);
            } else {
                controller.setAnimation(IronDrill.INACTIVE_DRILL);
            }
        }
    }

    static final int TEX_WIDTH = 48;
    static final int TEX_HEIGHT = 24;

    @SubscribeEvent
    public static void temperatureHud(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft minecraft = Minecraft.getMinecraft();
            EntityPlayer player = minecraft.player;
            if (player != null) {
                double y = player.posY;
                if (y < 0) {
                    int temp = (int) TemperatureHandler.calcTemp(y);
                    PotionEffect effect = player.getActivePotionEffect(MobEffects.FIRE_RESISTANCE);
                    int fireResist = effect == null ? 0 : effect.getAmplifier() + 1;
                    int limit = TemperatureHandler.getLimit(fireResist);

                    Heat heat = Heat.SAFE;

                    if (temp >= limit) {
                        heat = Heat.DANGER;
                    } else if (limit - temp < limit - (limit / 1.2)) {
                        heat = Heat.WARN;
                    }

                    String s = temp + " C";

                    int textWidth = minecraft.fontRenderer.getStringWidth(s);

                    int x1 = event.getResolution().getScaledWidth() / 2 - TEX_WIDTH / 2;
                    int x2 = x1 + TEX_WIDTH / 2 - textWidth / 2;
                    int y1 = 0;


                    minecraft.getTextureManager().bindTexture(heat.tex);

                    Gui.drawModalRectWithCustomSizedTexture(x1, y1, 0, 0, TEX_WIDTH, TEX_HEIGHT, TEX_WIDTH, TEX_HEIGHT);
                    minecraft.fontRenderer.drawString(s, x2, y1 + 11, heat.color, true);
                }
            }
        }
    }

    enum Heat {
        SAFE(0x00ff00, new ResourceLocation(Reference.MOD_ID, "textures/gui/temp_gui.png")),
        WARN(0xffff00, new ResourceLocation(Reference.MOD_ID, "textures/gui/temp_gui_hot.png")),
        DANGER(0xff0000, new ResourceLocation(Reference.MOD_ID, "textures/gui/temp_gui_superheated.png"));
        final int color;
        final ResourceLocation tex;

        Heat(int color, ResourceLocation tex) {
            this.color = color;
            this.tex = tex;
        }
    }

    @SubscribeEvent
    public static void playSound(PlaySoundEvent e) {
        EntityPlayer player = Minecraft.getMinecraft().player;

        if (player != null) {
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() instanceof IronDrill) {
                ISound sound = e.getSound();
                ResourceLocation rl = sound.getSoundLocation();
                if (rl.getPath().endsWith("hit")) {
                    e.setResultSound(null);
                }
            }
        }
    }

    @SubscribeEvent
    public static void overlay(RenderBlockOverlayEvent e) {
        RenderBlockOverlayEvent.OverlayType overlayType = e.getOverlayType();
        if (overlayType == RenderBlockOverlayEvent.OverlayType.FIRE) {
            EntityPlayer player = e.getPlayer();
            if (player.isBurning()) {
                PotionEffect effect = player.getActivePotionEffect(MobEffects.FIRE_RESISTANCE);
                int fireResist = effect == null ? 0 : effect.getAmplifier() + 1;
                if (fireResist == 0) return;
                double y = player.posY;
                double temp = TemperatureHandler.calcTemp(y);
                double damage = TemperatureHandler.getDamage(temp, fireResist);
                if (damage <= 0) {
                    e.setCanceled(true);
                }
            }
        }
    }
}
