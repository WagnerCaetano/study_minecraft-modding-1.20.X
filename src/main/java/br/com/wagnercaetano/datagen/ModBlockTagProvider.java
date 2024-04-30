package br.com.wagnercaetano.datagen;

import br.com.wagnercaetano.block.ModBlocks;
import br.com.wagnercaetano.item.util.ModTags;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SpaceOres.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.GALACTITE_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GALACTITE_BLOCK.get())
                .add(ModBlocks.GALACTITE_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.DEEPSLATE_GALACTITE_ORE.get(),
                        ModBlocks.END_STONE_GALACTITE_ORE.get(),
                        ModBlocks.NETHER_GALACTITE_ORE.get(),
                        ModBlocks.RAW_GALACTITE_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GALACTITE_ORE.get(),
                        ModBlocks.DEEPSLATE_GALACTITE_ORE.get(),
                        ModBlocks.NETHER_GALACTITE_ORE.get(),
                        ModBlocks.END_STONE_GALACTITE_ORE.get(),
                        ModBlocks.RAW_GALACTITE_BLOCK.get(),
                        ModBlocks.GALACTITE_BLOCK.get(),
                        ModBlocks.SOUND_BLOCK.get());

        // this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
    }
}
