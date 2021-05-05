package com.piinut.taicraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.World;

public class MilkTeaBottle extends ModDrinkableItem{

    public MilkTeaBottle(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {

        if (!world.isClientSide){
            livingEntity.removeEffect(Effects.POISON);
            livingEntity.removeEffect(Effects.MOVEMENT_SLOWDOWN);
            livingEntity.removeEffect(Effects.HUNGER);
            livingEntity.removeEffect(Effects.WEAKNESS);
            livingEntity.removeEffect(Effects.CONFUSION);
        }

        if (livingEntity instanceof PlayerEntity && !((PlayerEntity)livingEntity).abilities.instabuild) {
            stack.shrink(1);
        }

        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

}
