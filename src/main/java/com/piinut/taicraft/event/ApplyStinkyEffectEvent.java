package com.piinut.taicraft.event;

import com.piinut.taicraft.register.ModPotions;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ApplyStinkyEffectEvent {

    @SubscribeEvent
    public static void modifyMobAI(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof CreatureEntity){
            CreatureEntity entity = (CreatureEntity)(event.getEntity());
            entity.goalSelector.addGoal(1, new AvoidEntityGoal(entity, PlayerEntity.class
                    , 12f, 0, 1.4){
                @Override
                public boolean canUse() {
                    return super.canUse() && this.toAvoid.hasEffect(ModPotions.STINKY.get());
                }

                /*
                @Override
                public boolean canContinueToUse() {
                    if(this.toAvoid == null){
                        return false;
                    }
                    if(!this.toAvoid.hasEffect(ModPotions.STINKY.get())){
                        return false;
                    }
                    return super.canContinueToUse();
                }
                */

            });
        }
    }
}
