package br.com.wagnercaetano.spaceores.item;

import br.com.wagnercaetano.spaceores.SpaceOres;
import br.com.wagnercaetano.spaceores.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolsTiers {
    public static final Tier CONSTELLARITE = TierSortingRegistry.registerTier(
            new ForgeTier(3, 500, 10f, 1f, 5, ModTags.Blocks.NEEDS_CONSTELLARITE_TOOL, () -> Ingredient.of(ModItems.CONSTELLARITE.get())),
            new ResourceLocation(SpaceOres.MOD_ID, "constellarite"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));

    public static final Tier GALACTITE = TierSortingRegistry.registerTier(
            new ForgeTier(4, 1800, 6.5f, 3.5f, 10, ModTags.Blocks.NEEDS_GALACTITE_TOOL, () -> Ingredient.of(ModItems.GALACTITE.get())),
            new ResourceLocation(SpaceOres.MOD_ID, "galactite"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));

}
