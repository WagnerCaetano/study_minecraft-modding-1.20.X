package br.com.wagnercaetano.spaceores;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.entity.ModEntities;
import br.com.wagnercaetano.spaceores.entity.client.RhinoRenderer;
import br.com.wagnercaetano.spaceores.item.ModCreativeModeTabs;
import br.com.wagnercaetano.spaceores.item.ModItems;
import br.com.wagnercaetano.spaceores.loot.ModLootModifiers;
import br.com.wagnercaetano.spaceores.sound.ModSounds;
import br.com.wagnercaetano.spaceores.villager.ModVillagers;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
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
*                                   MATERIAL PROGRESSION
*
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
        ModSounds.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

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
            EntityRenderers.register(ModEntities.RHINO.get(), RhinoRenderer::new);
        }
    }
}
