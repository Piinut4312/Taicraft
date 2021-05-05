package com.piinut.taicraft.block;

import com.piinut.taicraft.register.ModItems;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;

public class RiceBlock extends CropsBlock {
    public RiceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return ModItems.RICE_SEED.get();
    }
}
