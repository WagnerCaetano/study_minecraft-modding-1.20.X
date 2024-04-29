package br.com.wagnercaetano.datagen;

import br.com.wagnercaetano.block.ModBlocks;
import br.com.wagnercaetano.item.ModItems;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static br.com.wagnercaetano.utils.ReflectionUtils.getListClassesBasedOnType;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SpaceOres.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void registerModels() {
        List<Field> allFields = Arrays.asList(ModItems.class.getDeclaredFields());

        getListClassesBasedOnType(allFields, (object) -> simpleItem((RegistryObject<Item>) object));
    }



    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SpaceOres.MOD_ID, "item/" + item.getId().getPath()));
    }
}
