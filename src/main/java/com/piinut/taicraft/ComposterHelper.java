package com.piinut.taicraft;

import com.piinut.taicraft.register.ModItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Item;

public class ComposterHelper {

    private static void add(float chance, Item item){
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    public static void addAll(){
        add(0.65f, ModItems.RICE.get());
        add(0.3f, ModItems.RICE_SEED.get());
        add(0.65f, ModItems.SCALLION.get());
        add(0.65f, ModItems.SWEET_POTATOES.get());
        add(0.65f, ModItems.PEANUT.get());
        add(0.65f, ModItems.SOYBEAN.get());
        add(0.3f, ModItems.SOYBEAN_SEED.get());
        add(0.3f, ModItems.BLACK_TEA_SAPLING.get());
        add(0.3f, ModItems.BLACK_TEA_LEAVES.get());
        add(0.3f, ModItems.BAMBOO_LEAF.get());
    }

}
