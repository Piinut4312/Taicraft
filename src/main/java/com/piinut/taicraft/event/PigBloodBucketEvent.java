package com.piinut.taicraft.event;


import com.piinut.taicraft.register.ModItems;
import com.piinut.taicraft.register.ModPotions;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class PigBloodBucketEvent {
    @SubscribeEvent
    public static void handleBucket(PlayerInteractEvent.EntityInteract event){
        if(event.getTarget() instanceof PigEntity && event.getItemStack().getItem() instanceof BucketItem){
            PigEntity pig = (PigEntity) event.getTarget();
            if(pig.hasEffect(ModPotions.BLEEDING.get()) && pig.getEffect(ModPotions.BLEEDING.get()).getDuration() > 0){
                if(!event.getPlayer().isCreative()){
                    event.getItemStack().shrink(1);
                }
                pig.kill();
                event.getPlayer().addItem(new ItemStack(ModItems.PIG_BLOOD_BUCKET.get(), 1));
            }
        }
    }
}
