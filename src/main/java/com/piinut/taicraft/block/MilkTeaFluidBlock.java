package com.piinut.taicraft.block;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;

import java.util.function.Supplier;

public class MilkTeaFluidBlock extends FlowingFluidBlock {

    public MilkTeaFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }
}
