package com.piinut.taicraft.block;

import com.piinut.taicraft.register.ModItems;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

public class PeanutBlock extends CropsBlock {
    public PeanutBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return ModItems.PEANUT.get();
    }
}
