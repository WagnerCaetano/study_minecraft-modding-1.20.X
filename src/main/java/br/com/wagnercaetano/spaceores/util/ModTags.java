package br.com.wagnercaetano.spaceores.util;

import br.com.wagnercaetano.spaceores.SpaceOres;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = createTag("metal_detector_valuables");
        public static final TagKey<Block> NEEDS_GALACTITE_TOOL = createTag("needs_galactite_tool");
        public static final TagKey<Block> NEEDS_CONSTELLARITE_TOOL = createTag("needs_constellarite_tool");

        private static TagKey<Block> createTag(String pName) {
            return BlockTags.create(new ResourceLocation(SpaceOres.MOD_ID, pName));
        }
    }

    public static class Items {


        private static TagKey<Item> createTag(String pName) {
            return ItemTags.create(new ResourceLocation(SpaceOres.MOD_ID, pName));
        }
    }
}
