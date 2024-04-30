package br.com.wagnercaetano.block;

import br.com.wagnercaetano.block.custom.SoundBlock;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static br.com.wagnercaetano.block.util.RegisterBlockUtils.registerBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SpaceOres.MOD_ID);

    public static final RegistryObject<Block> GALACTITE_BLOCK = registerBlock(BLOCKS, "galactite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.CALCITE)));
    public static final RegistryObject<Block> RAW_GALACTITE_BLOCK = registerBlock(BLOCKS, "raw_galactite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).sound(SoundType.CALCITE)));

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock(BLOCKS, "sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> GALACTITE_ORE = ModOreBlocks.registryObjectFromName(BLOCKS, "galactite_ore");
    public static final RegistryObject<Block> DEEPSLATE_GALACTITE_ORE = ModOreBlocks.registryObjectFromName(BLOCKS, "deepslate_galactite_ore");
    public static final RegistryObject<Block> NETHER_GALACTITE_ORE = ModOreBlocks.registryObjectFromName(BLOCKS, "nether_galactite_ore");
    public static final RegistryObject<Block> END_STONE_GALACTITE_ORE = ModOreBlocks.registryObjectFromName(BLOCKS, "end_stone_galactite_ore");

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}