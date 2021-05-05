package com.piinut.taicraft.block;

import com.piinut.taicraft.tileentity.SteamerTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SteamerBlock extends AbstractFurnaceBlock {

    public SteamerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected void openContainer(World world, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof SteamerTileEntity) {
            player.openMenu((INamedContainerProvider)tileentity);
        }
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new SteamerTileEntity();
    }
}
