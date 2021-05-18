package com.piinut.taicraft.api.jei;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.recipe.SteamerRecipe;
import com.piinut.taicraft.register.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class SteamerRecipeCategory implements IRecipeCategory<SteamerRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Main.MODID, "steamer_category");

    private IDrawable background;
    private IDrawable icon;

    public SteamerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(new ResourceLocation("jei", "textures/gui/gui_vanilla.png"), 0, 114, 82, 54);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModItems.STEAMER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends SteamerRecipe> getRecipeClass() {
        return SteamerRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category."+Main.MODID+".steamer_recipe").getString();
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(SteamerRecipe steamerRecipe, IIngredients iIngredients) {
        iIngredients.setInputIngredients(steamerRecipe.getIngredients());
        iIngredients.setOutput(VanillaTypes.ITEM, steamerRecipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, SteamerRecipe steamerRecipe, IIngredients iIngredients) {

        IGuiItemStackGroup itemStackGroup = iRecipeLayout.getItemStacks();

        itemStackGroup.init(0, true, 0, 0);
        //itemStackGroup.init(1, true, 0, 36);
        itemStackGroup.init(2, false, 60, 18);

        itemStackGroup.set(iIngredients);

    }
}
