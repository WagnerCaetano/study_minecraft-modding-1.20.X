package br.com.wagnercaetano.spaceores.datagen;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.block.custom.CornCropBlock;
import br.com.wagnercaetano.spaceores.block.custom.StrawberryCropBlock;
import br.com.wagnercaetano.spaceores.constants.Constants;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static br.com.wagnercaetano.spaceores.util.CropContentUtils.cornStates;
import static br.com.wagnercaetano.spaceores.util.CropContentUtils.strawberryStates;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SpaceOres.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        List<RegistryObject<Block>> blocks = new ArrayList<>(ModBlocks.BLOCKS.getEntries());
        blocks.stream().filter(Constants.CustomAssets::isNotCustom)
                .forEach(this::blockWithItem);
        
        blockStraberryCrop((CropBlock) ModBlocks.STRAWBERRY_CROP.get(), "strawberry_stage_");
        makeCornCrop((CropBlock) ModBlocks.CORN_CROP.get(), "corn_stage_");
    }

    private void blockStraberryCrop(CropBlock block, String modelName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, models());

        getVariantBuilder(block).forAllStates(function);
    }

    public void makeCornCrop(CropBlock block, String modelName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, models());

        getVariantBuilder(block).forAllStates(function);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    
}
