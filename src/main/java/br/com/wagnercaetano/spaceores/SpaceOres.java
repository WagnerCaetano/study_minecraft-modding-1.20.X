package br.com.wagnercaetano.spaceores;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.item.ModCreativeModeTabs;
import br.com.wagnercaetano.spaceores.item.ModItems;
import br.com.wagnercaetano.spaceores.loot.ModLootModifiers;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/*
* MATERIAL PROGRESSION
* CONSTELLARITE  <     GALACTITE    <    NEBULIUM     <    STELLARITE   <   BOÖTES VOID
* BEFORE DIAMOND - BEFORE NETHERITE - AFTER NETHERITE - AFTER NEBULIUM - AFTER STELLARITE
*     GRAY       -       BLUE       -   MISC PURPLE   -  MISC YELLOW    -    MISC RED
* */
@Mod(SpaceOres.MOD_ID)
public class SpaceOres
{
    public static final String MOD_ID = "spaceores";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SpaceOres()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.GALACTITE);
            event.accept(ModItems.RAW_GALACTITE);
            event.accept(ModItems.CONSTELLARITE);
            event.accept(ModItems.RAW_CONSTELLARITE);
            event.accept(ModItems.PINE_CONE);
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.GALACTITE_BLOCK);
            event.accept(ModBlocks.RAW_GALACTITE_BLOCK);
            event.accept(ModBlocks.GALACTITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_GALACTITE_ORE);
            event.accept(ModBlocks.NETHER_GALACTITE_ORE);
            event.accept(ModBlocks.END_STONE_GALACTITE_ORE);
            event.accept(ModBlocks.CONSTELLARITE_BLOCK);
            event.accept(ModBlocks.RAW_CONSTELLARITE_BLOCK);
            event.accept(ModBlocks.CONSTELLARITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_CONSTELLARITE_ORE);
            event.accept(ModBlocks.NETHER_CONSTELLARITE_ORE);
            event.accept(ModBlocks.END_STONE_CONSTELLARITE_ORE);
        }

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.STRAWBERRY);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.SOUND_BLOCK);
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.METAL_DETECTOR);
            event.accept(ModItems.GALACTITE_STAFF);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
