package com.piinut.taicraft.event;

import com.piinut.taicraft.item.KnifeItem;
import com.piinut.taicraft.register.ModPotions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ApplyBleedingEffectEvent {

    @SubscribeEvent
    public static void applyEffect(AttackEntityEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.getMainHandItem().getItem() instanceof KnifeItem){
            if(event.getTarget() instanceof LivingEntity){
                ((LivingEntity)event.getTarget()).addEffect(new EffectInstance(ModPotions.BLEEDING.get(), 20*8));
            }
        }
    }

}
