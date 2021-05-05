package com.piinut.taicraft.register;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.block.*;
import com.piinut.taicraft.fluid.BrineFluid;
import com.piinut.taicraft.fluid.SoyMilkFluid;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    public static final RegistryObject<Block> SCALLION = BLOCKS.register("scallion", () -> new ScallionBlock(
            AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.CROP).instabreak().noCollission().randomTicks()
    ));

    public static final RegistryObject<Block> RICE = BLOCKS.register("rice", () -> new RiceBlock(
            AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.CROP).instabreak().noCollission().randomTicks()
    ));

    public static final RegistryObject<Block> SWEET_POTATOES = BLOCKS.register("sweet_potato", () -> new SweetPotatoesBlock(
            AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.CROP).instabreak().noCollission().randomTicks()
    ));

    public static final RegistryObject<Block> PEANUTS = BLOCKS.register("peanut", () -> new PeanutBlock(
            AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.CROP).instabreak().noCollission().randomTicks()
    ));

    public static final RegistryObject<Block> SOYBEANS = BLOCKS.register("soybean", () -> new SoybeanBlock(
            AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.CROP).instabreak().noCollission().randomTicks()
    ));

    public static final RegistryObject<Block> STEAMER = BLOCKS.register("steamer", () -> new SteamerBlock(
            AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).harvestLevel(0).harvestTool(ToolType.PICKAXE).strength(5.0f)
    ));

    public static final RegistryObject<Block> SOY_MILK = BLOCKS.register("soy_milk", () -> new SoyMilkFluidBlock(
            SoyMilkFluid.Source::new, AbstractBlock.Properties.of(Material.WATER).strength(100.0f).noDrops()
    ));

    public static final RegistryObject<Block> FLUID_TANK = BLOCKS.register("fluid_tank", () -> new FluidTankBlock(
            AbstractBlock.Properties.of(Material.GLASS).strength(1.0f).sound(SoundType.GLASS)
    ));

    public static final RegistryObject<FlowingFluidBlock> BRINE = BLOCKS.register("brine"
            , () -> new BrineFluidBlock(ModFluids.BRINE, AbstractBlock.Properties.of(Material.WATER)
                    .noDrops().strength(100.0f)));

    public static final RegistryObject<Block> BLACK_TEA_TREE = BLOCKS.register("black_tea_tree", () -> new BlackTeaBlock(
            AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.GRASS).strength(0.2f).noCollission()
    ));

    public static final RegistryObject<FlowingFluidBlock> BLACK_TEA = BLOCKS.register("black_tea"
            , () -> new BlackTeaFluidBlock(ModFluids.BLACK_TEA, AbstractBlock.Properties.of(Material.WATER)
                    .noDrops().strength(100.0f)));

    public static final RegistryObject<Block> BEVERAGE_CONTAINER = BLOCKS.register("beverage_container", () -> new BeverageContainerBlock(
            AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.STONE).strength(1.0f)
    ));

    public static final RegistryObject<FlowingFluidBlock> MILK_TEA = BLOCKS.register("milk_tea"
            , () -> new MilkTeaFluidBlock(ModFluids.MILK_TEA, AbstractBlock.Properties.of(Material.WATER)
                    .noDrops().strength(100.0f)));

    public static final RegistryObject<Block> OYSTER_SHELL_BRICK = BLOCKS.register("oyster_shell_brick", () -> new Block(
            AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(4.0f).sound(SoundType.STONE)
    ));

    public static final RegistryObject<Block> POLISHED_OYSTER_SHELL_BRICK = BLOCKS.register("polished_oyster_shell_brick", () -> new Block(
            AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(4.0f).sound(SoundType.STONE)
    ));

    public static final RegistryObject<Block> OYSTER_SHELL_BRICK_STAIRS = BLOCKS.register("oyster_shell_brick_stairs", () -> new StairsBlock(
            ModBlocks.OYSTER_SHELL_BRICK.get().defaultBlockState(), AbstractBlock.Properties.copy(ModBlocks.OYSTER_SHELL_BRICK.get())
    ));

    public static final RegistryObject<Block> OYSTER_SHELL_BRICK_SLAB = BLOCKS.register("oyster_shell_brick_slab", () -> new SlabBlock(
            AbstractBlock.Properties.copy(ModBlocks.OYSTER_SHELL_BRICK.get())
    ));

    public static void register(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
