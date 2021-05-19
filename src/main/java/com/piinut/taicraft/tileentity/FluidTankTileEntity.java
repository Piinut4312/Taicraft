package com.piinut.taicraft.tileentity;

import com.piinut.taicraft.recipe.FluidTankRecipe;
import com.piinut.taicraft.register.ModRecipes;
import com.piinut.taicraft.register.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class FluidTankTileEntity extends TileEntity implements ITickableTileEntity{

    private int cookTimer;
    private FluidStack fluid;
    private static final int maxTime = 200000;
    public ItemStackHandler inventory = new ItemStackHandler(6){

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return canPutInTank(stack);
        }

        @Override
        protected void onContentsChanged(int slot) {
            markDirty();
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            markDirty();
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            markDirty();
            return super.extractItem(slot, amount, simulate);
        }
    };
    LazyOptional<IItemHandler> item_handler = LazyOptional.of(()->inventory);

    public FluidTankTileEntity() {
        super(ModTileEntities.FLUID_TANK.get());
        cookTimer = 0;
        fluid = new FluidStack(Fluids.EMPTY, 1);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return item_handler.cast();
        }
        return super.getCapability(cap, side);
    }

    public int getCookTimer(){
        return this.cookTimer;
    }

    public boolean canPutInTank(ItemStack stack){
        return !isFluidItem(stack)  && stack.getItem() != Items.BUCKET && !(stack.getItem() instanceof BlockItem);
    }

    public Fluid getFluid(){
        return this.fluid.getFluid();
    }

    public boolean hasFluid(){
        return this.getFluid() != Fluids.EMPTY;
    }

    public boolean isFluidItem(ItemStack stack){
        Item item = stack.getItem();
        return item instanceof BucketItem && item != Items.BUCKET;
    }

    public void setFluid(Fluid fluid){
        this.fluid = new FluidStack(fluid, 1);
    }

    public ItemStack setFluid(ItemStack stack){
        if(isFluidItem(stack) && !this.hasFluid()){
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
            cookTimer = 0;
            this.setChanged();
            markDirty();
        }
    }

    public ItemStack removeFluid(ItemStack stack){
        if(stack.getItem() == Items.BUCKET){
            ItemStack bucket;
            if(hasFluid()){
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

    public NonNullList<ItemStack> getAllItems() {
        NonNullList<ItemStack> items = NonNullList.create();
        for(int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            items.add(stack);
        }
        return items;
    }

    public ItemStack getItem(int slot){
        return inventory.getStackInSlot(slot);
    }

    public void addItem(int slot, ItemStack stack){
        ItemStack stack1 = stack.copy();
        stack1.setCount(1);
        inventory.insertItem(slot, stack1, false);
    }

    public void addItem(int slot, Item item){
        inventory.insertItem(slot, new ItemStack(item, 1), false);
    }

    public void fillItems(Item item){
        for(int i = 0; i < 6; i++){
            if(getItem(i).isEmpty()){
                addItem(i, item);
            }
        }
    }

    public void removeItem(int slot){
        inventory.extractItem(slot, 1, false);
    }

    public void emptyItems(){
        for(int i = 0; i < 6; i++){
            if(getItem(i) != ItemStack.EMPTY){
                removeItem(i);
            }
        }
    }

    public void replaceItems(Item target, Item replacement){
        for(int i = 0; i < 6; i++){
            if(getItem(i).getItem() == target){
                removeItem(i);
                addItem(i, replacement);
            }
        }
    }

    private void markDirty(){
        level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    private boolean isBurningBlock(BlockState state){
        Block block = state.getBlock();
        return block == Blocks.FIRE || block == Blocks.CAMPFIRE || block == Blocks.SOUL_CAMPFIRE || block == Blocks.LAVA;
    }

    private void playSound(SoundEvent soundEvent){
        level.playSound(null, getBlockPos(), soundEvent, SoundCategory.BLOCKS, 100, 1);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        inventory.deserializeNBT(compound.getCompound("inventory"));
        fluid = FluidStack.loadFluidStackFromNBT(compound);
        cookTimer = compound.getInt("cookTimer");
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        compound.put("inventory", inventory.serializeNBT());
        fluid.writeToNBT(compound);
        compound.putInt("cookTimer", getCookTimer());
        return compound;
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

    private void updateCookTimer(){
        if(getCookTimer() < maxTime){
            cookTimer++;
        }
    }

    public boolean isBurning(){
        return isBurningBlock(level.getBlockState(getBlockPos().below()));
    }

    private boolean handleRecipe(FluidTankRecipe recipe){
        if(recipe.isValid(this.inventory, this.getFluid())){
            updateCookTimer();
            if(getCookTimer() >= recipe.getCookTime()){
                if((recipe.requireHeat() && isBurning()) || !recipe.requireHeat()) {
                    setFluid(recipe.getFluidOut());
                    switch (recipe.getMode()) {
                        case REMOVE_ALL:
                            emptyItems();
                            break;
                        case REPLACE_ALL:
                            emptyItems();
                            fillItems(recipe.getResultItem().getItem());
                            break;
                        case REPLACE_ITEM:
                            replaceItems(recipe.getInput().getItem(), recipe.getResultItem().getItem());
                            break;
                        case REPLACE_EMPTY:
                            fillItems(recipe.getResultItem().getItem());
                            break;
                        default:
                            System.out.println("Nani???");
                            break;
                    }
                    cookTimer = 0;
                    playSound(recipe.getSound());
                    markDirty();
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public void tick() {
        ArrayList<FluidTankRecipe> recipe_later = new ArrayList<>();
        boolean shouldProcessLater = true;
        for(final IRecipe<?> r : ModRecipes.getRecipes(ModRecipes.FLUID_TANK_RECIPE_TYPE, level.getRecipeManager()).values()){
            final FluidTankRecipe recipe = (FluidTankRecipe) r;
            if(recipe.isProcessedLater()){
                recipe_later.add(recipe);
            }else{
                shouldProcessLater = shouldProcessLater && handleRecipe(recipe);
            }
        }
        if(shouldProcessLater){
            for(FluidTankRecipe recipe : recipe_later){
                handleRecipe(recipe);
            }
        }
        if(getFluid() == Fluids.LAVA){
            boolean flag = false;
            for(int i = 0; i < 6; i++){
                ItemStack stack = inventory.getStackInSlot(i);
                if(stack != ItemStack.EMPTY && !stack.getItem().isFireResistant()){
                    removeItem(i);
                    flag = true;
                }
            }
            if(flag){
                playSound(SoundEvents.GENERIC_BURN);
                markDirty();
            }
        }
    }

}
