package com.piinut.taicraft.recipe;

import com.piinut.taicraft.Main;
import net.minecraft.item.crafting.IRecipeType;

public class SteamerRecipeType implements IRecipeType<SteamerRecipe> {

    @Override
    public String toString () {

        return Main.MODID+"steaming";
    }


}
