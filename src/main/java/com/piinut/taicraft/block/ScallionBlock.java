package com.piinut.taicraft.block;

import com.piinut.taicraft.register.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

public class ScallionBlock extends CropsBlock {

    public ScallionBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return ModItems.SCALLION.get();
    }

}
