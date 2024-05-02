package br.com.wagnercaetano.spaceores.block;

import br.com.wagnercaetano.spaceores.datagen.loot.ModBlockLootTables;
import br.com.wagnercaetano.spaceores.item.ModItems;
import br.com.wagnercaetano.spaceores.util.ModTags;
import br.com.wagnercaetano.spaceores.util.RegisterBlockUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static br.com.wagnercaetano.spaceores.util.RegisterBlockUtils.registryOreBlockFromName;
import static br.com.wagnercaetano.spaceores.util.RegisterBlockUtils.registryRawBlockFromName;

public enum ModBlockOreInfoTable {

    RAW_CONSTELLARITE_BLOCK("raw_constellarite_block", Blocks.RAW_IRON_BLOCK, 5f,
            0, 0, 9, 9, ModItems.RAW_CONSTELLARITE,
            List.of(ModTags.Blocks.NEEDS_CONSTELLARITE_TOOL)),
    CONSTELLARITE_ORE("constellarite_ore", Blocks.STONE, 2f
            , 3, 5, 1, 1, ModItems.RAW_CONSTELLARITE,
            List.of(BlockTags.NEEDS_IRON_TOOL, ModTags.Blocks.METAL_DETECTOR_VALUABLES)),
    DEEPSLATE_CONSTELLARITE_ORE("deepslate_constellarite_ore", Blocks.DEEPSLATE, 3f,
            3, 6, 1, 1, ModItems.RAW_CONSTELLARITE,
            List.of(BlockTags.NEEDS_IRON_TOOL)),
    NETHER_CONSTELLARITE_ORE("nether_constellarite_ore", Blocks.NETHERRACK, 1f,
            3, 6, 1, 2, ModItems.RAW_CONSTELLARITE,
            List.of(BlockTags.NEEDS_IRON_TOOL)),
    END_STONE_CONSTELLARITE_ORE("end_stone_constellarite_ore", Blocks.END_STONE, 5f,
            3, 6, 2, 3, ModItems.RAW_CONSTELLARITE,
            List.of(ModTags.Blocks.NEEDS_CONSTELLARITE_TOOL)),

    RAW_GALACTITE_BLOCK("raw_galactite_block", Blocks.RAW_IRON_BLOCK, 5f,
            0, 0, 9, 9, ModItems.RAW_GALACTITE,
            List.of(ModTags.Blocks.NEEDS_GALACTITE_TOOL)),
    GALACTITE_ORE("galactite_ore", Blocks.STONE, 2f,
            3, 6, 1, 1, ModItems.RAW_GALACTITE,
            List.of(ModTags.Blocks.NEEDS_CONSTELLARITE_TOOL, ModTags.Blocks.METAL_DETECTOR_VALUABLES)),
    DEEPSLATE_GALACTITE_ORE("deepslate_galactite_ore", Blocks.DEEPSLATE, 3f,
            3, 7, 1, 1, ModItems.RAW_GALACTITE,
            List.of(ModTags.Blocks.NEEDS_CONSTELLARITE_TOOL)),
    NETHER_GALACTITE_ORE("nether_galactite_ore", Blocks.NETHERRACK, 1f,
            3, 7, 1, 2, ModItems.RAW_GALACTITE,
            List.of(ModTags.Blocks.NEEDS_CONSTELLARITE_TOOL)),
    END_STONE_GALACTITE_ORE("end_stone_galactite_ore", Blocks.END_STONE, 5f,
            3, 7, 2, 3, ModItems.RAW_GALACTITE,
            List.of( ModTags.Blocks.NEEDS_GALACTITE_TOOL));

    private final String name;
    private final Block copyParameter;
    private final float strengthParameter;
    private final int minExperienceParameter;
    private final int maxExperienceParameter;
    private final int dropMin;
    private final int dropMax;
    private final RegistryObject<Item> dropMaterial;
    private final List<TagKey<Block>> requiresTool;

    ModBlockOreInfoTable(String name, Block copyParameter, float strengthParameter,
                         int minExperienceParameter, int maxExperienceParameter, int dropMin,
                         int dropMax, RegistryObject<Item> dropMaterial, List<TagKey<Block>> requiresTool) {
        this.name = name;
        this.copyParameter = copyParameter;
        this.strengthParameter = strengthParameter;
        this.minExperienceParameter = minExperienceParameter;
        this.maxExperienceParameter = maxExperienceParameter;
        this.dropMin = dropMin;
        this.dropMax = dropMax;
        this.dropMaterial = dropMaterial;
        this.requiresTool = requiresTool;
    }

    public String getName() {
        return name;
    }

    public Block getCopyParameter() {
        return copyParameter;
    }

    public float getStrengthParameter() {
        return strengthParameter;
    }

    public int getMinExperienceParameter() {
        return minExperienceParameter;
    }

    public int getMaxExperienceParameter() {
        return maxExperienceParameter;
    }

    public List<TagKey<Block>> getRequiresTool() {
        return Objects.isNull(requiresTool) ? Collections.emptyList() : requiresTool;
    }

    public static ModBlockOreInfoTable getByRegistryBlock(RegistryObject<Block> block) {
        for (ModBlockOreInfoTable modBlockOreInfoTable : ModBlockOreInfoTable.values()) {
            if (modBlockOreInfoTable.name.equals(block.getId().getPath())) {
                return modBlockOreInfoTable;
            }
        }
        return null;
    }

    public static DeferredRegister<Block> processOreInstances(DeferredRegister<Block> blockList) {
        for (ModBlockOreInfoTable block : ModBlockOreInfoTable.values()) {
            if (block.name.contains("raw")) {
                registryRawBlockFromName(blockList, block);
            } else {
                registryOreBlockFromName(blockList, block);
            }
        }
        return blockList;
    }

    public static void processOreLootTable(DeferredRegister<Block> blockList,
                                            BiConsumer<Block, Function<Block, LootTable.Builder>> addBiConsumer,
                                            Consumer<Block> dropSelf,
                                            ModBlockLootTables modBlockLootTables){
        for (RegistryObject<Block> selectedBlock : blockList.getEntries()) {
            String pathName = selectedBlock.getId().getPath();
            Optional<ModBlockOreInfoTable> selectedModOreBlockOptional =
                    Arrays.stream(ModBlockOreInfoTable.values()).filter(v -> v.name.equals(pathName)).findFirst();
            if (selectedModOreBlockOptional.isEmpty()) {
                continue;
            }
            ModBlockOreInfoTable selectedModOreBlock = selectedModOreBlockOptional.get();
            if (pathName.contains("ore")) {
                addBiConsumer.accept(selectedBlock.get(),
                        block -> modBlockLootTables.createOreDrop(block,
                                selectedModOreBlock.dropMaterial.get(),
                                selectedModOreBlock.dropMin ,
                                selectedModOreBlock.dropMax));
            } else {
                dropSelf.accept(selectedBlock.get());
            }

        }


    }
}
