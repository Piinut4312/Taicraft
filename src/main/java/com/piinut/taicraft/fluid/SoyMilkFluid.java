package com.piinut.taicraft.fluid;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.register.ModBlocks;
import com.piinut.taicraft.register.ModFluids;
import com.piinut.taicraft.register.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class SoyMilkFluid extends ModFluid {

    public static final ResourceLocation STILL_TEXTURE =  new ResourceLocation(Main.MODID, "block/soy_milk");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation(Main.MODID, "block/flowing_soy_milk");

    public Fluid getFlowing() {
        return ModFluids.FLOWING_SOY_MILK.get();
    }

    public Fluid getSource() {
        return ModFluids.SOY_MILK.get();
    }

    public Item getBucket() {
        return ModItems.SOY_MILK_BUCKET.get();
    }

    public int getSlopeFindDistance(IWorldReader p_185698_1_) {
        return 4;
    }

    public BlockState createLegacyBlock(FluidState state) {
        return ModBlocks.SOY_MILK.get().defaultBlockState().setValue(FlowingFluidBlock.LEVEL, getLegacyLevel(state));
    }

    public boolean isSame(Fluid fluid) {
        return fluid == ModFluids.SOY_MILK.get() || fluid == ModFluids.FLOWING_SOY_MILK.get();
    }

    public int getTickDelay(IWorldReader reader) {
        return 8;
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(STILL_TEXTURE, FLOWING_TEXTURE)
                .density(3).viscosity(3).sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY).build(this);
    }

    public static class Flowing extends SoyMilkFluid {
        protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends SoyMilkFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
