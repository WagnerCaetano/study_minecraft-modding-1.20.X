package br.com.wagnercaetano.spaceores.datagen;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SpaceOres.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        List<RegistryObject<Block>> allBlocks = new ArrayList<>(ModBlocks.BLOCKS.getEntries());
        allBlocks.forEach(this::blockWithItem);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
