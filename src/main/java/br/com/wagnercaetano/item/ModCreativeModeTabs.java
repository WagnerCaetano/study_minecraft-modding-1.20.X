package br.com.wagnercaetano.item;

import br.com.wagnercaetano.block.ModBlocks;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpaceOres.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("spaceores",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GALACTITE.get()))
                    .title(Component.translatable("creativetab.spaceores"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.GALACTITE.get());
                        pOutput.accept(ModItems.RAW_GALACTITE.get());

                        pOutput.accept(ModBlocks.GALACTITE_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_GALACTITE_BLOCK.get());

                        pOutput.accept(ModBlocks.GALACTITE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_GALACTITE_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_GALACTITE_ORE.get());
                        pOutput.accept(ModBlocks.END_STONE_GALACTITE_ORE.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}