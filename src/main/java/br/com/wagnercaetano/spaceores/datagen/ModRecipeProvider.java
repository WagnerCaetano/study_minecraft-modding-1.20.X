package br.com.wagnercaetano.spaceores.datagen;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GALACTITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.GALACTITE.get())
                .unlockedBy(getHasName(ModItems.GALACTITE.get()), has(ModItems.GALACTITE.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GALACTITE.get(), 9)
                .requires(ModBlocks.GALACTITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.GALACTITE_BLOCK.get()), has(ModBlocks.GALACTITE_BLOCK.get()))
                .save(pRecipeOutput);
    }
}
