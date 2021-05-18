package com.piinut.taicraft.recipe;

import com.piinut.taicraft.Main;
import net.minecraft.item.crafting.IRecipeType;

public class FluidTankRecipeType implements IRecipeType<FluidTankRecipe> {

    @Override
    public String toString() {
        return Main.MODID+"tank_cooking";
    }
}
