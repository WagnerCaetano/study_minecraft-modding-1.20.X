package br.com.wagnercaetano.spaceores.item;

import br.com.wagnercaetano.spaceores.item.custom.FuelItem;
import br.com.wagnercaetano.spaceores.item.custom.GalactiteStaff;
import br.com.wagnercaetano.spaceores.item.custom.MetalDetectorItem;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.world.item.Item;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}