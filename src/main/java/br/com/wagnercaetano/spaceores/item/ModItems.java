package br.com.wagnercaetano.spaceores.item;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.entity.ModEntities;
import br.com.wagnercaetano.spaceores.item.custom.FuelItem;
import br.com.wagnercaetano.spaceores.item.custom.GalactiteStaff;
import br.com.wagnercaetano.spaceores.item.custom.MetalDetectorItem;
import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.sound.ModSounds;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static br.com.wagnercaetano.spaceores.item.ModItemOreInfoTable.generateMaterialItems;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpaceOres.MOD_ID);

    public static final RegistryObject<Item> CONSTELLARITE = ITEMS.register("constellarite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_CONSTELLARITE = ITEMS.register("raw_constellarite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GALACTITE = ITEMS.register("galactite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_GALACTITE = ITEMS.register("raw_galactite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GALACTITE_STAFF = ITEMS.register("galactite_staff",
            () -> new GalactiteStaff(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.STRAWBERRY_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds",
            () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            () -> new RecordItem(6, ModSounds.BAR_BRAWL.get(),
                    new Item.Properties().stacksTo(1), 2440));

    public static final RegistryObject<Item> RHINO_SPAWN_EGG = ITEMS.register("rhino_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RHINO, 0x8B4513, 0x000000,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        generateMaterialItems(ITEMS, ModItemOreInfoTable.CONSTELLARITE);
        generateMaterialItems(ITEMS, ModItemOreInfoTable.GALACTITE)
                .register(eventBus);
    }
}