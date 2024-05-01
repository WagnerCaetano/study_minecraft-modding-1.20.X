package br.com.wagnercaetano.spaceores.datagen;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.item.ModItems;
import br.com.wagnercaetano.spaceores.item.ModOreInfoTable;
import br.com.wagnercaetano.spaceores.item.custom.ModArmorItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Arrays;
import java.util.List;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> GALACTITE_SMELTABLES = Arrays.asList(ModItems.RAW_GALACTITE.get(),
            ModBlocks.GALACTITE_ORE.get(), ModBlocks.DEEPSLATE_GALACTITE_ORE.get(), ModBlocks.NETHER_GALACTITE_ORE.get(), ModBlocks.END_STONE_GALACTITE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        oreSmelting(pRecipeOutput, GALACTITE_SMELTABLES, RecipeCategory.MISC, ModItems.GALACTITE.get(), 0.25f, 200, "galactite");
        oreBlasting(pRecipeOutput, GALACTITE_SMELTABLES, RecipeCategory.MISC, ModItems.GALACTITE.get(), 0.25f, 100, "galactite");

        generateRecipeForItems(ModOreInfoTable.GALACTITE, pRecipeOutput);
    }

    private void generateRecipeForItems(ModOreInfoTable modOreInfoTable, RecipeOutput pRecipeOutput) {
        ModItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
            if (itemRegistryObject.getId().getPath().contains(modOreInfoTable.getName())) {
                Item item = itemRegistryObject.get();
                Class<? extends Item> aClass = item.getClass();
                if (aClass.equals(SwordItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                            .pattern("#")
                            .pattern("#")
                            .pattern("|")
                            .define('#', modOreInfoTable.getItem())
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                            .save(pRecipeOutput);
                } else if (aClass.equals(PickaxeItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, item)
                            .pattern("###")
                            .pattern(" | ")
                            .pattern(" | ")
                            .define('#', modOreInfoTable.getItem())
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                            .save(pRecipeOutput);
                } else if (aClass.equals(AxeItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, item)
                            .pattern("##")
                            .pattern("#|")
                            .pattern(" |")
                            .define('#', modOreInfoTable.getItem())
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                            .save(pRecipeOutput);
                } else if (aClass.equals(ShovelItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, item)
                            .pattern("#")
                            .pattern("|")
                            .pattern("|")
                            .define('#', modOreInfoTable.getItem())
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                            .save(pRecipeOutput);
                } else if (aClass.equals(HoeItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, item)
                            .pattern("##")
                            .pattern(" |")
                            .pattern(" |")
                            .define('#', modOreInfoTable.getItem())
                            .define('|', Items.STICK)
                            .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                            .save(pRecipeOutput);
                } else if (aClass.equals(ModArmorItem.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                            .pattern("###")
                            .pattern("# #")
                            .define('#', modOreInfoTable.getItem())
                            .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                            .save(pRecipeOutput);
                } else if (aClass.equals(ArmorItem.class)) {
                    ArmorItem.Type type = ((ArmorItem)item).getType();
                    // verify what type of armor is
                    if (type.equals(ArmorItem.Type.CHESTPLATE)) {
                        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                                .pattern("# #")
                                .pattern("###")
                                .pattern("###")
                                .define('#', modOreInfoTable.getItem())
                                .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                                .save(pRecipeOutput);
                    } else if (type.equals(ArmorItem.Type.LEGGINGS)) {
                        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                                .pattern("###")
                                .pattern("# #")
                                .pattern("# #")
                                .define('#', modOreInfoTable.getItem())
                                .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                                .save(pRecipeOutput);
                    } else if (type.equals(ArmorItem.Type.BOOTS)) {
                        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                                .pattern("# #")
                                .pattern("# #")
                                .define('#', modOreInfoTable.getItem())
                                .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                                .save(pRecipeOutput);
                    }
                } else if (aClass.equals(Item.class)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item)
                            .pattern("###")
                            .pattern("###")
                            .pattern("###")
                            .define('#', modOreInfoTable.getItem())
                            .unlockedBy(getHasName(modOreInfoTable.getItem()), has(modOreInfoTable.getItem()))
                            .save(pRecipeOutput);

                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item, 9)
                            .requires(ModBlocks.GALACTITE_BLOCK.get())
                            .unlockedBy(getHasName(modOreInfoTable.getBlock()), has(modOreInfoTable.getBlock()))
                            .save(pRecipeOutput);
                }
            }
        });
    }
}
