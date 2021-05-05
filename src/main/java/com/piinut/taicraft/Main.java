package com.piinut.taicraft;

import com.piinut.taicraft.gui.SteamerScreen;
import com.piinut.taicraft.register.*;
import com.piinut.taicraft.tileentity.render.FluidTankTER;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public class Main
{

    public static final String MODID = "taicraft";

    public static final ItemGroup MOD_GROUP = new ItemGroup("taicraft") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RICE.get());
        }
    };

    public Main() {
        ModRegister.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event){
        RenderTypeLookup.setRenderLayer(ModBlocks.SCALLION.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.RICE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SWEET_POTATOES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PEANUTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SOYBEANS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.FLUID_TANK.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(ModFluids.BRINE.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModFluids.FLOWING_BRINE.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.BLACK_TEA_TREE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModFluids.BLACK_TEA.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModFluids.FLOWING_BLACK_TEA.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.BEVERAGE_CONTAINER.get(), RenderType.cutout());
        ScreenManager.register(ModContainers.STEAMER.get(), SteamerScreen::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.FLUID_TANK.get(), FluidTankTER::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event){
        event.enqueueWork(ComposterHelper::addAll);
    }

}

