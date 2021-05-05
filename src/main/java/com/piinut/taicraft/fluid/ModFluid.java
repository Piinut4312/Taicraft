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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public abstract class ModFluid extends FlowingFluid {

    public abstract Fluid getFlowing();

    public abstract Fluid getSource() ;

    public abstract Item getBucket() ;

    protected boolean canConvertToSource() {
        return false;
    }

    protected void beforeDestroyingBlock(IWorld p_205580_1_, BlockPos p_205580_2_, BlockState p_205580_3_) {
        TileEntity tileentity = p_205580_3_.hasTileEntity() ? p_205580_1_.getBlockEntity(p_205580_2_) : null;
        Block.dropResources(p_205580_3_, p_205580_1_, p_205580_2_, tileentity);
    }

    public boolean canBeReplacedWith(FluidState state, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !isSame(fluid);
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0f;
    }

    public int getDropOff(IWorldReader reader) {
        return 1;
    }

}
