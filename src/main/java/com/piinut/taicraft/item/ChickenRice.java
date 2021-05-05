package com.piinut.taicraft.item;

import com.piinut.taicraft.register.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ChickenRice extends Item {
    public ChickenRice(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        if(stack.getItem() == ModItems.CHICKEN_RICE.get() && entity instanceof PlayerEntity){
            ((PlayerEntity)entity).addItem(new ItemStack(Items.BOWL));
        }
        return super.finishUsingItem(stack, world, entity);
    }
}
