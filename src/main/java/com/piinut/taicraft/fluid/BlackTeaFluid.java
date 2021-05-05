package com.piinut.taicraft.fluid;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.register.ModBlocks;
import com.piinut.taicraft.register.ModFluids;
import com.piinut.taicraft.register.ModItems;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class BlackTeaFluid extends ForgeFlowingFluid {

    public static final ResourceLocation STILL_TEXTURE =  new ResourceLocation(Main.MODID, "block/black_tea");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation(Main.MODID, "block/flowing_black_tea");

    public static Properties makeProperty(){
        return new Properties(ModFluids.BLACK_TEA, ModFluids.FLOWING_BLACK_TEA, FluidAttributes.builder(STILL_TEXTURE, FLOWING_TEXTURE)
                .sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY)).bucket(ModItems.BLACK_TEA_BUCKET).block(ModBlocks.BLACK_TEA);
    }

    protected BlackTeaFluid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSource(FluidState state) {
        return state.isSource();
    }

    @Override
    public int getAmount(FluidState state) {
        return state.getAmount();
    }
}
