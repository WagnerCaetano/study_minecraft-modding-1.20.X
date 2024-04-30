package br.com.wagnercaetano.spaceores.datagen;

import br.com.wagnercaetano.spaceores.item.ModItems;
import br.com.wagnercaetano.spaceores.item.custom.CustomItemAssets;
import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SpaceOres.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        List<RegistryObject<Item>> allBlocks = new ArrayList<>(ModItems.ITEMS.getEntries());
        allBlocks.stream().filter(CustomItemAssets::isNotCustomItem).forEach(this::simpleItem);
    }

    private void simpleItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SpaceOres.MOD_ID, "item/" + item.getId().getPath()));
    }
}
