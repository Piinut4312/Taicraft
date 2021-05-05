package com.piinut.taicraft.register;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.potion.BleedingEffect;
import com.piinut.taicraft.potion.StinkyEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPotions {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MODID);

    public static final RegistryObject<Effect> STINKY = EFFECTS.register("stinky", () -> new StinkyEffect(
            EffectType.NEUTRAL, 0x78724e
    ));

    public static final RegistryObject<Effect> BLEEDING = EFFECTS.register("bleeding", () -> new BleedingEffect(
            EffectType.HARMFUL, 0xe80f0f
    ));

    public static void register(){
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
