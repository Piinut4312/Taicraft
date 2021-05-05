package com.piinut.taicraft.block;

import com.piinut.taicraft.register.ModItems;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

public class SweetPotatoesBlock extends CropsBlock {
    public SweetPotatoesBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return ModItems.SWEET_POTATOES.get();
    }
}
