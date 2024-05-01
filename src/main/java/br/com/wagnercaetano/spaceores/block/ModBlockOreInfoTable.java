package br.com.wagnercaetano.spaceores.block;

import br.com.wagnercaetano.spaceores.datagen.loot.ModBlockLootTables;
import br.com.wagnercaetano.spaceores.item.ModItems;
import br.com.wagnercaetano.spaceores.util.RegisterBlockUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public enum ModBlockOreInfoTable {

    RAW_CONSTELLARITE_BLOCK("raw_constellarite_block", Blocks.RAW_IRON_BLOCK, 5f,
            0, 0, 9, 9, ModItems.RAW_CONSTELLARITE),
    CONSTELLARITE_ORE("constellarite_ore", Blocks.STONE, 2f
            , 3, 5, 1, 1, ModItems.RAW_CONSTELLARITE),
    DEEPSLATE_CONSTELLARITE_ORE("deepslate_constellarite_ore", Blocks.DEEPSLATE, 3f,
            3, 6, 1, 1, ModItems.RAW_CONSTELLARITE),
    NETHER_CONSTELLARITE_ORE("nether_constellarite_ore", Blocks.NETHERRACK, 1f,
            3, 6, 1, 2, ModItems.RAW_CONSTELLARITE),
    END_STONE_CONSTELLARITE_ORE("end_stone_constellarite_ore", Blocks.END_STONE, 5f,
            3, 6, 2, 3, ModItems.RAW_CONSTELLARITE),

    RAW_GALACTITE_BLOCK("raw_galactite_block", Blocks.RAW_IRON_BLOCK, 5f,
            0, 0, 9, 9, ModItems.RAW_GALACTITE),
    GALACTITE_ORE("galactite_ore", Blocks.STONE, 2f,
            3, 6, 1, 1, ModItems.RAW_GALACTITE),
    DEEPSLATE_GALACTITE_ORE("deepslate_galactite_ore", Blocks.DEEPSLATE, 3f,
            3, 7, 1, 1, ModItems.RAW_GALACTITE),
    NETHER_GALACTITE_ORE("nether_galactite_ore", Blocks.NETHERRACK, 1f,
            3, 7, 1, 2, ModItems.RAW_GALACTITE),
    END_STONE_GALACTITE_ORE("end_stone_galactite_ore", Blocks.END_STONE, 5f,
            3, 7, 2, 3, ModItems.RAW_GALACTITE);

    private String name;
    private Block copyParameter;
    private float strengthParameter;
    private int minExperienceParameter;
    private int maxExperienceParameter;
    private int dropMin;
    private int dropMax;
    private RegistryObject<Item> dropMaterial;

    ModBlockOreInfoTable(String name, Block copyParameter, float strengthParameter,
                         int minExperienceParameter, int maxExperienceParameter, int dropMin,
                         int dropMax, RegistryObject<Item> dropMaterial) {
        this.name = name;
        this.copyParameter = copyParameter;
        this.strengthParameter = strengthParameter;
        this.minExperienceParameter = minExperienceParameter;
        this.maxExperienceParameter = maxExperienceParameter;
        this.dropMin = dropMin;
        this.dropMax = dropMax;
        this.dropMaterial = dropMaterial;
    }

    public static RegistryObject<Block> registryOreBlockFromName(DeferredRegister<Block> blockList, String name) {
        for (ModBlockOreInfoTable block : ModBlockOreInfoTable.values()) {
            if (block.name.equals(name)) {
                return RegisterBlockUtils.registerBlock(blockList, block.name,
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(block.copyParameter)
                                .sound(SoundType.CALCITE).strength(block.strengthParameter),
                                UniformInt.of(block.minExperienceParameter, block.maxExperienceParameter)));
            }
        }
        return null;
    }

    public static RegistryObject<Block> registryRawBlockFromName(DeferredRegister<Block> blockList, String name) {
        for (ModBlockOreInfoTable block : ModBlockOreInfoTable.values()) {
            if (block.name.equals(name)) {
                return RegisterBlockUtils.registerBlock(blockList, name,
                        () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).sound(SoundType.ROOTS)));
            }
        }
        return null;
    }

    public static void processOreLootTable (DeferredRegister<Block> blockList,
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
