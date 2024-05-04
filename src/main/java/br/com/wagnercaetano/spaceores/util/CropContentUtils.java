package br.com.wagnercaetano.spaceores.util;

import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.block.custom.CornCropBlock;
import br.com.wagnercaetano.spaceores.block.custom.StrawberryCropBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;

public class CropContentUtils {

    public static ConfiguredModel[]  strawberryStates(BlockState state, CropBlock block, String modelName, BlockModelProvider blockModelProvider) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(blockModelProvider.crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
                new ResourceLocation(SpaceOres.MOD_ID, "block/" + modelName.split("_")[0] + "/" + modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    public static ConfiguredModel[] cornStates(BlockState state, CropBlock block, String modelName, BlockModelProvider blockModelProvider) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(blockModelProvider.crop(modelName + state.getValue(((CornCropBlock) block).getAgeProperty()),
                new ResourceLocation(SpaceOres.MOD_ID, "block/" + modelName.split("_")[0] + "/" + modelName + state.getValue(((CornCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
}
