package com.piinut.taicraft.register;

public class ModRegister {

    public static void init() {
        ModPotions.register();
        ModBlocks.register();
        ModItems.register();
        ModFluids.register();
        ModRecipes.register();
        ModTileEntities.register();
        ModContainers.register();
        ModLoots.register();
    }

}
