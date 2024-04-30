package br.com.wagnercaetano.spaceores.util;

import br.com.wagnercaetano.spaceores.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegisterBlockUtils {

    public static <T extends Block> RegistryObject<T> registerBlock(DeferredRegister<Block> blockList,  String name, Supplier<T> block) {
        RegistryObject<T> toReturn = blockList.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
