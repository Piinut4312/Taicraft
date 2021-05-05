package com.piinut.taicraft.block;

import com.piinut.taicraft.register.ModFluids;
import com.piinut.taicraft.register.ModItems;
import com.piinut.taicraft.tileentity.FluidTankTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class FluidTankBlock extends Block {

    public FluidTankBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return VoxelShapes.or(
                Block.box(0, 0, 0, 16, 1, 16),
                Block.box(0, 1, 15, 15, 16, 16),
                Block.box(15, 1, 1, 16, 16, 16),
                Block.box(1, 1, 0, 16, 16, 1),
                Block.box(0, 1, 0, 1, 16, 15)
        ).optimize();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FluidTankTileEntity();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        ItemStack handStack = player.getItemInHand(hand);
        FluidTankTileEntity tank = (FluidTankTileEntity) world.getBlockEntity(pos);
        if(world.isClientSide){
            return ActionResultType.SUCCESS;
        }else{
            Item item = handStack.getItem();
            if(tank.isFluidItem(handStack)){
                ItemStack resultStack = tank.setFluid(handStack);
                if(!player.abilities.instabuild){
                    player.setItemInHand(hand, resultStack);
                }
                return ActionResultType.SUCCESS;
            }else if(item == Items.BUCKET){
                ItemStack resultStack = tank.removeFluid(handStack);
                if(!player.abilities.instabuild){
                    handStack.shrink(1);
                    if(handStack.isEmpty()){
                        player.setItemInHand(hand, resultStack);
                    }else if(player.inventory.add(resultStack)){
                        player.drop(resultStack, false);
                    }
                }
                return ActionResultType.SUCCESS;
            }else if(item instanceof BlockItem){
                return ActionResultType.SUCCESS;
            }else if(tank.canPutInTank(handStack)){
                Vector3d hitPos = rayTraceResult.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
                int hitResult = (int) (Math.floor(hitPos.x*3) + 3*Math.floor(hitPos.z*2));
                if(hitResult > 5){
                    hitResult = 5;
                }
                if(hitResult < 0){
                    hitResult = 0;
                }
                ItemStack tankStack = tank.getItem(hitResult);
                if(handStack.isEmpty()){
                    player.setItemInHand(hand, tankStack);
                    tank.removeItem(hitResult);
                    return ActionResultType.SUCCESS;
                }else if(tankStack.isEmpty()){
                    tank.addItem(hitResult, handStack.getItem());
                    if(!player.abilities.instabuild){
                        handStack.shrink(1);
                    }
                    return ActionResultType.SUCCESS;
                }else{
                    if(!player.abilities.instabuild){
                        if(player.inventory.add(tankStack)){
                            player.drop(tankStack, false);
                        }
                    }
                    tank.removeItem(hitResult);
                    return ActionResultType.SUCCESS;
                }
            }else {
                return ActionResultType.PASS;
            }
        }
    }

    @Override
    public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, TileEntity tile, ItemStack stack) {
        super.playerDestroy(world, player, pos, state, tile, stack);
        if(tile instanceof FluidTankTileEntity){
            FluidTankTileEntity tank = (FluidTankTileEntity)tile;
            InventoryHelper.dropContents(world, pos, tank.getAllItems());
            if(tank.hasFluid()){
                world.setBlock(pos, tank.getFluid().defaultFluidState().createLegacyBlock(), 1);
            }
        }
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        super.entityInside(state, world, pos, entity);
        if(world.getBlockEntity(pos) instanceof FluidTankTileEntity){
            FluidTankTileEntity tank = (FluidTankTileEntity) world.getBlockEntity(pos);
            Fluid fluid = tank.getFluid();
            if(fluid == Fluids.WATER || fluid == ModFluids.SOY_MILK.get()){
                if(entity.isOnFire()){
                    entity.clearFire();
                }
                if(fluid == Fluids.WATER){
                    if(!world.isClientSide() && entity instanceof ItemEntity){
                        ItemEntity itemEntity = (ItemEntity)entity;
                        if(itemEntity.getItem().getItem() == ModItems.INTESTINE.get()){
                            world.addFreshEntity(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.WASHED_INTESTINE.get(), itemEntity.getItem().getCount())));
                            world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.VILLAGER_WORK_LEATHERWORKER, SoundCategory.AMBIENT, 100, 1);
                            entity.kill();
                        }
                    }
                }
            }else if(fluid == Fluids.LAVA){
                if(!entity.fireImmune()){
                    entity.setSecondsOnFire(8);
                }
            }
        }
    }
}
