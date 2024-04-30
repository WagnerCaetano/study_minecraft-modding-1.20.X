package br.com.wagnercaetano.datagen.loot;

import br.com.wagnercaetano.block.ModBlocks;
import br.com.wagnercaetano.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.GALACTITE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_GALACTITE_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());

        this.add(ModBlocks.RAW_GALACTITE_BLOCK.get(), block -> createOreDrop(block, ModItems.RAW_GALACTITE.get(), 9 , 9));
        this.add(ModBlocks.GALACTITE_ORE.get(), block -> createOreDrop(block, ModItems.RAW_GALACTITE.get(), 1, 1));
        this.add(ModBlocks.DEEPSLATE_GALACTITE_ORE.get(), block -> createOreDrop(block, ModItems.RAW_GALACTITE.get(), 1, 1));
        this.add(ModBlocks.NETHER_GALACTITE_ORE.get(), block -> createOreDrop(block, ModItems.RAW_GALACTITE.get(), 1, 2));
        this.add(ModBlocks.END_STONE_GALACTITE_ORE.get(), block -> createOreDrop(block, ModItems.RAW_GALACTITE.get(), 2, 3));

    }

    protected LootTable.@NotNull Builder createOreDrop(Block pBlock, Item drop, float min, float max) {
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
