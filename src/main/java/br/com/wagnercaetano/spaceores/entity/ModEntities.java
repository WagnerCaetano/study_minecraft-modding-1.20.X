package br.com.wagnercaetano.spaceores.entity;

import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.entity.custom.RhinoEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SpaceOres.MOD_ID);

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO = ENTITY_TYPES.register("rhino",
            () -> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                    .sized(2.5f, 2.5f)
                    .build(SpaceOres.MOD_ID + ":rhino"));

    public static void register(IEventBus iEventBus) {
        ENTITY_TYPES.register(iEventBus);
    }

}
