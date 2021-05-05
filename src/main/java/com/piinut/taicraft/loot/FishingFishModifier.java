package com.piinut.taicraft.loot;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import java.util.List;
import java.util.Random;

public class FishingFishModifier extends LootModifier {

    private final Item drop;
    private final float chance;

    public FishingFishModifier(ILootCondition[] conditionsIn, Item drop, float chance) {
        super(conditionsIn);
        this.drop = drop;
        this.chance = chance;
    }


    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        Random random = new Random();
        if(random.nextFloat() < chance){
            generatedLoot.clear();
            generatedLoot.add(new ItemStack(drop));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<FishingFishModifier> {

        @Override
        public FishingFishModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            float chance = JSONUtils.getAsFloat(object, "chance");
            Item drop = JSONUtils.getAsItem(object, "drop");
            return new FishingFishModifier(ailootcondition, drop, chance);
        }

        @Override
        public JsonObject write(FishingFishModifier instance) {
            JsonObject object = new JsonObject();
            object.addProperty("chance", instance.chance);
            object.addProperty("drop", instance.drop.toString());
            return object;
        }
    }
}
