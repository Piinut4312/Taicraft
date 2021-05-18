package com.piinut.taicraft.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.piinut.taicraft.register.ModItems;
import com.piinut.taicraft.register.ModRecipes;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class FluidTankRecipe implements IRecipe<IInventory> {

    private final Ingredient input;
    private final int input_amount;
    private final ItemStack output;
    private final Fluid fluidIn;
    private final Fluid fluidOut;
    private final int cookTime;
    private final boolean heat;
    private final FluidTankMode mode;
    private final SoundEvent sound;
    private final boolean process_later;
    private final ResourceLocation id;

    public FluidTankRecipe(Ingredient input, int input_amount, ItemStack output, Fluid fluidIn, Fluid fluidOut, int cookTime, boolean heat, FluidTankMode mode, SoundEvent sound, boolean process_later, ResourceLocation id) {
        this.input = input;
        this.input_amount = input_amount;
        this.output = output;
        this.fluidIn = fluidIn;
        this.fluidOut = fluidOut;
        this.cookTime = cookTime;
        this.heat = heat;
        this.mode = mode;
        this.sound = sound;
        this.process_later = process_later;
        this.id = id;
    }

    @Override
    public boolean matches(IInventory inv, World world) {
        return this.input.test(inv.getItem(0));
    }

    @Override
    public ItemStack assemble(IInventory inv) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModItems.FLUID_TANK.get());
    }

    public boolean isValid(ItemStackHandler inputs, Fluid fluid){
        if(fluid != this.fluidIn) return false;
        int test = 0;
        for(int i = 0; i < 6; i++){
            if(this.input.test(inputs.getStackInSlot(i))){
                test++;
            }
        }
        if(test >= this.input_amount){
            return true;
        }
        return false;
    }

    public ItemStack getInput(){
        return input.getItems()[0];
    }

    public FluidTankMode getMode() {
        return mode;
    }

    public Fluid getFluidOut() {
        return fluidOut;
    }

    public int getCookTime() {
        return cookTime;
    }

    public SoundEvent getSound() {
        return sound;
    }

    public boolean isProcessedLater() {
        return process_later;
    }

    public boolean requireHeat(){
        return heat;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.FLUID_TANK_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.FLUID_TANK_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FluidTankRecipe>{

        @Override
        public FluidTankRecipe fromJson(ResourceLocation id, JsonObject json) {
            JsonElement jsonElement = JSONUtils.isArrayNode(json, "ingredient")? JSONUtils.getAsJsonArray(json, "ingredient") : JSONUtils.getAsJsonObject(json, "ingredient");
            Ingredient ingredient = Ingredient.fromJson(jsonElement);
            int input_amount = JSONUtils.getAsInt(json, "ingredient_amount");
            if(!json.has("result")) {
                throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            }
            ItemStack result;
            if(json.get("result").isJsonObject()) {
                result = ShapedRecipe.itemFromJson(json.getAsJsonObject("result"));
            }
            else {
                String s1 = JSONUtils.getAsString(json, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                result = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
            }
            Fluid fluidIn = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(JSONUtils.getAsString(json, "fluidIn")));
            Fluid fluidOut = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(JSONUtils.getAsString(json, "fluidOut")));
            int cookTime = JSONUtils.getAsInt(json, "cookingTime", 0);
            boolean heat = JSONUtils.getAsBoolean(json, "heat", false);
            FluidTankMode mode = FluidTankMode.getMode(JSONUtils.getAsString(json, "mode", "REMOVE_ALL"));
            boolean process_later = JSONUtils.getAsBoolean(json, "process_later", false);
            SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(JSONUtils.getAsString(json, "sound")));
            return new FluidTankRecipe(ingredient, input_amount, result, fluidIn, fluidOut, cookTime, heat, mode, sound, process_later, id);
        }

        @Override
        public FluidTankRecipe fromNetwork(ResourceLocation id, PacketBuffer packet) {
            Ingredient ingredient = Ingredient.fromNetwork(packet);
            int input_amount = packet.readVarInt();
            ItemStack output = packet.readItem();
            Fluid fluidIn = ForgeRegistries.FLUIDS.getValue(packet.readResourceLocation());
            Fluid fluidOut = ForgeRegistries.FLUIDS.getValue(packet.readResourceLocation());
            int cookTime = packet.readVarInt();
            boolean heat = packet.readBoolean();
            FluidTankMode mode = packet.readEnum(FluidTankMode.class);
            boolean process_later = packet.readBoolean();
            SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(packet.readResourceLocation());
            return new FluidTankRecipe(ingredient, input_amount, output, fluidIn, fluidOut, cookTime, heat, mode, sound, process_later, id);
        }

        @Override
        public void toNetwork(PacketBuffer packet, FluidTankRecipe recipe) {
            recipe.input.toNetwork(packet);
            packet.writeVarInt(recipe.input_amount);
            packet.writeItem(recipe.output);
            packet.writeResourceLocation(recipe.fluidIn.getRegistryName());
            packet.writeResourceLocation(recipe.fluidOut.getRegistryName());
            packet.writeVarInt(recipe.cookTime);
            packet.writeBoolean(recipe.heat);
            packet.writeEnum(recipe.mode);
            packet.writeBoolean(recipe.process_later);
            packet.writeResourceLocation(recipe.sound.getRegistryName());
        }
    }
}
