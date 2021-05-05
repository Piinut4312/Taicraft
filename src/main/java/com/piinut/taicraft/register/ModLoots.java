package com.piinut.taicraft.register;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.loot.BambooLootModifier;
import com.piinut.taicraft.loot.FishingFishModifier;
import com.piinut.taicraft.loot.GrassLootModifier;
import com.piinut.taicraft.loot.PigLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLoots {

    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOTS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Main.MODID);

    public static final RegistryObject<GlobalLootModifierSerializer<?>> GRASS_LOOT = LOOTS.register("grass", GrassLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<?>> PIG_LOOT = LOOTS.register("pig", PigLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<?>> FISHING_FISH_LOOT = LOOTS.register("fish", FishingFishModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<?>> BAMBOO_LOOT = LOOTS.register("bamboo", BambooLootModifier.Serializer::new);

    public static void register(){
        LOOTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
