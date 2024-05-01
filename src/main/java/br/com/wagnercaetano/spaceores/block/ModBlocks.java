package br.com.wagnercaetano.spaceores.block;

import br.com.wagnercaetano.spaceores.block.custom.SoundBlock;
import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.block.custom.StrawberryCropBlock;
import br.com.wagnercaetano.spaceores.interfaces.BlockItemModelType;
import br.com.wagnercaetano.spaceores.util.RegisterBlockUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SpaceOres.MOD_ID);

    @BlockItemModelType("blockWithItem")
    public static final RegistryObject<Block> GALACTITE_BLOCK = RegisterBlockUtils.registerBlock(BLOCKS, "galactite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.CALCITE)));
    @BlockItemModelType("blockWithItem")
    public static final RegistryObject<Block> RAW_GALACTITE_BLOCK = RegisterBlockUtils.registerBlock(BLOCKS, "raw_galactite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).sound(SoundType.CALCITE)));
    @BlockItemModelType("blockWithItem")
    public static final RegistryObject<Block> SOUND_BLOCK = RegisterBlockUtils.registerBlock(BLOCKS, "sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    @BlockItemModelType("blockWithItem")
    public static final RegistryObject<Block> GALACTITE_ORE = ModOreBlocks.registryObjectFromName(BLOCKS, "galactite_ore");
    @BlockItemModelType("blockWithItem")
    public static final RegistryObject<Block> DEEPSLATE_GALACTITE_ORE = ModOreBlocks.registryObjectFromName(BLOCKS, "deepslate_galactite_ore");
    @BlockItemModelType("blockWithItem")
    public static final RegistryObject<Block> NETHER_GALACTITE_ORE = ModOreBlocks.registryObjectFromName(BLOCKS, "nether_galactite_ore");
    @BlockItemModelType("blockWithItem")
    public static final RegistryObject<Block> END_STONE_GALACTITE_ORE = ModOreBlocks.registryObjectFromName(BLOCKS, "end_stone_galactite_ore");

    public static final RegistryObject<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop",
            () -> new StrawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}