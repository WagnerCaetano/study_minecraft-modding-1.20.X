package br.com.wagnercaetano.item.util;

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
