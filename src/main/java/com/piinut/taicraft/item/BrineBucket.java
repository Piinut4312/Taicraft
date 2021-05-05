package com.piinut.taicraft.item;

import com.piinut.taicraft.register.ModFluids;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;

import java.util.function.Supplier;

public class BrineBucket extends BucketItem {

    public BrineBucket(Supplier<? extends Fluid> supplier, Properties builder) {
        super(supplier, builder);
    }

    @Override
    public Fluid getFluid() {
        return ModFluids.BRINE.get();
    }

}
