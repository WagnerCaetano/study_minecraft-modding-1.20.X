package br.com.wagnercaetano.item.custom;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public enum CustomItemAssets {

    GALACTITE_STAFF("galactite_staff");

    private final String name;

    CustomItemAssets(String name) {
        this.name = name;
    }

    public static boolean isNotCustomItem(RegistryObject<Item> name) {
        for (CustomItemAssets customItem : CustomItemAssets.values()) {
            if (customItem.name.equals(name.getId().getPath())) {
                return false;
            }
        }
        return true;
    }


}
