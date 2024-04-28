package br.com.wagnercaetano.item;

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


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}