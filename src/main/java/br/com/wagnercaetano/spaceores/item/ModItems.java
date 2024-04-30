package br.com.wagnercaetano.spaceores.item;

import br.com.wagnercaetano.spaceores.item.custom.FuelItem;
import br.com.wagnercaetano.spaceores.item.custom.GalactiteStaff;
import br.com.wagnercaetano.spaceores.item.custom.MetalDetectorItem;
import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.item.custom.ModArmorItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SpaceOres.MOD_ID);

    public static final RegistryObject<Item> GALACTITE = ITEMS.register("galactite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_GALACTITE = ITEMS.register("raw_galactite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> GALACTITE_STAFF = ITEMS.register("galactite_staff",
            () -> new GalactiteStaff(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> GALACTITE_SWORD = ITEMS.register("galactite_sword",
            () -> new SwordItem(ModToolsTiers.GALACTITE, 3, -2.2f, new Item.Properties()));
    public static final RegistryObject<Item> GALACTITE_PICKAXE = ITEMS.register("galactite_pickaxe",
            () -> new PickaxeItem(ModToolsTiers.GALACTITE, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> GALACTITE_AXE = ITEMS.register("galactite_axe",
            () -> new AxeItem(ModToolsTiers.GALACTITE, 5, -3f, new Item.Properties()));
    public static final RegistryObject<Item> GALACTITE_SHOVEL = ITEMS.register("galactite_shovel",
            () -> new ShovelItem(ModToolsTiers.GALACTITE, 0, -3f, new Item.Properties()));
    public static final RegistryObject<Item> GALACTITE_HOE = ITEMS.register("galactite_hoe",
            () -> new HoeItem(ModToolsTiers.GALACTITE, 0, 0, new Item.Properties()));

    public static final RegistryObject<Item> GALACTITE_HELMET = ITEMS.register("galactite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.GALACTITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> GALACTITE_CHESTPLATE = ITEMS.register("galactite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.GALACTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> GALACTITE_LEGGINGS = ITEMS.register("galactite_leggings",
            () -> new ArmorItem(ModArmorMaterials.GALACTITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> GALACTITE_BOOTS = ITEMS.register("galactite_boots",
            () -> new ArmorItem(ModArmorMaterials.GALACTITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}