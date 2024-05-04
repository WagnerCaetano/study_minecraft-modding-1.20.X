package br.com.wagnercaetano.spaceores.events;

import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.entity.ModEntities;
import br.com.wagnercaetano.spaceores.entity.client.ModModelLayers;
import br.com.wagnercaetano.spaceores.entity.client.RhinoModel;
import br.com.wagnercaetano.spaceores.entity.custom.RhinoEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpaceOres.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
    }
}
