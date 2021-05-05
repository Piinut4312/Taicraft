package com.piinut.taicraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BlackTeaBottle extends ModDrinkableItem {
    public BlackTeaBottle(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {

        if (!world.isClientSide) livingEntity.curePotionEffects(new ItemStack(Items.MILK_BUCKET));

        if (livingEntity instanceof PlayerEntity && !((PlayerEntity)livingEntity).abilities.instabuild) {
            stack.shrink(1);
        }

        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

}
