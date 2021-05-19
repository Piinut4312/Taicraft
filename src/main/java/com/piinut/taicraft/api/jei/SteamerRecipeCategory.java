package com.piinut.taicraft.api.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.piinut.taicraft.Main;
import com.piinut.taicraft.recipe.SteamerRecipe;
import com.piinut.taicraft.register.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class SteamerRecipeCategory implements IRecipeCategory<SteamerRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Main.MODID, "steamer_category");

    private IDrawable background;
    private IDrawable icon;
    private final int regularCookTime;
    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated animatedFlame;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public SteamerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(ModJeiPlugin.VANILLA_GUI_ID, 0, 114, 82, 54);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModItems.STEAMER.get()));
        this.regularCookTime = 200;
        staticFlame = helper.createDrawable(ModJeiPlugin.VANILLA_GUI_ID, 82, 114, 14, 14);
        animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
        this.cachedArrows = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() {
            @Override
            public IDrawableAnimated load(Integer cookTime) {
                return helper.drawableBuilder(ModJeiPlugin.VANILLA_GUI_ID, 82, 128, 24, 17)
                        .buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
            }
        });
    }

    protected IDrawableAnimated getArrow(SteamerRecipe recipe) {
        int cookTime = recipe.getCookingTime();
        if (cookTime <= 0) {
            cookTime = regularCookTime;
        }
        return this.cachedArrows.getUnchecked(cookTime);
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
        itemStackGroup.init(2, false, 60, 18);

        itemStackGroup.set(iIngredients);

    }

    protected void drawExperience(SteamerRecipe recipe, MatrixStack matrixStack, int y) {
        float experience = recipe.getExperience();
        if (experience > 0) {
            TranslationTextComponent experienceString = new TranslationTextComponent("gui.jei.category.smelting.experience", experience);
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(experienceString);
            fontRenderer.draw(matrixStack, experienceString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }

    protected void drawCookTime(SteamerRecipe recipe, MatrixStack matrixStack, int y) {
        int cookTime = recipe.getCookingTime();
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            TranslationTextComponent timeString = new TranslationTextComponent("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            fontRenderer.draw(matrixStack, timeString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }

    @Override
    public void draw(SteamerRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        animatedFlame.draw(matrixStack, 1, 20);

        IDrawableAnimated arrow = getArrow(recipe);
        arrow.draw(matrixStack, 24, 18);

        drawExperience(recipe, matrixStack, 0);
        drawCookTime(recipe, matrixStack, 45);

    }
}
