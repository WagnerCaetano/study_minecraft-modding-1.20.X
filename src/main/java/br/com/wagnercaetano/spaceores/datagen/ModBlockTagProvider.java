package br.com.wagnercaetano.spaceores.datagen;

import br.com.wagnercaetano.spaceores.block.ModBlockOreInfoTable;
import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.util.ModTags;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static br.com.wagnercaetano.spaceores.block.ModBlockOreInfoTable.getByRegistryBlock;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SpaceOres.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        List<RegistryObject<Block>> blocks = new ArrayList<>(ModBlocks.BLOCKS.getEntries());
        blocks.forEach(block -> {
                    ModBlockOreInfoTable modBlockOreInfoTable = getByRegistryBlock(block);
                    if(Objects.nonNull(modBlockOreInfoTable)) {
                        modBlockOreInfoTable.getRequiresTool().forEach(tool ->
                            this.tag(tool).add(block.get())
                        );
                        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
                    }
                });

        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .addTag(Tags.Blocks.ORES);

        // this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
    }
}
