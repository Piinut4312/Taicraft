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

public class GrassLootModifier extends LootModifier {

    private final int maxCount;
    private final int minCount;
    private final Item drop;

    public GrassLootModifier(ILootCondition[] conditionsIn, int maxCount, int minCount, Item drop) {
        super(conditionsIn);
        this.maxCount = maxCount;
        this.minCount = minCount;
        this.drop = drop;
    }

    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        Random random = new Random();
        int count = random.nextInt(maxCount-minCount+1)+minCount;
        if(count > 0){
            generatedLoot.add(new ItemStack(drop, count));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<GrassLootModifier>{

        @Override
        public GrassLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            int maxCount = JSONUtils.getAsInt(object, "maxCount");
            int minCount = JSONUtils.getAsInt(object, "minCount");
            Item drop = JSONUtils.getAsItem(object, "drop");
            return new GrassLootModifier(ailootcondition, maxCount, minCount, drop);
        }

        @Override
        public JsonObject write(GrassLootModifier instance) {
            JsonObject object = new JsonObject();
            object.addProperty("maxCount", instance.maxCount);
            object.addProperty("minCount", instance.minCount);
            object.addProperty("drop", instance.drop.toString());
            return object;
        }
    }
}
