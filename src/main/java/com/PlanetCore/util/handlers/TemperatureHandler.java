package com.PlanetCore.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

import java.util.HashMap;
import java.util.Map;

public class TemperatureHandler {

    private static final Map<Integer,Integer> heatMap = new HashMap<>();

    static {
        heatMap.put(0,70);
        heatMap.put(1,500);
        heatMap.put(2,1000);
        heatMap.put(3,2000);
        heatMap.put(4,3000);
        heatMap.put(5,4000);
    }

    public static double calcTemp(double y) {
        if (y >= 0) return 20;
        if (y >= -512) return y * -0.15625 + 20;
        else if (y >= -768) return (y + 512) * -0.78125 + 100;
        else if (y >= -2500) return (y + 768) * -2.8291 + 300;
        else return 5200;
    }

    public static double getDamage(double temp, int  fireResist) {
        int lowerBound = heatMap.getOrDefault(fireResist, heatMap.get(heatMap.size() - 1));

        if (temp < lowerBound / 1.2) return 0;

        double damage = (temp - lowerBound) / lowerBound + 2;

        if (temp >= lowerBound / 1.2 && temp < lowerBound)
            damage = 2;

        return damage;
    }

    public static void tickTemps(EntityPlayer player) {
        PotionEffect effect = player.getActivePotionEffect(MobEffects.FIRE_RESISTANCE);
        int fireResist = effect == null ? 0 : effect.getAmplifier() + 1;
        double y = player.posY;
        double temp = calcTemp(y);
        double damage = getDamage(temp,fireResist);
        if (damage > 0)
        {
            player.attackEntityFrom(PlayerTickEventHandler.OVERHEAT, (float) damage);
        }
        if (damage > 2) {
            player.setFire(15);
        }
    }

    public static int getLimit(int fireResist) {
        return heatMap.getOrDefault(fireResist, heatMap.get(heatMap.size() - 1));
    }
}
