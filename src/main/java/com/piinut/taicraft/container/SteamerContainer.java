package com.piinut.taicraft.container;

import com.piinut.taicraft.register.ModContainers;
import com.piinut.taicraft.register.ModRecipes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;


public class SteamerContainer extends AbstractFurnaceContainer {

    public SteamerContainer(int id, PlayerInventory inv) {
        super(ModContainers.STEAMER.get(), ModRecipes.STEAMER_RECIPE_TYPE, RecipeBookCategory.FURNACE, id, inv);
    }

    public SteamerContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray data) {
        super(ModContainers.STEAMER.get(), ModRecipes.STEAMER_RECIPE_TYPE, RecipeBookCategory.FURNACE, id, playerInventory, inventory, data);
    }

}
