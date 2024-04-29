package br.com.wagnercaetano.datagen;

import br.com.wagnercaetano.block.ModBlocks;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static br.com.wagnercaetano.utils.ReflectionUtils.getListClassesBasedOnType;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SpaceOres.MOD_ID, exFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void registerStatesAndModels() {
        List<Field> allFields = Arrays.asList(ModBlocks.class.getDeclaredFields());

        getListClassesBasedOnType(allFields, (object) -> blockWithItem((RegistryObject<Block>) object));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
