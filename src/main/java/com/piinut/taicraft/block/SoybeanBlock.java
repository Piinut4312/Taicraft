package com.piinut.taicraft.block;

import com.piinut.taicraft.register.ModItems;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

public class SoybeanBlock extends CropsBlock {

    public SoybeanBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return ModItems.SOYBEAN_SEED.get();
    }
}
