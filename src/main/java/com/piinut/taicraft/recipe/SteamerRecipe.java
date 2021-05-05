package com.piinut.taicraft.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.piinut.taicraft.register.ModRecipes;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.resources.IFutureReloadListener;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SteamerRecipe extends AbstractCookingRecipe {

    public static final Serializer SERIALIZER = new Serializer(200);

    public SteamerRecipe(Ingredient ingredient, ItemStack result, String group, ResourceLocation resource, float exp, int cookTime) {
        super(ModRecipes.STEAMER_RECIPE_TYPE, resource, group, ingredient, result, exp, cookTime);

    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.STEAMER_RECIPE_TYPE;
    }

    private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SteamerRecipe>{

        private final int defaultCookingTime;

        Serializer(int defaultCookingTime){
            this.defaultCookingTime = defaultCookingTime;
        }

        @Override
        public SteamerRecipe fromJson(ResourceLocation resource, JsonObject json) {
            JsonElement jsonElement = JSONUtils.isArrayNode(json , "ingredient") ? JSONUtils.getAsJsonArray(json, "ingredient") : JSONUtils.getAsJsonObject(json, "ingredient");
            Ingredient ingredient = Ingredient.fromJson(jsonElement);
            if (!json.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack result;
            if (json.get("result").isJsonObject()) result = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
            else {
                String s1 = JSONUtils.getAsString(json, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                result = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
            }
            float f = JSONUtils.getAsFloat(json, "experience", 0.0F);
            int i = JSONUtils.getAsInt(json, "cookingtime", this.defaultCookingTime);
            String s = JSONUtils.getAsString(json, "group", "");
            return new SteamerRecipe(ingredient, result, s, resource, f, i);
        }

        @Override
        public SteamerRecipe fromNetwork(ResourceLocation resource, PacketBuffer packet) {
            Ingredient input = Ingredient.fromNetwork(packet);
            ItemStack output = packet.readItem();
            float exp = packet.readFloat();
            int cookingTime = packet.readVarInt();
            String s = packet.readUtf(32767);
            return new SteamerRecipe(input, output, s, resource, exp, cookingTime);
        }

        @Override
        public void toNetwork(PacketBuffer packet, SteamerRecipe recipe) {
            recipe.ingredient.toNetwork(packet);
            packet.writeItem(recipe.result);
            packet.writeFloat(recipe.experience);
            packet.writeFloat(recipe.cookingTime);
            packet.writeUtf(recipe.group);
        }
    }

}
