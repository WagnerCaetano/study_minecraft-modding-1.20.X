package br.com.wagnercaetano.spaceores.datagen;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.item.ModItemOreInfoTable;
import br.com.wagnercaetano.spaceores.item.ModItems;
import br.com.wagnercaetano.spaceores.item.custom.ModArmorItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Arrays;
import java.util.List;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> CONSTELLARITE_SMELTABLES = Arrays.asList(ModItems.RAW_CONSTELLARITE.get(),
            ModBlocks.CONSTELLARITE_ORE.get(), ModBlocks.DEEPSLATE_CONSTELLARITE_ORE.get(), ModBlocks.NETHER_CONSTELLARITE_ORE.get(), ModBlocks.END_STONE_CONSTELLARITE_ORE.get());
    private static final List<ItemLike> GALACTITE_SMELTABLES = Arrays.asList(ModItems.RAW_GALACTITE.get(),
            ModBlocks.GALACTITE_ORE.get(), ModBlocks.DEEPSLATE_GALACTITE_ORE.get(), ModBlocks.NETHER_GALACTITE_ORE.get(), ModBlocks.END_STONE_GALACTITE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        oreSmelting(pRecipeOutput, CONSTELLARITE_SMELTABLES, RecipeCategory.MISC, ModItems.CONSTELLARITE.get(), 0.25f, 200, "constellarite");
        oreBlasting(pRecipeOutput, CONSTELLARITE_SMELTABLES, RecipeCategory.MISC, ModItems.CONSTELLARITE.get(), 0.25f, 100, "constellarite");
        oreSmelting(pRecipeOutput, GALACTITE_SMELTABLES, RecipeCategory.MISC, ModItems.GALACTITE.get(), 0.25f, 200, "galactite");
        oreBlasting(pRecipeOutput, GALACTITE_SMELTABLES, RecipeCategory.MISC, ModItems.GALACTITE.get(), 0.25f, 100, "galactite");


        generateRecipeForItems(ModItemOreInfoTable.CONSTELLARITE, pRecipeOutput);
        generateRecipeForItems(ModItemOreInfoTable.GALACTITE, pRecipeOutput);

    }

    private void generateRecipeForItems(ModItemOreInfoTable modItemOreInfoTable, RecipeOutput pRecipeOutput) {
        Item itemMaterial = modItemOreInfoTable.getItem().get();
        Block itemBlock = modItemOreInfoTable.getBlock().get();
        ModItems.ITEMS.getEntries().forEach(selectedItemRegistry -> {
            if (selectedItemRegistry.getId().getPath().contains(modItemOreInfoTable.getName())) {
                Item selectedItem = selectedItemRegistry.get();
                Class<? extends Item> selectedItemClass = selectedItem.getClass();
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
    }
}
