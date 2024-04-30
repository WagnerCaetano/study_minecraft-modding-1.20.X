package br.com.wagnercaetano.spaceores.block;

import br.com.wagnercaetano.spaceores.util.RegisterBlockUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public enum ModOreBlocks {

    GALACTITE_ORE("galactite_ore", Blocks.STONE, 2f, 3, 6),
    DEEPSLATE_GALACTITE_ORE("deepslate_galactite_ore", Blocks.DEEPSLATE, 3f, 3, 7),
    NETHER_GALACTITE_ORE("nether_galactite_ore", Blocks.NETHERRACK, 1f, 3, 7),
    END_STONE_GALACTITE_ORE("end_stone_galactite_ore", Blocks.END_STONE, 5f, 3, 7);

    private String name;
    private Block copyParameter;
    private float strengthParameter;
    private int minExperienceParameter;
    private int maxExperienceParameter;

    ModOreBlocks(String name, Block stone, float strengthParameter, int min, int max) {
        this.name = name;
        this.copyParameter = stone;
        this.strengthParameter = strengthParameter;
        this.minExperienceParameter = min;
        this.maxExperienceParameter = max;
    }

    public static RegistryObject<Block> registryObjectFromName(DeferredRegister<Block> blockList, String name) {
        for (ModOreBlocks block : ModOreBlocks.values()) {
            if (block.name.equals(name)) {
                return RegisterBlockUtils.registerBlock(blockList, block.name,
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(block.copyParameter)
                                .sound(SoundType.CALCITE).strength(block.strengthParameter),
                                UniformInt.of(block.minExperienceParameter, block.maxExperienceParameter)));
            }
        }
        return null;
    }
}
