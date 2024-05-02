package br.com.wagnercaetano.spaceores.util;

import br.com.wagnercaetano.spaceores.block.ModBlockOreInfoTable;
import br.com.wagnercaetano.spaceores.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegisterBlockUtils {

    public static void registryOreBlockFromName(DeferredRegister<Block> blockList, ModBlockOreInfoTable modBlockOreInfoTable) {
        RegisterBlockUtils.registerBlock(blockList, modBlockOreInfoTable.getName(),
                () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(modBlockOreInfoTable.getCopyParameter()).strength(modBlockOreInfoTable.getStrengthParameter()),
                        UniformInt.of(modBlockOreInfoTable.getMinExperienceParameter(), modBlockOreInfoTable.getMaxExperienceParameter())));
    }

    public static void registryRawBlockFromName(DeferredRegister<Block> blockList, ModBlockOreInfoTable modBlockOreInfoTable) {
        RegisterBlockUtils.registerBlock(blockList, modBlockOreInfoTable.getName(),
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
    }

    public static <T extends Block> RegistryObject<T> registerBlock(DeferredRegister<Block> blockList,  String name, Supplier<T> block) {
        RegistryObject<T> toReturn = blockList.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
