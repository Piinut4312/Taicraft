package com.piinut.taicraft.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class BleedingEffect extends Effect {
    public BleedingEffect(EffectType type, int color) {
        super(type, color);
    }

    @Override
    public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
        int j = 50 >> p_76397_2_;
        if (j > 0) {
            return p_76397_1_ % j == 0;
        } else {
            return true;
        }
    }


    @Override
    public void applyEffectTick(LivingEntity livingEntity, int p_76394_2_) {
        livingEntity.hurt(DamageSource.MAGIC, 1.0F);
    }
}
