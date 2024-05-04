package br.com.wagnercaetano.spaceores.datagen.loot;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.block.ModBlockOreInfoTable;
import br.com.wagnercaetano.spaceores.block.custom.CornCropBlock;
import br.com.wagnercaetano.spaceores.block.custom.StrawberryCropBlock;
import br.com.wagnercaetano.spaceores.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    private static final LootItemCondition.Builder BLOCK_HIGH_CROP_BUILDER = LootItemBlockStatePropertyCondition
            .hasBlockStateProperties(ModBlocks.CORN_CROP.get())
            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 7))
            .or(LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(ModBlocks.CORN_CROP.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 8)));

    LootItemCondition.Builder CROP_BUILDER = LootItemBlockStatePropertyCondition
            .hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP.get())
            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 5));

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.CONSTELLARITE_BLOCK.get());
        this.dropSelf(ModBlocks.GALACTITE_BLOCK.get());

        this.dropSelf(ModBlocks.SOUND_BLOCK.get());

        ModBlockOreInfoTable.processOreLootTable(ModBlocks.BLOCKS, this::add, this::dropSelf, this);

        this.add(ModBlocks.CORN_CROP.get(), createCropDrops(ModBlocks.CORN_CROP.get(), ModItems.CORN.get(),
                ModItems.CORN_SEEDS.get(), BLOCK_HIGH_CROP_BUILDER));

        this.add(ModBlocks.STRAWBERRY_CROP.get(),
                createCropDrops(ModBlocks.STRAWBERRY_CROP.get(),
                        ModItems.STRAWBERRY.get(), ModItems.STRAWBERRY_SEEDS.get(), CROP_BUILDER));
    }

    public LootTable.@NotNull Builder createOreDrop(Block pBlock, Item drop, float min, float max) {
        return createSilkTouchDispatchTable(pBlock,
                applyExplosionDecay(pBlock, LootItem.lootTableItem(drop)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
