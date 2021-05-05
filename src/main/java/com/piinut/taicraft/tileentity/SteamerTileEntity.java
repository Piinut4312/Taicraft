package com.piinut.taicraft.tileentity;

import com.piinut.taicraft.container.SteamerContainer;
import com.piinut.taicraft.register.ModRecipes;
import com.piinut.taicraft.register.ModTileEntities;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;



public class SteamerTileEntity extends AbstractFurnaceTileEntity{

    public SteamerTileEntity() {
        super(ModTileEntities.STEAMER.get(), ModRecipes.STEAMER_RECIPE_TYPE);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("screen.taicraft.steamer");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new SteamerContainer(id, playerInventory, this, this.dataAccess);
    }

}
