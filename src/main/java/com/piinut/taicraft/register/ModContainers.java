package com.piinut.taicraft.register;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.container.SteamerContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MODID);

    public static final RegistryObject<ContainerType<SteamerContainer>> STEAMER = CONTAINERS.register("steamer"
            , ()->IForgeContainerType.create((windowId, inv, data)-> new SteamerContainer(windowId, inv)));

    public static void register(){
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
