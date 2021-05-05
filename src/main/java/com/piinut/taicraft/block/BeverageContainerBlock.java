package com.piinut.taicraft.block;

import com.piinut.taicraft.register.ModFluids;
import com.piinut.taicraft.tileentity.BeverageContainerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BeverageContainerBlock extends HorizontalBlock {

    public static final VoxelShape NORTH_SHAPE = VoxelShapes.or(
            Block.box(3, 0, 3, 13, 1, 13),
            Block.box(3, 12, 3, 13, 13, 13),
            Block.box(3, 1, 3, 13, 12, 4),
            Block.box(3, 1, 12, 13, 12, 13),
            Block.box(2, 10, 8, 3, 16, 9),
            Block.box(13, 10, 8, 14, 16, 9),
            Block.box(3, 15, 8, 13, 16, 9),
            Block.box(12, 1, 4, 13, 12, 12),
            Block.box(7, 2, 0, 9, 5, 2),
            Block.box(7, 5, 1, 9, 7, 2),
            Block.box(7, 3, 2, 9, 5, 3),
            Block.box(3, 1, 4, 4, 12, 12)
    ).optimize();

    public static final VoxelShape EAST_SHAPE = VoxelShapes.or(
            Block.box(3, 0, 3, 13, 1, 13),
            Block.box(3, 12, 3, 13, 13, 13),
            Block.box(12, 1, 3, 13, 12, 13),
            Block.box(3, 1, 3, 4, 12, 13),
            Block.box(7, 10, 2, 8, 16, 3),
            Block.box(7, 10, 13, 8, 16, 14),
            Block.box(7, 15, 3, 8, 16, 13),
            Block.box(4, 1, 12, 12, 12, 13),
            Block.box(14, 2, 7, 16, 5, 9),
            Block.box(14, 5, 7, 15, 7, 9),
            Block.box(13, 3, 7, 14, 5, 9),
            Block.box(4, 1, 3, 12, 12, 4)
    ).optimize();

    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.or(
            Block.box(3, 0, 3, 13, 1, 13),
            Block.box(3, 12, 3, 13, 13, 13),
            Block.box(3, 1, 12, 13, 12, 13),
            Block.box(3, 1, 3, 13, 12, 4),
            Block.box(13, 10, 7, 14, 16, 8),
            Block.box(2, 10, 7, 3, 16, 8),
            Block.box(3, 15, 7, 13, 16, 8),
            Block.box(3, 1, 4, 4, 12, 12),
            Block.box(7, 2, 14, 9, 5, 16),
            Block.box(7, 5, 14, 9, 7, 15),
            Block.box(7, 3, 13, 9, 5, 14),
            Block.box(12, 1, 4, 13, 12, 12)
    ).optimize();

    public static final VoxelShape WEST_SHAPE = VoxelShapes.or(
            Block.box(3, 0, 3, 13, 1, 13),
            Block.box(3, 12, 3, 13, 13, 13),
            Block.box(3, 1, 3, 4, 12, 13),
            Block.box(12, 1, 3, 13, 12, 13),
            Block.box(8, 10, 13, 9, 16, 14),
            Block.box(8, 10, 2, 9, 16, 3),
            Block.box(8, 15, 3, 9, 16, 13),
            Block.box(4, 1, 3, 12, 12, 4),
            Block.box(0, 2, 7, 2, 5, 9),
            Block.box(1, 5, 7, 2, 7, 9),
            Block.box(2, 3, 7, 3, 5, 9),
            Block.box(4, 1, 12, 12, 12, 13)
    ).optimize();

    public static final VoxelShape[] SHAPES = new VoxelShape[]{
            NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE,
    };

    public BeverageContainerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BeverageContainerTileEntity();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        int id = 0;
        Direction facing = state.getValue(FACING);
        if(facing == Direction.EAST){
            id += 1;
        }else if(facing == Direction.SOUTH){
            id += 2;
        }else if(facing == Direction.WEST){
            id += 3;
        }
        return SHAPES[id];
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        ItemStack handStack = player.getItemInHand(hand);
        BeverageContainerTileEntity tileEntity = (BeverageContainerTileEntity) world.getBlockEntity(pos);
        if(world.isClientSide){
            return ActionResultType.SUCCESS;
        }else{
            Item handItem = handStack.getItem();
            if(tileEntity.isValidFluidItem(handStack)){
                ItemStack resultStack = tileEntity.setFluid(handStack);
                if(!player.abilities.instabuild){
                    player.setItemInHand(hand, resultStack);
                }
                return ActionResultType.SUCCESS;
            }else if(handItem == Items.BUCKET){
                ItemStack resultStack = tileEntity.removeFluid(handStack);
                if(!player.abilities.instabuild){
                    handStack.shrink(1);
                    if(handStack.isEmpty()){
                        player.setItemInHand(hand, resultStack);
                    }else if(player.inventory.add(resultStack)){
                        player.drop(resultStack, false);
                    }
                }
            }else if(handItem == Items.GLASS_BOTTLE){
                ItemStack resultStack = tileEntity.decrFluid(handStack);
                if(!player.abilities.instabuild){
                    handStack.shrink(1);
                    if(handStack.isEmpty()){
                        player.setItemInHand(hand, resultStack);
                    }else if(player.inventory.add(resultStack)){
                        player.drop(resultStack, false);
                    }
                }
            }else if(handItem == Items.MILK_BUCKET){
                if(tileEntity.getFluid() == ModFluids.BLACK_TEA.get()){
                    ItemStack resultStack = new ItemStack(Items.BUCKET);
                    tileEntity.setFluid(ModFluids.MILK_TEA.get());
                    if(!player.abilities.instabuild){
                        handStack.shrink(1);
                        if(handStack.isEmpty()){
                            player.setItemInHand(hand, resultStack);
                        }else if(player.inventory.add(resultStack)){
                            player.drop(resultStack, false);
                        }
                    }
                }
            }else {
                return ActionResultType.PASS;
            }
        }
        return super.use(state, world, pos, player, hand, rayTraceResult);
    }

}
