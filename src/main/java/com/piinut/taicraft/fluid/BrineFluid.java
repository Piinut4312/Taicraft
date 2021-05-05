package com.piinut.taicraft.fluid;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.register.ModBlocks;
import com.piinut.taicraft.register.ModFluids;
import com.piinut.taicraft.register.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class BrineFluid extends ForgeFlowingFluid {

    public static final ResourceLocation STILL_TEXTURE =  new ResourceLocation(Main.MODID, "block/brine");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation(Main.MODID, "block/flowing_brine");

    public static Properties makeProperty(){
        return new Properties(ModFluids.BRINE, ModFluids.FLOWING_BRINE, FluidAttributes.builder(STILL_TEXTURE, FLOWING_TEXTURE)
                .sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY)).bucket(ModItems.BRINE_BUCKET).block(ModBlocks.BRINE);
    }

    protected BrineFluid(Properties properties) {
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
