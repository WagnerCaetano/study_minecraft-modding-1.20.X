package br.com.wagnercaetano.spaceores.datagen;

import br.com.wagnercaetano.spaceores.item.ModItems;
import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.item.custom.CustomItemAssets;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.*;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static br.com.wagnercaetano.spaceores.constants.Constants.trimMaterials;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SpaceOres.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        List<RegistryObject<Item>> items = new ArrayList<>(ModItems.ITEMS.getEntries());

        items.stream().filter(CustomItemAssets::isNotCustomItem)
                .forEach(itemRegistryObject -> {
            Item item = itemRegistryObject.get();
            if(item instanceof ArmorItem) {
                trimmedArmorItem(itemRegistryObject);
            } else if (item instanceof TieredItem) {
                simpleHandHeldItem(itemRegistryObject);
            }
             else if (!(item instanceof BlockItem) || item instanceof ItemNameBlockItem) {
                simpleBlockItem(itemRegistryObject);
            }
        });

    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = SpaceOres.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {

                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }


    private void simpleHandHeldItem(RegistryObject<Item> item) {
        setResourceItemModel(item, "item/handheld");
    }

    private void simpleBlockItem(RegistryObject<Item> item) {
        setResourceItemModel(item, "item/generated");
    }

    private void setResourceItemModel(RegistryObject<Item> item, String type) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation(type)).texture("layer0",
                new ResourceLocation(SpaceOres.MOD_ID, "item/" + item.getId().getPath()));
    }
}
