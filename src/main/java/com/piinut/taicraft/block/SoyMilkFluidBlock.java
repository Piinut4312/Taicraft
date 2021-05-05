package com.piinut.taicraft.block;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;

import java.util.function.Supplier;

public class SoyMilkFluidBlock extends FlowingFluidBlock {
    public SoyMilkFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }



}
