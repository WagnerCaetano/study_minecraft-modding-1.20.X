package br.com.wagnercaetano.spaceores.constants;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class Constants {
    public static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public enum CustomAssets {

        GALACTITE_STAFF("galactite_staff"),
        STRAWBERRY_CROP("strawberry_crop");

        private final String name;

        CustomAssets(String name) {
            this.name = name;
        }

        public static <T> boolean isNotCustom(RegistryObject<T> name) {
            for (CustomAssets customItem : CustomAssets.values()) {
                if (customItem.name.equals(name.getId().getPath())) {
                    return false;
                }
            }
            return true;
        }
    }
}
