package com.piinut.taicraft.register;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.recipe.SteamerRecipe;
import com.piinut.taicraft.recipe.SteamerRecipeType;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class ModRecipes {

    public static final IRecipeType<SteamerRecipe> STEAMER_RECIPE_TYPE = new SteamerRecipeType();

    private static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MODID);

    public static final RegistryObject<IRecipeSerializer<SteamerRecipe>> STEAMER_RECIPE = RECIPES.register("steaming", ()-> SteamerRecipe.SERIALIZER);

    public static void register(){
        RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


}
