package com.piinut.taicraft.tileentity;

import com.piinut.taicraft.register.ModFluids;
import com.piinut.taicraft.register.ModItems;
import com.piinut.taicraft.register.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidStack;

public class BeverageContainerTileEntity extends TileEntity{

    private FluidStack fluid;

    public BeverageContainerTileEntity() {
        super(ModTileEntities.BEVERAGE_CONTAINER.get());
        fluid = new FluidStack(FluidStack.EMPTY, 1);
    }

    public Fluid getFluid(){
        return this.fluid.getFluid();
    }

    public int getFluidLevel(){ return this.fluid.getAmount(); }

    public boolean hasFluid(){
        return this.getFluid() != Fluids.EMPTY;
    }

    public boolean isFull(){ return getFluidLevel() == 3;}

    public boolean isValidFluidItem(ItemStack stack){
        Item item = stack.getItem();
        if(!(item instanceof BucketItem) || item == Items.BUCKET){
            return false;
        }
        BucketItem bucket = (BucketItem) item;
        if(bucket.getFluid() == Fluids.LAVA || bucket.getFluid() == ModFluids.BRINE.get()){
            return false;
        }
        return true;
    }

    public void setFluid(Fluid fluid){
        this.fluid = new FluidStack(fluid, 3);
    }

    public ItemStack setFluid(ItemStack stack){
        if(isValidFluidItem(stack) && !this.hasFluid()){
            BucketItem bucket = (BucketItem)stack.getItem();
            setFluid(bucket.getFluid());
            playSound(getFluid().getAttributes().getEmptySound());
            this.setChanged();
            markDirty();
            return new ItemStack(Items.BUCKET);
        }
        return stack;
    }

    public void removeFluid(){
        if(hasFluid()){
            this.fluid = new FluidStack(Fluids.EMPTY, 1);
            this.setChanged();
            markDirty();
        }
    }

    public ItemStack removeFluid(ItemStack stack){
        if(stack.getItem() == Items.BUCKET){
            ItemStack bucket;
            if(hasFluid() && isFull()){
                bucket = new ItemStack(getFluid().getBucket());
                playSound(getFluid().getAttributes().getFillSound());
                removeFluid();
            }else{
                bucket = new ItemStack(Items.BUCKET);
            }
            return bucket;
        }
        return stack;
    }

    public ItemStack decrFluid(ItemStack stack){
        if(stack.getItem() == Items.GLASS_BOTTLE){
            ItemStack bottle;
            if(hasFluid()){
                if(getFluid() == Fluids.WATER){
                    bottle = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
                    playSound(SoundEvents.BOTTLE_FILL);
                }else if(getFluid() == ModFluids.BLACK_TEA.get()){
                    bottle = new ItemStack(ModItems.BLACK_TEA_BOTTLE.get());
                    playSound(SoundEvents.BOTTLE_FILL);
                }else if(getFluid() == ModFluids.SOY_MILK.get()){
                    bottle = new ItemStack(ModItems.SOY_MILK_BOTTLE.get());
                    playSound(SoundEvents.BOTTLE_FILL);
                }else if(getFluid() == ModFluids.MILK_TEA.get()){
                    bottle = new ItemStack(ModItems.MILK_TEA_BOTTLE.get());
                    playSound(SoundEvents.BOTTLE_FILL);
                }else {
                    bottle = new ItemStack(Items.GLASS_BOTTLE);
                }
                this.fluid.setAmount(this.fluid.getAmount()-1);
                if(this.fluid.getAmount() <= 0){
                    removeFluid();
                }
            }else{
                bottle = new ItemStack(Items.GLASS_BOTTLE);
            }
            return bottle;
        }
        return stack;
    }

    private void markDirty(){
        level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    private void playSound(SoundEvent soundEvent){
        level.playSound(null, getBlockPos(), soundEvent, SoundCategory.BLOCKS, 100, 1);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        fluid = FluidStack.loadFluidStackFromNBT(nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        fluid.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
        load(state, tag);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT compound = new CompoundNBT();
        save(compound);
        return new SUpdateTileEntityPacket(this.getBlockPos(), 0, compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT compound = pkt.getTag();
        this.load(this.getBlockState(), compound);
    }
}
