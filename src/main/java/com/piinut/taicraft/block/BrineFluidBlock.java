package com.piinut.taicraft.block;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;

import java.util.function.Supplier;

public class BrineFluidBlock extends FlowingFluidBlock {
    public BrineFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);
    }
}
