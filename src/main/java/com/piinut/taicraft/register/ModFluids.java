package com.piinut.taicraft.register;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.fluid.BlackTeaFluid;
import com.piinut.taicraft.fluid.BrineFluid;
import com.piinut.taicraft.fluid.MilkTeaFluid;
import com.piinut.taicraft.fluid.SoyMilkFluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Main.MODID);

    public static final RegistryObject<FlowingFluid> SOY_MILK = FLUIDS.register("soy_milk", SoyMilkFluid.Source::new);
    public static final RegistryObject<FlowingFluid> FLOWING_SOY_MILK = FLUIDS.register("flowing_soy_milk", SoyMilkFluid.Flowing::new);
    public static final RegistryObject<FlowingFluid> BRINE = FLUIDS.register("brine", ()->new ForgeFlowingFluid.Source(BrineFluid.makeProperty()));
    public static final RegistryObject<FlowingFluid> FLOWING_BRINE = FLUIDS.register("flowing_brine",  ()->new ForgeFlowingFluid.Flowing(BrineFluid.makeProperty()));
    public static final RegistryObject<FlowingFluid> BLACK_TEA = FLUIDS.register("black_tea", () -> new ForgeFlowingFluid.Source(BlackTeaFluid.makeProperty()));
    public static final RegistryObject<FlowingFluid> FLOWING_BLACK_TEA = FLUIDS.register("flowing_black_tea", () -> new ForgeFlowingFluid.Flowing(BlackTeaFluid.makeProperty()));
    public static final RegistryObject<FlowingFluid> MILK_TEA = FLUIDS.register("milk_tea", () -> new ForgeFlowingFluid.Source(MilkTeaFluid.makeProperty()));
    public static final RegistryObject<FlowingFluid> FLOWING_MILK_TEA = FLUIDS.register("flowing_milk_tea", () -> new ForgeFlowingFluid.Flowing(MilkTeaFluid.makeProperty()));

    public static void register(){
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
