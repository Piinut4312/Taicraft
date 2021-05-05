package com.piinut.taicraft.event;

import com.google.common.eventbus.Subscribe;
import com.piinut.taicraft.register.ModItems;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ModVillagerTradeEvent {

    @SubscribeEvent

    public static void addVillagerTrades(VillagerTradesEvent event){
        if(event.getType() == VillagerProfession.FARMER){
            List<VillagerTrades.ITrade> trades = event.getTrades().get(1);
            trades.add(new BasicTrade(2, new ItemStack(ModItems.SCALLION.get(), 12), 20, 2));
            trades.add(new BasicTrade(1, new ItemStack(ModItems.PEANUT.get(), 12), 20, 1));
            trades.add(new BasicTrade(1, new ItemStack(ModItems.SWEET_POTATOES.get(), 6), 20, 2));
            trades.add(new BasicTrade(1, new ItemStack(ModItems.SOYBEAN.get(), 12), 20, 1));
            trades.add(new BasicTrade(6, new ItemStack(ModItems.BLACK_TEA_SAPLING.get(), 1), 20, 2));
            trades.add(new BasicTrade(new ItemStack(ModItems.RICE.get(), 20), new ItemStack(Items.EMERALD, 1), 16, 2, 1.0f));
            trades.add(new BasicTrade(new ItemStack(ModItems.PEANUT.get(), 20), new ItemStack(Items.EMERALD, 1), 16, 2, 1.0f));
            trades.add(new BasicTrade(new ItemStack(ModItems.SCALLION.get(), 18), new ItemStack(Items.EMERALD, 1), 16, 2, 1.0f));
            trades.add(new BasicTrade(new ItemStack(ModItems.SWEET_POTATOES.get(), 10), new ItemStack(Items.EMERALD, 1), 16, 2, 1.0f));
        }
    }

}
