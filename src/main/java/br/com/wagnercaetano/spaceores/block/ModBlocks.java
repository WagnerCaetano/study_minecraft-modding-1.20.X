package br.com.wagnercaetano.spaceores.block;

import br.com.wagnercaetano.spaceores.block.custom.CornCropBlock;
import br.com.wagnercaetano.spaceores.block.custom.SoundBlock;
import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.block.custom.StrawberryCropBlock;
import br.com.wagnercaetano.spaceores.sound.ModSounds;
import br.com.wagnercaetano.spaceores.util.RegisterBlockUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static br.com.wagnercaetano.spaceores.block.ModBlockOreInfoTable.processOreInstances;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SpaceOres.MOD_ID);

    public static final RegistryObject<Block> CONSTELLARITE_BLOCK = RegisterBlockUtils.registerBlock(BLOCKS, "constellarite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS)));

    public static final RegistryObject<Block> GALACTITE_BLOCK = RegisterBlockUtils.registerBlock(BLOCKS, "galactite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.CALCITE)));
    public static final RegistryObject<Block> SOUND_BLOCK = RegisterBlockUtils.registerBlock(BLOCKS, "sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(ModSounds.SOUND_BLOCK_SOUNDS)));

    public static final RegistryObject<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop",
            () -> new StrawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop",
            () -> new CornCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static void register(IEventBus eventBus) {
        processOreInstances(BLOCKS).register(eventBus);
    }
}