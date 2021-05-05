package com.piinut.taicraft.register;

import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;


public class ModFoods {
    public static final Food SCALLION_PANCAKE = createFood(2, 0.3f, false);
    public static final Food PIG_BLOOD_CAKE = createFood(4, 0.4f, false);
    public static final Food BOWL_OF_RICE = createFood(5, 0.6f, false);
    public static final Food STEAMED_BREAD = createFood(5, 0.6f, false);
    public static final Food SUNNY_SIDE_UP_EGG = createFood(2, 0.2f, false);
    public static final Food STEAMED_BREAD_WITH_EGG = createFood(7, 0.9f, false);
    public static final Food RAW_SAUSAGE = createFood(1, 0.1f, true);
    public static final Food GRILLED_SAUSAGE = createFood(3, 0.3f, true);
    public static final Food BOWL_OF_NOODLES = createFood(5, 0.5f, false);
    public static final Food BEEF_NOODLES = createFood(15, 1.5f, false);
    public static final Food OYSTER = createFood(2, 0.1f, false);
    public static final Food SWEET_POTATO = createFood(1, 0.3f, false);
    public static final Food BAKED_SWEET_POTATO = createFood(5, 0.6f, false);
    public static final Food STEAMED_SWEET_POTATO = createFood(5, 0.6f, false);
    public static final Food OYSTER_OMELET = createFood(8, 0.8f, false);
    public static final Food OYSTER_VERMICELLI = createFood(10, 1.0f, false);
    public static final Food STICKY_RICE_SAUSAGE = createFood(5, 0.6f, false);
    public static final Food INTESTINE = createFood(1, 0.1f, Effects.CONFUSION, 3, true);
    public static final Food WASHED_INTESTINE = createFood(1, 0.1f, true);
    public static final Food TAIWANESE_SAUSAGE_WITH_STICKY_RICE = createFood(10, 1.0f, false);
    public static final Food ZONGZI = createFood(15, 1.5f, false);
    public static final Food GUA_BAO = createFood(14, 1.4f, false);
    public static final Food TOFU = createFood(1, 0.1f, false);
    public static final Food STINKY_TOFU = (new Food.Builder().nutrition(2).saturationMod(0.1f).effect(()->new EffectInstance(ModPotions.STINKY.get(), 20*60), 1)).build();
    public static final Food BAO = createFood(7, 0.7f, false);
    public static final Food RAW_BAO = createFood(4, 0.3f, false);
    public static final Food CHICKEN_RICE = createFood(11, 1.0f, false);
    public static final Food STICKY_RICE_IN_BAMBOO = createFood(6, 0.6f, false);
    public static final Food COOKED_INTESTINE = createFood(3, 0.3f, true);
    public static final Food INTESTINE_VERMICELLI = createFood(10, 1.0f, false);
    public static final Food PIG_BLOOD_CAKE_WITH_PEANUT_POWDER = createFood(5, 0.5f, false);
    public static final Food STEAMED_BEEF = createFood(8, 0.8f, true);
    public static final Food STEAMED_PORKCHOP = createFood(8, 0.8f, true);
    public static final Food STEAMED_MUTTON = createFood(6, 0.8f, true);
    public static final Food STEAMED_CHICKEN = createFood(6, 0.6f, true);
    public static final Food STEAMED_RABBIT = createFood(5, 0.6f, true);

    private static Food createFood(int nutrition, float saturation, boolean isMeat){
        if(isMeat){
            return (new Food.Builder().nutrition(nutrition).saturationMod(saturation).meat()).build();
        }
        return (new Food.Builder().nutrition(nutrition).saturationMod(saturation)).build();
    }

    private static Food createFood(int nutrition, float saturation, Effect effect, int sec, boolean isMeat){
        if(isMeat){
            return (new Food.Builder().nutrition(nutrition).saturationMod(saturation).meat().effect(()->new EffectInstance(effect, 20*sec), 1)).build();
        }
        return (new Food.Builder().nutrition(nutrition).saturationMod(saturation).effect(()->new EffectInstance(effect, 20*sec), 1)).build();
    }
}
