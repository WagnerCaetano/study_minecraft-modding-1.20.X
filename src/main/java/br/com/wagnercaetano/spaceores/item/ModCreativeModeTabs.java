package br.com.wagnercaetano.spaceores.item;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpaceOres.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SPACE_ORE_TAB = CREATIVE_MODE_TABS.register(SpaceOres.MOD_ID,
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GALACTITE.get()))
                    .title(Component.translatable("creativetab.spaceores"))
                    .displayItems((pParameters, pOutput) -> {
                        List<RegistryObject<Block>> allBlocks = new ArrayList<>(ModBlocks.BLOCKS.getEntries());
                        allBlocks.forEach(block -> pOutput.accept(new ItemStack(block.get())));

                        List<RegistryObject<Item>> allItems = new ArrayList<>(ModItems.ITEMS.getEntries());
                        allItems.forEach(item -> pOutput.accept(new ItemStack(item.get())));
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}