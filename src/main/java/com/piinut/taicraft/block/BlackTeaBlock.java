package com.piinut.taicraft.block;


import com.piinut.taicraft.register.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlackTeaBlock extends BushBlock implements IGrowable {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;

    public BlackTeaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0));
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(ModItems.BLACK_TEA_SAPLING.get());
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 1;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = state.getValue(AGE);
        if(i < 1 && world.getRawBrightness(pos.above(), 0) > 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state,random.nextInt(10) == 0)){
            world.setBlock(pos, state.setValue(AGE, 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean p_176473_4_) {
        return state.getValue(AGE) < 1;
    }

    @Override
    public boolean isBonemealSuccess(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if(state.getValue(AGE) < 1 && random.nextFloat() > 0.8){
            world.setBlock(pos, state.setValue(AGE, 1), 2);
        }
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        int i = state.getValue(AGE);
        Item item = player.getItemInHand(hand).getItem();
        if(item == Items.BONE_MEAL){
            return ActionResultType.PASS;
        }else if(i > 0 && item == Items.SHEARS){
            Random random = new Random();
            int count = 1+world.random.nextInt(8);
            popResource(world, pos, new ItemStack(ModItems.BLACK_TEA_LEAVES.get(), count));
            if(random.nextInt(6) == 1){
                popResource(world, pos, new ItemStack(ModItems.BLACK_TEA_SAPLING.get(), 1));
            }
            world.setBlock(pos, state.setValue(AGE, 0), 2);
            world.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundCategory.BLOCKS, 1, 1);
            return ActionResultType.sidedSuccess(world.isClientSide());
        }else{
            return super.use(state, world, pos, player, hand, rayTraceResult);
        }
    }
}
