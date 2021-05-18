package com.piinut.taicraft.register;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.recipe.FluidTankRecipe;
import com.piinut.taicraft.recipe.FluidTankRecipeType;
import com.piinut.taicraft.recipe.SteamerRecipe;
import com.piinut.taicraft.recipe.SteamerRecipeType;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class ModRecipes {

    private static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MODID);

    public static final RegistryObject<IRecipeSerializer<SteamerRecipe>> STEAMER_RECIPE = RECIPES.register("steaming", () -> new SteamerRecipe.Serializer(200));
    public static final RegistryObject<IRecipeSerializer<FluidTankRecipe>> FLUID_TANK_RECIPE = RECIPES.register("tank_cooking", FluidTankRecipe.Serializer::new);

    public static final IRecipeType<SteamerRecipe> STEAMER_RECIPE_TYPE = new SteamerRecipeType();
    public static final IRecipeType<FluidTankRecipe> FLUID_TANK_RECIPE_TYPE = new FluidTankRecipeType();

    public static void register(){
        RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static Map<ResourceLocation, IRecipe<?>> getRecipes(IRecipeType<?> type, RecipeManager manager){
        final Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipes = ObfuscationReflectionHelper.getPrivateValue(RecipeManager.class, manager, "recipes");
        return recipes.get(type);
    }

}
