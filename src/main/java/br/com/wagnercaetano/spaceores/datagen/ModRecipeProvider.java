package br.com.wagnercaetano.spaceores.datagen;

import br.com.wagnercaetano.spaceores.item.ModItemOreInfoTable;
import br.com.wagnercaetano.spaceores.item.ModItems;
import br.com.wagnercaetano.spaceores.item.custom.ModArmorItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.apache.http.cookie.SM;

import java.util.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final Map<String, List<ItemLike>> SMELTABLES = Map.of(
            "constellarite", new ArrayList<>(),
            "galactite", new ArrayList<>()
    );

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        generateRecipeForItemsAndPopulateSmeltables(ModItemOreInfoTable.CONSTELLARITE, pRecipeOutput);
        generateRecipeForItemsAndPopulateSmeltables(ModItemOreInfoTable.GALACTITE, pRecipeOutput);
    }

    private void generateRecipeForItemsAndPopulateSmeltables(ModItemOreInfoTable modItemOreInfoTable, RecipeOutput pRecipeOutput) {
        Item itemMaterial = modItemOreInfoTable.getItem().get();
        String itemMaterialName = modItemOreInfoTable.getItem().getId().getPath();
        Block itemBlock = modItemOreInfoTable.getBlock().get();
        ModItems.ITEMS.getEntries().forEach(selectedItemRegistry -> {
            if (selectedItemRegistry.getId().getPath().contains(modItemOreInfoTable.getName())) {
                Item selectedItem = selectedItemRegistry.get();
                Class<?> selectedItemClass = selectedItem.getClass();
                if (selectedItemClass.equals(BlockItem.class) && ((BlockItem) selectedItem).getBlock() instanceof DropExperienceBlock) {
                    SMELTABLES.get(itemMaterialName).add(((BlockItem) selectedItem).getBlock());
                }
                if (selectedItemClass.equals(SwordItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, selectedItem)
                            .pattern("#")
                            .pattern("#")
                            .pattern("|")
                            .define('#', itemMaterial)
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                            .save(pRecipeOutput);
                } else if (selectedItemClass.equals(PickaxeItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, selectedItem)
                            .pattern("###")
                            .pattern(" | ")
                            .pattern(" | ")
                            .define('#', itemMaterial)
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                            .save(pRecipeOutput);
                } else if (selectedItemClass.equals(AxeItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, selectedItem)
                            .pattern("##")
                            .pattern("#|")
                            .pattern(" |")
                            .define('#', itemMaterial)
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                            .save(pRecipeOutput);
                } else if (selectedItemClass.equals(ShovelItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, selectedItem)
                            .pattern("#")
                            .pattern("|")
                            .pattern("|")
                            .define('#', itemMaterial)
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                            .save(pRecipeOutput);
                } else if (selectedItemClass.equals(HoeItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, selectedItem)
                            .pattern("##")
                            .pattern(" |")
                            .pattern(" |")
                            .define('#', itemMaterial)
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                            .save(pRecipeOutput);
                } else if (selectedItemClass.equals(ModArmorItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, selectedItem)
                            .pattern("###")
                            .pattern("# #")
                            .define('#', itemMaterial)
                            .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                            .save(pRecipeOutput);
                } else if (selectedItemClass.equals(ArmorItem.class)) {
                    ArmorItem.Type type = ((ArmorItem)selectedItem).getType();
                    if (type.equals(ArmorItem.Type.CHESTPLATE)) {
                        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, selectedItem)
                                .pattern("# #")
                                .pattern("###")
                                .pattern("###")
                                .define('#', itemMaterial)
                                .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                                .save(pRecipeOutput);
                    } else if (type.equals(ArmorItem.Type.LEGGINGS)) {
                        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, selectedItem)
                                .pattern("###")
                                .pattern("# #")
                                .pattern("# #")
                                .define('#', itemMaterial)
                                .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                                .save(pRecipeOutput);
                    } else if (type.equals(ArmorItem.Type.BOOTS)) {
                        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, selectedItem)
                                .pattern("# #")
                                .pattern("# #")
                                .define('#', itemMaterial)
                                .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                                .save(pRecipeOutput);
                    }
                }
            }
        });
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, itemBlock)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', itemMaterial)
                .unlockedBy(getHasName(itemMaterial), has(itemMaterial))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemMaterial, 9)
                .requires(itemBlock)
                .unlockedBy(getHasName(itemBlock), has(itemBlock))
                .save(pRecipeOutput);

        oreSmelting(pRecipeOutput, SMELTABLES.get(itemMaterialName), RecipeCategory.MISC, itemMaterial, 0.25f, 200, itemMaterialName);
        oreBlasting(pRecipeOutput, SMELTABLES.get(itemMaterialName), RecipeCategory.MISC, itemMaterial, 0.25f, 100, itemMaterialName);
    }
}
