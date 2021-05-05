package com.piinut.taicraft.event;

import com.piinut.taicraft.register.ModItems;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WashIntestineEvent {

    @SubscribeEvent
    public static void washIntestine(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof ItemEntity && !(event.getEntity() instanceof SpecialItemEntity)){
            ItemEntity itemEntity = (ItemEntity) event.getEntity();
            if(itemEntity.getItem().getItem() == ModItems.INTESTINE.get()){
                event.setCanceled(true);
                SpecialItemEntity new_item = new SpecialItemEntity(event.getWorld(), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), new ItemStack(ModItems.INTESTINE.get(), itemEntity.getItem().getCount()));
                new_item.setPickUpDelay(50);
                new_item.setDeltaMovement(itemEntity.getDeltaMovement());
                event.getWorld().addFreshEntity(new_item);
            }
        }
    }


    static class SpecialItemEntity extends ItemEntity{

        public SpecialItemEntity(World world, double x, double y, double z, ItemStack stack) {
            super(world, x, y, z, stack);
        }

        @Override
        public void tick() {
            if(this.isInWater()){
                ItemEntity item = new ItemEntity(getCommandSenderWorld(), getX(), getY(), getZ(), new ItemStack(ModItems.WASHED_INTESTINE.get(), this.getItem().getCount()));
                item.setDeltaMovement(this.getDeltaMovement());
                getCommandSenderWorld().addFreshEntity(item);
                this.kill();
                getCommandSenderWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.VILLAGER_WORK_LEATHERWORKER, SoundCategory.AMBIENT, 100, 1);
            }else{
                super.tick();
            }
        }
    }
}
