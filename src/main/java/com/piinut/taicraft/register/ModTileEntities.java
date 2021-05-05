package com.piinut.taicraft.register;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.tileentity.BeverageContainerTileEntity;
import com.piinut.taicraft.tileentity.FluidTankTileEntity;
import com.piinut.taicraft.tileentity.SteamerTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MODID);

    public static final RegistryObject<TileEntityType<SteamerTileEntity>> STEAMER = TILES.register("steamer"
            , ()->TileEntityType.Builder.of(SteamerTileEntity::new, ModBlocks.STEAMER.get()).build(null));

    public static final RegistryObject<TileEntityType<FluidTankTileEntity>> FLUID_TANK = TILES.register("fluid_tank"
            , ()->TileEntityType.Builder.of(FluidTankTileEntity::new, ModBlocks.FLUID_TANK.get()).build(null));

    public static final RegistryObject<TileEntityType<BeverageContainerTileEntity>> BEVERAGE_CONTAINER = TILES.register("beverage_container"
            , ()->TileEntityType.Builder.of(BeverageContainerTileEntity::new, ModBlocks.BEVERAGE_CONTAINER.get()).build(null));

    public static void register(){
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
