package com.piinut.taicraft.item;

import com.piinut.taicraft.register.ModFluids;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class BlackTeaBucket extends BucketItem {

    public BlackTeaBucket(Supplier<? extends Fluid> supplier, Properties builder) {
        super(supplier, builder);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        RayTraceResult raytraceresult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.NONE);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(player, world, itemstack, raytraceresult);
        if (ret != null) return ret;
        if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
            return DrinkHelper.useDrink(world, player, hand);
        }
        return super.use(world, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World p_77654_2_, LivingEntity livingEntity) {
        if (!p_77654_2_.isClientSide) livingEntity.curePotionEffects(new ItemStack(Items.MILK_BUCKET));

        if (livingEntity instanceof PlayerEntity && !((PlayerEntity)livingEntity).abilities.instabuild) {
            stack.shrink(1);
        }

        return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
    }

    @Override
    public Fluid getFluid() {
        return ModFluids.BLACK_TEA.get();
    }


    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 32;
    }

    @Override
    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.DRINK;
    }

}
