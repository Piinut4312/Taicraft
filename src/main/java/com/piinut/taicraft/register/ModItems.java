package com.piinut.taicraft.register;

import com.piinut.taicraft.Main;
import com.piinut.taicraft.fluid.BrineFluid;
import com.piinut.taicraft.fluid.SoyMilkFluid;
import com.piinut.taicraft.item.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;
import vazkii.patchouli.api.PatchouliAPI;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Item> RICE = ITEMS.register("rice", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> RICE_SEED = ITEMS.register("rice_seed", () -> new BlockNamedItem(
            ModBlocks.RICE.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> BOWL_OF_RICE = ITEMS.register("bowl_of_rice", () -> new BowlOfRice(
            new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1).food(ModFoods.BOWL_OF_RICE).craftRemainder(Items.BOWL)
    ));

    public static final RegistryObject<Item> CHICKEN_RICE = ITEMS.register("chicken_rice", () -> new ChickenRice(
            new Item.Properties().tab(Main.MOD_GROUP).craftRemainder(Items.BOWL).food(ModFoods.CHICKEN_RICE)
    ));

    public static final RegistryObject<Item> FLOUR = ITEMS.register("flour", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> DOUGH = ITEMS.register("dough", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<BlockItem> STEAMER = ITEMS.register("steamer", () -> new BlockItem(
            ModBlocks.STEAMER.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<BlockItem> FLUID_TANK = ITEMS.register("fluid_tank", () -> new BlockItem(
            ModBlocks.FLUID_TANK.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> BEVERAGE_CONTAINER = ITEMS.register("beverage_container", () -> new BlockItem(
            ModBlocks.BEVERAGE_CONTAINER.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> SOY_MILK_BUCKET = ITEMS.register("soy_milk_bucket", () -> new SoyMilkBucket(
            ModFluids.SOY_MILK, new Item.Properties().tab(Main.MOD_GROUP).craftRemainder(Items.BUCKET).stacksTo(1)
    ));

    public static final RegistryObject<Item> BLACK_TEA_BUCKET = ITEMS.register("black_tea_bucket", () -> new BlackTeaBucket(
            ModFluids.BLACK_TEA, new Item.Properties().tab(Main.MOD_GROUP).craftRemainder(Items.BUCKET).stacksTo(1)
    ));

    public static final RegistryObject<Item> MILK_TEA_BUCKET = ITEMS.register("milk_tea_bucket", () -> new MilkTeaBucket(
            ModFluids.MILK_TEA, new Item.Properties().tab(Main.MOD_GROUP).craftRemainder(Items.BUCKET).stacksTo(1)
    ));

    public static final RegistryObject<Item> SOY_MILK_BOTTLE = ITEMS.register("soy_milk_bottle", () -> new SoyMilkBottle(
            new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1)
    ));

    public static final RegistryObject<Item> BLACK_TEA_BOTTLE = ITEMS.register("black_tea_bottle", () -> new BlackTeaBottle(
            new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1)
    ));

    public static final RegistryObject<Item> MILK_TEA_BOTTLE = ITEMS.register("milk_tea_bottle", () -> new MilkTeaBottle(
            new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1)
    ));

    public static final RegistryObject<Item> STEAMED_BEEF = ITEMS.register("steamed_beef", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STEAMED_BEEF)
    ));

    public static final RegistryObject<Item> STEAMED_PORKCHOP = ITEMS.register("steamed_porkchop", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STEAMED_PORKCHOP)
    ));

    public static final RegistryObject<Item> STEAMED_MUTTON = ITEMS.register("steamed_mutton", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STEAMED_MUTTON)
    ));

    public static final RegistryObject<Item> STEAMED_CHICKEN = ITEMS.register("steamed_chicken", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STEAMED_CHICKEN)
    ));

    public static final RegistryObject<Item> STEAMED_RABBIT = ITEMS.register("steamed_rabbit", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STEAMED_RABBIT)
    ));

    public static final RegistryObject<Item> SUNNY_SIDE_UP_EGG = ITEMS.register("sunny_side_up_egg", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.SUNNY_SIDE_UP_EGG)
    ));

    public static final RegistryObject<Item> STEAMED_BREAD = ITEMS.register("steamed_bread", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STEAMED_BREAD)
    ));

    public static final RegistryObject<Item> STEAMED_BREAD_WITH_EGG = ITEMS.register("steamed_bread_with_egg", ()-> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STEAMED_BREAD_WITH_EGG)
    ));

    public static final RegistryObject<Item> INTESTINE = ITEMS.register("intestine", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.INTESTINE)
    ));

    public static final RegistryObject<Item> WASHED_INTESTINE = ITEMS.register("washed_intestine", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.WASHED_INTESTINE)
    ));

    public static final RegistryObject<Item> COOKED_INTESTINE = ITEMS.register("cooked_intestine", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.COOKED_INTESTINE)
    ));

    public static final RegistryObject<Item> SAUSAGE_CASING = ITEMS.register("sausage_casing", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> RAW_SAUSAGE = ITEMS.register("raw_sausage", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.RAW_SAUSAGE)
    ));

    public static final RegistryObject<Item> GRILLED_SAUSAGE = ITEMS.register("grilled_sausage", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.GRILLED_SAUSAGE)
    ));

    public static final RegistryObject<Item> STICKY_RICE_SAUSAGE = ITEMS.register("sticky_rice_sausage", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STICKY_RICE_SAUSAGE)
    ));

    public static final RegistryObject<Item> TAIWANESE_SAUSAGE_WITH_STICKY_RICE = ITEMS.register("taiwanese_sausage_with_sticky_rice", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.TAIWANESE_SAUSAGE_WITH_STICKY_RICE)
    ));

    public static final RegistryObject<Item> SCALLION = ITEMS.register("scallion", () -> new BlockNamedItem(
            ModBlocks.SCALLION.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> SCALLION_PANCAKE = ITEMS.register("scallion_pancake", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.SCALLION_PANCAKE)
    ));

    public static final RegistryObject<Item> BOWL_OF_NOODLES = ITEMS.register("bowl_of_noodles", () -> new BowlOfNoodles(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.BOWL_OF_NOODLES).stacksTo(1)
    ));

    public static final RegistryObject<Item> BEEF_NOODLES = ITEMS.register("beef_noodles", () -> new BeefNoodles(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.BEEF_NOODLES).stacksTo(1)
    ));

    public static final RegistryObject<Item> DISH = ITEMS.register("dish", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> OYSTER_SHELL = ITEMS.register("oyster_shell", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> OYSTER = ITEMS.register("oyster", () -> new OysterItem(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.OYSTER).craftRemainder(OYSTER_SHELL.get())
    ));

    public static final RegistryObject<Item> OYSTER_OMELET = ITEMS.register("oyster_omelet", () -> new OysterOmelet(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.OYSTER_OMELET).craftRemainder(ModItems.DISH.get())
    ));

    public static final RegistryObject<Item> OYSTER_VERMICELLI = ITEMS.register("oyster_vermicelli", () -> new OysterVermicelli(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.OYSTER_VERMICELLI).craftRemainder(Items.BOWL)
    ));

    public static final RegistryObject<Item> INTESTINE_VERMICELLI = ITEMS.register("intestine_vermicelli", () -> new IntestineVermicelli(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.INTESTINE_VERMICELLI)
    ));

    public static final RegistryObject<BlockItem> OYSTER_SHELL_BRICK = ITEMS.register("oyster_shell_brick", () -> new BlockItem(
            ModBlocks.OYSTER_SHELL_BRICK.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<BlockItem> OYSTER_SHELL_BRICK_STAIRS = ITEMS.register("oyster_shell_brick_stairs", () -> new BlockItem(
            ModBlocks.OYSTER_SHELL_BRICK_STAIRS.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<BlockItem> OYSTER_SHELL_BRICK_SLAB = ITEMS.register("oyster_shell_brick_slab", () -> new BlockItem(
            ModBlocks.OYSTER_SHELL_BRICK_SLAB.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<BlockItem> POLISHED_OYSTER_SHELL_BRICK = ITEMS.register("polished_oyster_shell_brick", () -> new BlockItem(
            ModBlocks.POLISHED_OYSTER_SHELL_BRICK.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> SWEET_POTATOES = ITEMS.register("sweet_potato", () -> new BlockNamedItem(
            ModBlocks.SWEET_POTATOES.get(), new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.SWEET_POTATO)
    ));

    public static final RegistryObject<Item> BAKED_SWEET_POTATOES = ITEMS.register("baked_sweet_potato", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.BAKED_SWEET_POTATO)
    ));

    public static final RegistryObject<Item> STEAMED_SWEET_POTATO = ITEMS.register("steamed_sweet_potato", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STEAMED_SWEET_POTATO)
    ));

    public static final RegistryObject<Item> SWEET_POTATO_FLOUR = ITEMS.register("sweet_potato_flour", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> PEANUT = ITEMS.register("peanut", () -> new BlockNamedItem(
            ModBlocks.PEANUTS.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> PEANUT_POWDER = ITEMS.register("peanut_powder", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> POTATO_STARCH = ITEMS.register("potato_starch", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> BAMBOO_LEAF = ITEMS.register("bamboo_leaf", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> ZONGZI = ITEMS.register("zongzi", () -> new ZongziItem(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.ZONGZI).craftRemainder(ModItems.BAMBOO_LEAF.get())
    ));

    public static final RegistryObject<Item> GUA_BAO = ITEMS.register("gua_bao", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.GUA_BAO)
    ));

    public static final RegistryObject<Item> SOYBEAN = ITEMS.register("soybean", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> SOYBEAN_SEED = ITEMS.register("soybean_seed", () -> new BlockNamedItem(
            ModBlocks.SOYBEANS.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> NIGARI = ITEMS.register("nigari", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> BRINE_BUCKET = ITEMS.register("brine_bucket", () -> new BrineBucket(
            ModFluids.BRINE, new Item.Properties().tab(Main.MOD_GROUP).craftRemainder(Items.BUCKET).stacksTo(1)
    ));

    public static final RegistryObject<Item> TOFU = ITEMS.register("tofu", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.TOFU)
    ));

    public static final RegistryObject<Item> STINKY_TOFU = ITEMS.register("stinky_tofu", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STINKY_TOFU)
    ));

    public static final RegistryObject<Item> BAO = ITEMS.register("bao", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.BAO)
    ));

    public static final RegistryObject<Item> RAW_BAO = ITEMS.register("raw_bao", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.RAW_BAO)
    ));

    public static final RegistryObject<Item> BLACK_TEA_SAPLING = ITEMS.register("black_tea_sapling", () -> new BlockNamedItem(
            ModBlocks.BLACK_TEA_TREE.get(), new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> BLACK_TEA_LEAVES = ITEMS.register("black_tea_leaves", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> EMPTY_TEA_BAG = ITEMS.register("empty_tea_bag", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> BLACK_TEA_BAG = ITEMS.register("black_tea_bag", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP)
    ));

    public static final RegistryObject<Item> STICKY_RICE_IN_BAMBOO = ITEMS.register("sticky_rice_in_bamboo", () -> new StickyRiceInBamboo(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.STICKY_RICE_IN_BAMBOO)
    ));

    public static final RegistryObject<Item> PIG_BLOOD_BUCKET = ITEMS.register("pig_blood_bucket", () -> new PigBloodBucket(
            new Item.Properties().tab(Main.MOD_GROUP).craftRemainder(Items.BUCKET).stacksTo(1)
    ));

    public static final RegistryObject<Item> PIG_BLOOD_CAKE = ITEMS.register("pig_blood_cake", () -> new Item(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.PIG_BLOOD_CAKE)
    ));

    public static final RegistryObject<Item> PIG_BLOOD_CAKE_WITH_PEANUT_POWDER = ITEMS.register("pig_blood_cake_with_peanut_powder", () -> new PigBloodCakeWithPeanutPowder(
            new Item.Properties().tab(Main.MOD_GROUP).food(ModFoods.PIG_BLOOD_CAKE_WITH_PEANUT_POWDER)
    ));

    public static final RegistryObject<Item> WOODEN_KNIFE = ITEMS.register("wooden_knife", () -> new KnifeItem(
            ItemTier.WOOD, 2, -1.6f, new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1)
    ));

    public static final RegistryObject<Item> STONE_KNIFE = ITEMS.register("stone_knife", () -> new KnifeItem(
            ItemTier.STONE, 2, -1.6f, new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1)
    ));

    public static final RegistryObject<Item> GOLDEN_KNIFE = ITEMS.register("golden_knife", () -> new KnifeItem(
            ItemTier.GOLD, 2, -1.6f, new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1)
    ));

    public static final RegistryObject<Item> IRON_KNIFE = ITEMS.register("iron_knife", () -> new KnifeItem(
            ItemTier.IRON, 2, -1.6f, new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1)
    ));

    public static final RegistryObject<Item> DIAMOND_KNIFE = ITEMS.register("diamond_knife", () -> new KnifeItem(
            ItemTier.DIAMOND, 2, -1.6f, new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1)
    ));

    public static final RegistryObject<Item> NETHERITE_KNIFE = ITEMS.register("netherite_knife", () -> new KnifeItem(
            ItemTier.NETHERITE, 2, -1.6f, new Item.Properties().tab(Main.MOD_GROUP).stacksTo(1)
    ));


    public static void register(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
