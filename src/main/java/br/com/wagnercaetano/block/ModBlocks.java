package br.com.wagnercaetano.block;

import br.com.wagnercaetano.item.ModItems;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SpaceOres.MOD_ID);

    public static final RegistryObject<Block> GALACTITE_BLOCK = registerBlock("galactite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.CALCITE)));
    public static final RegistryObject<Block> RAW_GALACTITE_BLOCK = registerBlock("raw_galactite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).sound(SoundType.CALCITE)));

    public static final RegistryObject<Block> GALACTITE_ORE = registerBlock("galactite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .sound(SoundType.CALCITE).strength(2f), UniformInt.of(3, 6)));
    public static final RegistryObject<Block> DEEPSLATE_GALACTITE_ORE = registerBlock("deepslate_galactite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .sound(SoundType.CALCITE).strength(3f), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> NETHER_GALACTITE_ORE = registerBlock("nether_galactite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .sound(SoundType.CALCITE).strength(1f), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> END_STONE_GALACTITE_ORE = registerBlock("end_stone_galactite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                    .sound(SoundType.CALCITE).strength(5f), UniformInt.of(3, 7)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}